package net.argus.school.api.http.api.formdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.pack.PackagePrefab;

public class APIFormDataHandler extends APIHandler {

	private String defPath;
	
	public APIFormDataHandler(String name, String defPath) {
		super(name);
		this.defPath = defPath;
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.get405Package());
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		Headers headers = exchange.getRequestHeaders();
		
		String contentType = headers.getFirst("Content-Type");
		if(contentType.startsWith("multipart/form-data")){
			String boundary = contentType.substring(contentType.indexOf("boundary=")+9);
			String param = getParameters(exchange);
			if(!param.startsWith("--" + boundary)) {
				send404(exchange);
				return;
			}
			System.out.println(param);
			param = param.substring(boundary.length() + 4, param.length());
			
			FormData d = null;
			while(!param.startsWith("\r\n")) {
				String name = param.substring(0, param.indexOf(":"));
				String arg = param.substring(param.indexOf(":") + 2, param.indexOf("\r\n"));
				
				if(name.equals("Content-Disposition"))
					d = FormData.parse(arg);
				
				param = param.substring(param.indexOf("\r\n") + 2);
			}
			
			param = param.substring(param.indexOf("\r\n") + 2);
			
			String content = param.substring(0, param.indexOf("--" + boundary)-2);
			
			File f = new File(defPath + d.get("filename"));
			
			if(!f.exists())
				f.createNewFile();
			
			BufferedWriter w = Files.newBufferedWriter(f.toPath(), Charset.forName("UTF-8"));
			w.write(content);
			w.flush();
			w.close();
		}
	}

}
