package sharedModel;

import java.io.Serializable;

public class ResidentialCustomer extends Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResidentialCustomer(int id, String phoneNumber, String fName, String lName, String address,
			String postalCode, String type) {
		super(id, phoneNumber, fName, lName, address, postalCode, type);
	}
	
	public String toString() {
		return super.toString();
	}
}
