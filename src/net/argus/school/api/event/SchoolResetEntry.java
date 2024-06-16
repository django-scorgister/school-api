package net.argus.school.api.event;

public class SchoolResetEntry {
	
	private String id, name, description;
	private boolean defaultValue;
	
	
	public SchoolResetEntry(String id, String name, String description, boolean defaultValue) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}
	
	public SchoolResetEntry(String id, String name, String description) {
		this(id, name, description, false);
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getDefaultValue() {
		return defaultValue;
	}

}
