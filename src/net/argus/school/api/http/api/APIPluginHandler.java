package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.CJSON;
import net.argus.plugin.PluginRegister;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.pack.PackagePrefab;

public class APIPluginHandler extends APIHandler {

	public APIPluginHandler() {
		super("plugins");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.getPluginsPackage());
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		CJSON parameters = getCJSONParameters(exchange);

		switch(parameters.getString("action").toLowerCase()) {
			case "get":
				String pId = parameters.getString("plugin_id");
				for(int i = 0; i < PluginRegister.getInfos().size(); i++) {
					if(PluginRegister.getInfo(i).pluginId().equals(pId)) {
						send(exchange, PackagePrefab.getPluginPackage(PluginRegister.getPlugin(i)));
						break;
					}
				}
				send404(exchange);
				break;
		}
	}

}
