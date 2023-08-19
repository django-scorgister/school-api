package net.argus.school.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import net.argus.cjson.Array;
import net.argus.cjson.CJSON;
import net.argus.cjson.CJSONItem;
import net.argus.cjson.CJSONParser;
import net.argus.cjson.value.CJSONInteger;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONString;
import net.argus.cjson.value.CJSONValue;
import net.argus.file.CJSONFile;
import net.argus.instance.Instance;

public class Students {
	
	public static final CJSONFile FILE = new CJSONFile("students", "", Instance.SYSTEM);
	public static final CJSON STUDENTS = CJSONParser.getCJSON(FILE);
	
	public static final IdRegister ID_REGISTER = new IdRegister(STUDENTS.getArray("students"));
	
	
	public static CJSONValue getStudentsArray() {
		return STUDENTS.getValue("students");
	}
	
	public static CJSONObject getStudent(int id) throws IOException {
		Array array = STUDENTS.getArray("students");
		for(CJSONValue val : array.getArray()) {
			CJSONObject obj = (CJSONObject) val;
			if(obj.getInt("id") == id) {
				return obj;
			}
		}
		return null;
	}
	
	public synchronized static void addStudent(String name) throws IOException {
		CJSONObject obj = new CJSONObject();

		obj.addItem("name", new CJSONString(name));
		obj.addItem("id", new CJSONInteger(ID_REGISTER.genId()));
		
		STUDENTS.getArray("students").addValue(obj);

		writeFile();
	}
	
	public synchronized static boolean updateStudentName(int id, String name) throws IOException {
		Array array = STUDENTS.getArray("students");
		for(CJSONValue val : array.getArray()) {
			CJSONObject obj = (CJSONObject) val;
			if(obj.getInt("id") == id) {
				List<CJSONItem> items = obj.getValue();
				int index = -1;
				for(CJSONItem item : items) {
					index ++;
					if(item.getName().equals("name"))
						break;
				}
				items.set(index, new CJSONItem("name", new CJSONString(name)));
				
				writeFile();
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean removeStudent(int id) throws IOException {
		Array array = STUDENTS.getArray("students");
		for(CJSONValue val : array.getArray()) {
			CJSONObject obj = (CJSONObject) val;
			if(obj.getInt("id") == id) {
				List<CJSONValue> vals = array.getArray();
				vals.remove(val);
				array.setArray(vals);
				
				ID_REGISTER.removeId(id);
				
				writeFile();
				return true;
			}
		}
		return false;
	}
	
	private static void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE.getFile());
        
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        
        osw.write(STUDENTS.toString());
        
        osw.flush();
        osw.close();
	}

}
