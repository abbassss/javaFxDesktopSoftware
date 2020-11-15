package model;
import java.sql.Timestamp;

/**
 * ContactModel class for build , set and get value of class
 * */
public class ContactModel {
	
	private long id;
	private String firstname;
	private String lastname;
	private String street;
	private String street_number;
	private Timestamp birthday;
	private String salutation_name;		
	private int salutation_id;
	private String fullname;
	private String address;
	private SalutationModel salModel = new SalutationModel();
	
	public ContactModel() {}
	
	/**
	 * ContactModel constructor 
	 * @param String
	 * */
	public ContactModel(String sal_name) {

		this.salModel.setName(salutation_name);
	}
	
	/**
	 * ContactModel constructor 
	 * @param id
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * @param Timestamp
	 * @param String
	 * */
	public ContactModel(long id,String firstname, String lastname, String street, String street_number, Timestamp birthday,
			String salutation_name) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.street_number = street_number;
		this.birthday = birthday;
		this.salutation_name = salutation_name;
		this.fullname = firstname+" "+lastname;
		this.address = street+" "+street_number;
	}
	
	/**
	 * ContactModel constructor 
	 * @param id
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * @param Timestamp
	 * @param String
	 * @param String
	 * */
	public ContactModel(long id, String firstname, String lastname, String fullname,String salutation_name,
			String address, Timestamp birthday,String street,String street_number,int salutation_id) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = fullname;
		this.salModel.setName(salutation_name);
		this.address = address;
		this.birthday = birthday;
		this.street = street;
		this.street_number = street_number;
		this.salutation_id = salutation_id;
		
	}
	
	public ContactModel(long id, String firstname, String lastname, String fullname,String salutation_name,
			String address, Timestamp birthday,String street,String street_number) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = fullname;
		this.salModel.setName(salutation_name);
		this.address = address;
		this.birthday = birthday;
		this.street = street;
		this.street_number = street_number;
	}
	
	/**
	 * ContactModel constructor 
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * @param Timestamp
	 * @param salutation_id
	 * */
	public ContactModel(String firstname, String lastname, String street, String street_number, Timestamp birthday,
			int salutation_id) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.street_number = street_number;
		this.birthday = birthday;
		this.salutation_id = salutation_id;
	}		
	
	/**
	 * ContactModel constructor 
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * @param Timestamp
	 * */
	public ContactModel(String firstname, String lastname, String fullname, String salutation_name,String address,Timestamp birthday) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = fullname;
		this.birthday = birthday;
		this.salModel.setName(salutation_name);
		this.address = address;
	}
	
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getSalutation_name() {
		return salutation_name;
	}
	public void setSalutation_name(String salutation_name) {
		this.salutation_name = salutation_name;
	}
	
	public int getSalutation_id() {
		return salutation_id;
	}
	public void setSalutation_id(int salutation_id) {
		this.salutation_id = salutation_id;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public SalutationModel getSalModel() {
		return salModel;
	}

	public void setSalModel(SalutationModel salModel) {
		this.salModel = salModel;
	}
	
	public String getName() {
		return salModel.getName();
	}


}
