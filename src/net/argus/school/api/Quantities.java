package net.argus.school.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import net.argus.cjson.CJSON;
import net.argus.cjson.CJSONItem;
import net.argus.cjson.CJSONParser;
import net.argus.cjson.value.CJSONInteger;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONValue;
import net.argus.file.CJSONFile;
import net.argus.instance.Instance;

public class Quantities {
	
	public static final CJSONFile FILE = new CJSONFile("quantities", "", Instance.SYSTEM);
	public static final CJSON QUANTITIES = CJSONParser.getCJSON(FILE);

	
	public static CJSONObject getMainObject() {
		return QUANTITIES.getMainObject();
	}
	
	public static int getQuantity(int id) throws IOException {
		CJSONObject obj = Quantities.getObject(id);
		if(obj == null)
			return -1;
		
		int base = obj.getInt("base");
		
		for(CJSONItem items : obj.getValue()) {
			if(items.getName().equals("base"))
				continue;
			
			base -= items.getValue().getInt();
		}
		return base;
	}

	public static void addMaterial(int id, int baseQuantity) throws IOException {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("base", new CJSONInteger(baseQuantity));
		getMainObject().addItem(Integer.toString(id), obj);
		
		writeFile();
	}
	
	public static boolean addStudentCount(int id, int uid, int add) throws IOException {
		CJSONObject obj = getMainObject().getObject(Integer.toString(id));
		
		CJSONValue val = null;
		if((val = obj.getValue(Integer.toString(uid))) == null)
			obj.addItem(Integer.toString(uid), new CJSONInteger(add));
		else {
			int c = val.getInt() + add;
			
			List<CJSONItem> items = obj.getValue();

			for(int i = 0; i < items.size(); i++) {
				if(items.get(i).getName().equals(Integer.toString(uid))) {
					items.remove(i);
					items.add(new CJSONItem(Integer.toString(uid), new CJSONInteger(c)));
					break;
				}
			}
		}
		
		writeFile();
		return true;
	}
	
	public static void addCount(int id, int add) throws IOException {
		CJSONObject obj = getMainObject().getObject(Integer.toString(id));
		
		List<CJSONItem> items = obj.getValue();
		for(int i = 0; i < items.size(); i++)
			if(items.get(i).getName().equals("base")) {
				items.set(i, new CJSONItem("base", new CJSONInteger(items.get(i).getValue().getInt() + add)));
				break;
			}
		
		writeFile();
	}
	
	public static CJSONObject getObject(int id) {
		CJSONObject obj = getMainObject();
		
		for(CJSONItem item : obj.getValue())
			if(item.getName().equals(Integer.toString(id)))
				return (CJSONObject) item.getValue();
		
		return null;
	}
	
	public static int getBaseQuantity(int id) {
		CJSONObject obj = getObject(id);
		if(obj == null)
			return -1;
		
		return obj.getInt("base");
	}
	
	private static void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE.getFile());
        
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        
        osw.write(QUANTITIES.toString());
        
        osw.flush();
        osw.close();
	}
	
}
