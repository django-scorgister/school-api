package net.argus.school.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.argus.cjson.Array;
import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONValue;

public class IdRegister {
	
	private List<Integer> ids = new ArrayList<Integer>();
	
	public IdRegister(Array array) {
		for(CJSONValue val : array.getArray()) {
			CJSONObject obj = (CJSONObject) val;
			
			ids.add(obj.getInt("id"));
		}
	}
	
	public int genId() {
		Random rand = new Random();
		int  id = 0;
		
		do
			id = rand.nextInt();
		while(ids.contains(id));
		
		ids.add(id);
		
		return id;
	}
	
	public boolean removeId(int id) {
		return ids.remove(new Integer(id));
	}

}
