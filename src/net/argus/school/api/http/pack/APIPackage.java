package net.argus.school.api.http.pack;

import net.argus.cjson.CJSON;

public class APIPackage extends CJSON {
	
	private int code;
	
	public APIPackage(PackageBuilder builder, int httpCode) {
		super(builder);
		this.code = httpCode;
	}

	public boolean isSuccess() {
		return getBoolean("success");
	}
	
	public byte[] getByte() {
		return toString().getBytes();
	}
	
	public long length() {
		return toString().length();
	}
	
	public int getHttpCode() {
		return code;
	}

}
