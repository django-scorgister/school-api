package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.school.api.http.APIHandler;
import net.argus.system.UserSystem;

public class APIExitHandler extends APIHandler {

	public APIExitHandler() {
		super("exit");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		sendEmptyPackage(exchange);
		UserSystem.exit(0);
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		doGet(exchange);
	}

}
