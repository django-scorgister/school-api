package net.argus.school.api.http.api.formdata;

import java.util.HashMap;
import java.util.Map;

public class FormData {
	
	private Map<String, String> map = new HashMap<String, String>();
	private String name;
	
	public FormData(String name) {
		this.name = name;
	}
	
	public void add(String key, String value) {
		map.put(key, value);
	}
	
	public String get(String key) {
		return map.get(key);
	}
	
	public String getName() {
		return name;
	}
	
	public static FormData parse(String arg) {
		if(!arg.startsWith("form-data"))
			return null;
		
		arg = arg.substring("form-data; ".length());
		Map<String, String> l = new HashMap<String, String>();
		
		while(arg.length() > 0) {
			String name = arg.substring(0, arg.indexOf("="));
			
			int i = arg.indexOf(";")==-1?arg.length():arg.indexOf(";");
			String content = arg.substring(arg.indexOf("=")+2, i-1);
			
			l.put(name, content);
			
			arg = arg.substring(arg.indexOf(";")==-1?arg.length():arg.indexOf(";")+2);
		}

		FormData data = new FormData(l.get("name"));
		l.remove("name");
		data.map = l;
		
		return data;
	}
	
	@Override
	public String toString() {
		return "formdata@" + name + ":" + map;
	}

}
