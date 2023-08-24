package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.school.api.MainAPI;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.pack.PackagePrefab;
import net.argus.system.UserSystem;


public class APIVersionHandler extends APIHandler {

	public APIVersionHandler() {
		super("version");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		String version = MainAPI.VERSION.toString();
		if(UserSystem.getManifest() != null)
			version = UserSystem.getCurrentVersion().toString() + "." + UserSystem.getCurrentDebug().toString();
		
		send(exchange, PackagePrefab.getVersionPackage(version));

	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		doGet(exchange);
	}
	
}
