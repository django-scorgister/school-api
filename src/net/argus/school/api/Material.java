package net.argus.school.api;

public class Material {
	
	private String name;
	private int id, baseQuantity;
	
	public Material(String name, int id, int baseQuantity) {
		this.name = name;
		this.id = id;
		this.baseQuantity = baseQuantity;
	}
	
	public Material(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public void setBaseQuantity(int baseQuantity) {
		this.baseQuantity = baseQuantity;
	}
	
	public int getBaseQuantity() {
		return baseQuantity;
	}

}
