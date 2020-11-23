package sharedModel;

public class CommercialCustomer extends Customer {
	
	private String customerType;

	public CommercialCustomer(int id, String phoneNumber, String fName, String lName, String address,
			String postalCode, String type) {
		super(id, phoneNumber, fName, lName, address, postalCode);
		this.customerType = type;
	}

}
