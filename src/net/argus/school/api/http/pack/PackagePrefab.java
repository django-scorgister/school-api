package net.argus.school.api.http.pack;

import java.net.HttpURLConnection;

import net.argus.cjson.value.CJSONInteger;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONString;
import net.argus.school.api.Material;
import net.argus.school.api.Materials;
import net.argus.school.api.Students;
import net.argus.util.Version;

public class PackagePrefab {
	
	public static APIPackage get404Package() {
		return PackageBuilder.getErrorPackage("not found", "404 Not Found", HttpURLConnection.HTTP_NOT_FOUND);
	}
	
	public static APIPackage get405Package() {
		return PackageBuilder.getErrorPackage("Method Not Allowed", "405 method not allowed", HttpURLConnection.HTTP_BAD_METHOD);
	}
	
	public static APIPackage getEmptyPackage() {
		return PackageBuilder.getSucessPackage(new CJSONObject());
	}
	
	public static APIPackage getVersionPackage(Version version) {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("version", new CJSONString(version.getVersion()));
		return PackageBuilder.getSucessPackage(obj);
	}
	
	public static APIPackage getStudentsPackage() {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("students", Students.STUDENTS.getValue("students"));
		return PackageBuilder.getSucessPackage(obj);
	}
	
	public static APIPackage getMaterialsPackage() {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("materials", Materials.MATERIALS.getValue("materials"));
		return PackageBuilder.getSucessPackage(obj);
	}
	
	public static APIPackage getMaterialPAckage(Material material) {
		if(material == null)
			return null;
		
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("name", new CJSONString(material.getName()));
		obj.addItem("id", new CJSONInteger(material.getId()));
		obj.addItem("quantity", new CJSONInteger(material.getQuantity()));
		return PackageBuilder.getSucessPackage(obj);
	}

}
