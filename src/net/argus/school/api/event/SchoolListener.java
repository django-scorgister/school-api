package net.argus.school.api.event;

import java.util.List;

import net.argus.util.Listener;

public interface SchoolListener extends Listener {
	
	public List<SchoolResetEntry> resetRequested(SchoolEvent e);
	public void reset(SchoolEvent e);

}
