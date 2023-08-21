package net.argus.school.api.http;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;

public class FileHandler extends CardinalHandler {

	private String base;
	
	public FileHandler(String handle, String defaultRep) {
		super(handle, false);
		base = defaultRep;
	}
	
	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		String ressource = getRessource(exchange.getRequestURI());
		File f = new File(base + ressource);
		
		if(f.isDirectory())
			f = new File(base + ressource + "/index.html");
		
		if(!f.exists()) {
			send404(exchange);
			return;
		}
		sendFile(exchange, f);
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		doGet(exchange);
	}
	
	public String getRessource(URI uri) {
		String path = uri.getPath().substring(getName().length());
		if(path.equals(""))
			path = "/";
		return path;
	}
	
	@Override
	public void send404(HttpExchange exchange) throws IOException {
		sendFile(exchange, new File(base + "404.html"), HttpURLConnection.HTTP_NOT_FOUND);
	}
	
	public void sendFile(HttpExchange exchange, File file) throws IOException {
		sendFile(exchange, file, HttpURLConnection.HTTP_OK);
	}
	
	public void sendFile(HttpExchange exchange, File file, int code) throws IOException {
		exchange.getResponseHeaders().set("Content-Type", getContentType(file.getName()));
		exchange.sendResponseHeaders(code, file.length());
		exchange.getResponseBody().write(Files.readAllBytes(file.toPath()));
		exchange.close();
	}

	

}
