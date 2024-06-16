package net.argus.school.api.event;

import java.util.ArrayList;
import java.util.List;

public class EventSchool {
	
	private List<SchoolListener> listeners = new ArrayList<SchoolListener>();
	
	public static final int RESET = 0;

	public void event(SchoolListener listener, int event, SchoolEvent e) {
		switch (event) {
			case RESET:
				listener.reset(e);
				break;
		}
	}
	
	public List<SchoolResetEntry> resetRequestEvent(SchoolListener listener, SchoolEvent e) {
		return listener.resetRequested(e);
	}
	
	public void invokeEvent(int event, SchoolEvent e) {
		for(SchoolListener listener : listeners)
			event(listener, event, e);
	}
	
	public List<List<SchoolResetEntry>> invokeResetRequestEvent(SchoolEvent e) {
		List<List<SchoolResetEntry>> list = new ArrayList<List<SchoolResetEntry>>();
		for(SchoolListener listener : listeners)
			list.add(resetRequestEvent(listener, e));
		
		return list;
	}
	
	public void addListener(SchoolListener listener) {
		listeners.add(listener);
	}

}
