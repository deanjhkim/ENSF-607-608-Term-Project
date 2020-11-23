package sharedModel;

public class ResidentialCustomer extends Customer {
	
	private String customerType;

	public ResidentialCustomer(int id, String phoneNumber, String fName, String lName, String address,
			String postalCode, String type) {
		super(id, phoneNumber, fName, lName, address, postalCode);
		this.customerType = type;
	}

}
