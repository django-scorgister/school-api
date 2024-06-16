package net.argus.school.api.event;

public class SchoolEvent {
	
	private String schoolEntryId;
	
	public SchoolEvent() {}
	
	public SchoolEvent(String schoolEntryId) {
		this.schoolEntryId = schoolEntryId;
	}
	
	public String getSchoolEntryId() {
		return schoolEntryId;
	}

}
