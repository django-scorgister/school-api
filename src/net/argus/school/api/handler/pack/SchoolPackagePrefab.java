package net.argus.school.api.handler.pack;

import java.util.ArrayList;
import java.util.List;

import net.argus.cjson.value.CJSONArray;
import net.argus.cjson.value.CJSONInteger;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONString;
import net.argus.cjson.value.CJSONValue;
import net.argus.plugin.Plugin;
import net.argus.plugin.PluginRegister;
import net.argus.plugin.annotation.PluginInfo;
import net.argus.school.api.Material;
import net.argus.school.api.Materials;
import net.argus.school.api.Students;
import net.argus.web.http.pack.APIPackage;
import net.argus.web.http.pack.PackageBuilder;

public class SchoolPackagePrefab {
	
	public static APIPackage getPluginsPackage() {
		CJSONObject obj = new CJSONObject();
		
		List<CJSONValue> array = new ArrayList<CJSONValue>();
		
		for(Plugin plug : PluginRegister.getPlugins()) {
			CJSONObject plgObj = new CJSONObject();
			PluginInfo info = PluginRegister.getInfo(plug);
			
			List<CJSONValue> auths = new ArrayList<CJSONValue>();
			
			for(String au : info.authors())
				auths.add(new CJSONString(au));
			
			
			plgObj.addItem("authors", new CJSONArray(auths));
			plgObj.addItem("description", new CJSONString(info.description()));
			plgObj.addItem("name", new CJSONString(info.name()));
			plgObj.addItem("plugin_id", new CJSONString(info.pluginId()));
			plgObj.addItem("version", new CJSONString(info.version()));
			
			array.add(plgObj);
		}
		
		obj.addItem("plugins", new CJSONArray(array));

		return PackageBuilder.getSucessPackage(obj);
	}
	
	public static APIPackage getPluginPackage(Plugin plug) {
		CJSONObject obj = new CJSONObject();
		
		PluginInfo info = PluginRegister.getInfo(plug);
			
		List<CJSONValue> auths = new ArrayList<CJSONValue>();
			
		for(String au : info.authors())
			auths.add(new CJSONString(au));
			
			
		obj.addItem("authors", new CJSONArray(auths));
		obj.addItem("description", new CJSONString(info.description()));
		obj.addItem("name", new CJSONString(info.name()));
		obj.addItem("plugin_id", new CJSONString(info.pluginId()));
		obj.addItem("version", new CJSONString(info.version()));

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
	
	public static APIPackage getMaterialPackage(Material material) {
		if(material == null)
			return null;
		
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("name", new CJSONString(material.getName()));
		obj.addItem("base_quantity", new CJSONInteger(material.getBaseQuantity()));
		return PackageBuilder.getSucessPackage(obj);
	}
	
	public static APIPackage getQuantityPackage(int quantity) {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("quantity", new CJSONInteger(quantity));
		return PackageBuilder.getSucessPackage(obj);
	}
	
	public static APIPackage getStudentPackage(String name, int id) {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("name", new CJSONString(name));
		obj.addItem("id", new CJSONInteger(id));
		return PackageBuilder.getSucessPackage(obj);
	}
	
	public static APIPackage getMaterialPackage(String name) {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("name", new CJSONString(name));
		return PackageBuilder.getSucessPackage(obj);
	}

}
