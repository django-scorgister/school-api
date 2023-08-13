package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.CJSON;
import net.argus.school.api.Materials;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.pack.PackagePrefab;

public class APIMaterialsHandler extends APIHandler {

	public APIMaterialsHandler() {
		super("materials");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.getMaterialsPackage());
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		CJSON parameters = getCJSONParameters(exchange);

		switch(parameters.getString("action").toLowerCase()) {
			case "add":
				Materials.addMaterials(parameters.getString("name"), parameters.getInt("base_quantity"));
				sendEmptyPackage(exchange);
				break;
				
			case "remove":
				Materials.removeMaterial(parameters.getInt("id"));
				sendEmptyPackage(exchange);
				break;
		}
	}

}
