package net.argus.school.api.handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.Array;
import net.argus.cjson.CJSON;
import net.argus.cjson.value.CJSONValue;
import net.argus.school.api.MainAPI;
import net.argus.school.api.event.EventSchool;
import net.argus.school.api.event.SchoolEvent;
import net.argus.school.api.handler.pack.SchoolPackagePrefab;
import net.argus.web.http.APIHandler;

public class APIResetHandler extends APIHandler {

	public APIResetHandler() {
		super("reset");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		send(exchange, SchoolPackagePrefab.getResetRequestPackage(MainAPI.invokeResetRequestEvent(new SchoolEvent())));
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		CJSON parameters = getCJSONParameters(exchange);
		
		Array array = parameters.getArray("reset");
		
		for(CJSONValue val : array.getArray())
			MainAPI.invokeEvent(EventSchool.RESET, new SchoolEvent(val.getString()));
		
		sendEmptyPackage(exchange);
	}

}
