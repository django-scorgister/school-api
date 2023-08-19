package net.argus.school.api.http.api.formdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.CardinalFileNameMap;
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
		if(contentType.startsWith("multipart/form-data")) {
			String boundary = contentType.substring(contentType.indexOf("boundary=")+9);
			String[] params = getBoundaryParameters(exchange, boundary);
			
			if(params == null) {
				send500(exchange);
				return;
			}
			
			String header = params[0];
			if(!header.startsWith("--" + boundary)) {
				send404(exchange);
				return;
			}

			header = header.substring(boundary.length() + 4, header.length());
			FormData d = null;
			
			String contentTypeFile = null;
			while(!header.equals("")) {
				String name = header.substring(0, header.indexOf(":"));
				String arg = header.substring(header.indexOf(":") + 2, header.indexOf("\r\n"));
				
				if(name.equals("Content-Disposition"))
					d = FormData.parse(arg);
				else if(name.equals("Content-Type"))
					contentTypeFile = arg;
				header = header.substring(header.indexOf("\r\n") + 2);
			}
			
			String content = params[1];
			
			content = content.substring(0, content.lastIndexOf(boundary) - 3);
			
			File f = new File(defPath + d.getName() + "." + CardinalFileNameMap.getExtentionByMimeType(contentTypeFile));
			
			if(!f.exists())
				f.createNewFile();
			
			BufferedWriter w = Files.newBufferedWriter(f.toPath(), StandardCharsets.ISO_8859_1);
			w.write(content);
			w.flush();
			w.close();
		}
		sendEmptyPackage(exchange);
	}

}
