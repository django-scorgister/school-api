package net.argus.school.api.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import net.argus.Cardinal;

public class APIServer {
	
	public static final String NAME = "Cardinal Server (" + Cardinal.VERSION + ")";
	
	private HttpServer server;
	
	public APIServer() throws IOException {
		server = HttpServer.create(new InetSocketAddress(8000), 100);
	}
	
	
	public void addHandle(CardinalHandler handler) {
		server.createContext(handler.getName(), handler);
	}
	
	public void start() {
		server.start();
	}

}
