package sharedModel;

import java.io.Serializable;

/**
 * Contains datafields for commercial customer
 * 
 * @author Evan Boerchers and Dean Kim
 *
 */
public class CommercialCustomer extends Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs commercial customer
	 * 
	 * @param id
	 * @param phoneNumber
	 * @param fName
	 * @param lName
	 * @param address
	 * @param postalCode
	 * @param type
	 */
	public CommercialCustomer(int id, String phoneNumber, String fName, String lName, String address,
			String postalCode, String type) {
		super(id, phoneNumber, fName, lName, address, postalCode, type);
	}
	
	/**
	 * string representation of object
	 * @Override
	 */
	public String toString() {
		return super.toString();
	}
}
