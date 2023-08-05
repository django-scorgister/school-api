package net.argus.school.api;

public class Material {
	
	private String name;
	private int id, quantity;
	
	public Material(String name, int id, int quantity) {
		this.name = name;
		this.id =id;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

}
