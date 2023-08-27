package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.CJSON;
import net.argus.school.api.Materials;
import net.argus.school.api.Quantities;
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
			case "get":
				String name = Materials.getMaterial(parameters.getInt("id")).getName();
				send(exchange, PackagePrefab.getMaterialPackage(name));
				break;
				
			case "add":
				Materials.addMaterials(parameters.getString("name"), parameters.getInt("base_quantity"));
				sendEmptyPackage(exchange);
				break;
				
			case "update":
				int id = parameters.getInt("id");
				String newName = parameters.getString("name");
				boolean success = Materials.updateMaterialsName(id, newName);
				
				if(success) sendEmptyPackage(exchange);
				else send500(exchange);
				break;
				
				
			case "remove":
				id = parameters.getInt("id");
				if(Materials.removeMaterial(id))
					if(Quantities.removeMaterial(id)) {
						sendEmptyPackage(exchange);
						break;
					}
				send500(exchange);
				break;
		}
	}

}
