package model;

/**
 * salutationModel (gender type) class to build , set and get its value 
 * */
public class SalutationModel {
	
	private int id;
	private String name;	
	
	public SalutationModel() {}
	
	public SalutationModel(int id , String name) {
		this.id=id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
