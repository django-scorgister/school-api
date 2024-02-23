package net.argus.school.api.handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.cjson.CJSON;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONValue;
import net.argus.school.api.Quantities;
import net.argus.school.api.handler.pack.SchoolPackagePrefab;
import net.argus.web.http.APIHandler;
import net.argus.web.http.pack.PackagePrefab;

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
				int q = getQuantity(id, uid);
				
				if(q == -1) {
					send404(exchange);
					break;
				}
				
				send(exchange, SchoolPackagePrefab.getQuantityPackage(q));
				break;
				
			case "get_quantity":
				id = parameters.getInt("id");
				q = Quantities.getQuantity(id);

				if(q >= 0)
					send(exchange, SchoolPackagePrefab.getQuantityPackage(q));
				else
					send500(exchange);
				
				break;
				
			case "get_base":
				id = parameters.getInt("id");
				CJSONObject obj = Quantities.getObject(id);
				if(obj == null) {
					send404(exchange);
					break;
				}
				
				int base = obj.getInt("base");
				send(exchange, SchoolPackagePrefab.getQuantityPackage(base));
				break;
				
			case "add":
				id = parameters.getInt("id");
				uid = parameters.getInt("user_id");
				int add = parameters.getInt("quantity");

				if(Quantities.getQuantity(id) - add < 0) {
					send500(exchange);
					break;
				}
				
				if(getQuantity(id, uid) + add < 0) {
					send500(exchange);
					break;
				}
				
				Quantities.addStudentCount(id, uid, add);
				sendEmptyPackage(exchange);
				break;
				
			case "add_quantity":
				id = parameters.getInt("id");
				add = parameters.getInt("quantity");
				
				if(Quantities.getQuantity(id) + add < 0) {
					send500(exchange);
					break;
				}
				
				Quantities.addCount(id, add);
				sendEmptyPackage(exchange);
				break;
		}
	}
	
	private int getQuantity(int id, int uid) throws IOException {
		CJSONObject obj = Quantities.getObject(id);
		if(obj == null)
			return -1;

		CJSONValue val = null;
		if((val = obj.getValue(Integer.toString(uid))) == null) {
			Quantities.addStudentCount(id, uid, 0);
			return 0;
		}
		
		return val.getInt();
	}

}
