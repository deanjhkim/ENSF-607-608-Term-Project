package sharedModel;

public class Customer {

	private int id;
	private String phoneNumber;
	private String fName;
	private String lName;
	private String address;
	private String postalCode;

	public Customer(int id, String phoneNumber, String fName, String lName, String address, String postalCode) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.postalCode = postalCode;
	}
}
