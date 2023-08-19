package net.argus.school.api.http;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.CJSON;
import net.argus.cjson.CJSONParser;
import net.argus.school.api.http.pack.PackagePrefab;

public abstract class APIHandler extends CardinalHandler {
	
	public APIHandler(String name) {
		super("/api/" + name, true);
	}
	
	public String getParameters(HttpExchange exchange) throws IOException {
		String lines = "";
		int i = 0;
		while((i = exchange.getRequestBody().read()) != -1)
			lines += new String(new byte[] {(byte) i}, StandardCharsets.UTF_8);
		return lines;
	}
	
	public String[] getBoundaryParameters(HttpExchange exchange, String boundary) throws IOException {
		List<Byte> byteList = new ArrayList<Byte>();
		
		int i = 0;
		while((i = exchange.getRequestBody().read()) != -1) {
			byteList.add((byte) i);		
		}
		
		int j = 0;
		List<Byte> b = new ArrayList<Byte>();
		byte[] by = new byte[byteList.size()];
		
		int sep = -1;
		for(byte o : byteList) {
			by[j] = o;
			
			if(b.size() == 5) {
				if(b.get(0) == "\r".getBytes()[0] && b.get(1) == "\n".getBytes()[0] && b.get(2) == "\r".getBytes()[0] && b.get(3) == "\n".getBytes()[0])
					sep = j;
				b.remove(0);
			}
			
			b.add(o);
			
			j++;
		}
		String header = new String(by, StandardCharsets.UTF_8);
		if(header.startsWith("--" + boundary + "--"))
			return null;
		
		header = header.substring(0, header.indexOf("\r\n\r\n"));
		
		byte[] bodyBytes = new byte[by.length - sep + 1];
		System.arraycopy(by, sep-1, bodyBytes, 0, bodyBytes.length);
		
		return new String[] {header + "\r\n", new String(bodyBytes, StandardCharsets.ISO_8859_1)};
	}
	
	public CJSON getCJSONParameters(HttpExchange exchange) throws IOException {
		return CJSONParser.getCJSON(getParameters(exchange));
	}
	
	public void sendEmptyPackage(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.getEmptyPackage());
	}

}
