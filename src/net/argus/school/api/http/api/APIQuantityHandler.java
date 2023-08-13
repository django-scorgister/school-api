package net.argus.school.api.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.CJSON;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONValue;
import net.argus.school.api.Quantities;
import net.argus.school.api.http.APIHandler;
import net.argus.school.api.http.pack.PackagePrefab;

public class APIQuantityHandler extends APIHandler {

	public APIQuantityHandler() {
		super("quantity");
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
				int id = parameters.getInt("id");
				int uid = parameters.getInt("user_id");
				CJSONObject obj = Quantities.getObject(id);
				if(obj == null) {
					send404(exchange);
					break;
				}

				CJSONValue val = null;
				if((val = obj.getValue(Integer.toString(uid))) == null) {
					Quantities.addStudentCount(id, uid, 0);
					send(exchange, PackagePrefab.getUserQuantityPackage(0));
					break;
				}
				
				int q = val.getInt();
				send(exchange, PackagePrefab.getUserQuantityPackage(q));
				break;
				
			case "add":
				id = parameters.getInt("id");
				uid = parameters.getInt("user_id");
				int add = parameters.getInt("quantity");
				
				Quantities.addStudentCount(id, uid, add);
				sendEmptyPackage(exchange);
				break;
		}
	}

}
