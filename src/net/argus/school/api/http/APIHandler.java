package net.argus.school.api.http;

import java.io.IOException;

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
			lines += (char) i;
		return lines;
	}
	
	public CJSON getCJSONParameters(HttpExchange exchange) throws IOException {
		return CJSONParser.getCJSON(getParameters(exchange));
	}
	
	public void sendEmptyPackage(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.getEmptyPackage());
	}

}
