package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.school.api.MainAPI;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.pack.PackagePrefab;


public class APIVersionHandler extends APIHandler {

	public APIVersionHandler() {
		super("version");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.getVersionPackage(MainAPI.VERSION));

	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		doGet(exchange);
	}
	
}
