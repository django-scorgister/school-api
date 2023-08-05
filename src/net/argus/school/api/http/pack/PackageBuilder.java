package net.argus.school.api.http.pack;

import java.net.HttpURLConnection;

import net.argus.cjson.CJSONBuilder;
import net.argus.cjson.value.CJSONObject;

public class PackageBuilder extends CJSONBuilder {
	
	public PackageBuilder(boolean success, CJSONObject result) {
		super();
		addBoolean(".", "success", success);
		addObject(".", "result", result);
	}
	
	public PackageBuilder(boolean success, String errorCode, String msg) {
		super();
		addBoolean(".", "success", success);
		addString(".", "error_code", errorCode);
		addString(".", "msg", msg);
	}
	
	public static APIPackage getSucessPackage(CJSONObject result, int httpCode) {
		return new APIPackage(new PackageBuilder(true, result), httpCode);
	}
	
	public static APIPackage getErrorPackage(String errorCode, String msg, int httpCode) {
		return new APIPackage(new PackageBuilder(false, errorCode, msg), httpCode);
	}
	
	public static APIPackage getSucessPackage(CJSONObject result) {
		return new APIPackage(new PackageBuilder(true, result), HttpURLConnection.HTTP_OK);
	}
}
