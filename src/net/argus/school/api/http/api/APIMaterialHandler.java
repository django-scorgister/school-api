package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.CJSON;
import net.argus.school.api.Material;
import net.argus.school.api.Materials;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.pack.PackagePrefab;

public class APIMaterialHandler extends APIHandler {

	public APIMaterialHandler() {
		super("material");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.get405Package());
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		CJSON parameters = getCJSONParameters(exchange);

		switch(parameters.getString("action").toLowerCase()) {
			case "get":
				Material mat = Materials.getMaterial(parameters.getInt("id"));
				if(mat == null) {
					send404(exchange);
					break;
				}
				
				send(exchange, PackagePrefab.getMaterialPAckage(mat));
				break;
		}
	}

}
