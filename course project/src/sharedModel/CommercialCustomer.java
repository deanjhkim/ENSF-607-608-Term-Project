package sharedModel;

import java.io.Serializable;

public class CommercialCustomer extends Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommercialCustomer(int id, String phoneNumber, String fName, String lName, String address,
			String postalCode, String type) {
		super(id, phoneNumber, fName, lName, address, postalCode, type);
	}
	
	public String toString() {
		return super.toString();
	}
}
