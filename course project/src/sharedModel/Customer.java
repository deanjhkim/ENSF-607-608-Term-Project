package sharedModel;

import java.io.Serializable;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String phoneNumber;
	private String fName;
	private String lName;
	private String address;
	private String postalCode;
	private String customerType;

	public Customer(int id, String phoneNumber, String fName, String lName, String address, String postalCode,
			String customerType) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.postalCode = postalCode;
		this.customerType = customerType;
	}

	public void setFields(String phoneNumber, String fName, String lName, String address, String postalCode,
			String customerType) {
		this.phoneNumber = phoneNumber;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.postalCode = postalCode;
		this.customerType = customerType;
	}

	public String toString() {
//		String s = "Customer ID: %d \nFirst Name: %s \nLast Name: %s \n"
//				+ "Phone Number: %s \nAddress: %s \nPostal Code: %s\nCustomer Type: %s\n";
//
//		return String.format(s, this.id, this.fName, this.lName, this.phoneNumber, this.address, this.postalCode,
//				this.customerType);
		String s = "Customer ID: %d \t, First Name: %s \t, Last Name: %s \t, Customer Type: %s";
		return String.format(s, this.id, this.fName, this.lName, this.customerType);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setCustomerType(String type) {
		this.customerType = type;
	}

	public String getCustomerType() {
		return this.customerType;
	}

}
