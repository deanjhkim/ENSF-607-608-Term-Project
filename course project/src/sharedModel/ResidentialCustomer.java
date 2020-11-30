package sharedModel;

import java.io.Serializable;

/**
 * Contains datafields for Residential Customer
 * @author Evan Boerchers and Dean Kim
 *
 */

public class ResidentialCustomer extends Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs residential customer
	 * @param id
	 * @param phoneNumber
	 * @param fName
	 * @param lName
	 * @param address
	 * @param postalCode
	 * @param type
	 */
	public ResidentialCustomer(int id, String phoneNumber, String fName, String lName, String address,
			String postalCode, String type) {
		super(id, phoneNumber, fName, lName, address, postalCode, type);
	}
	
	/**
	 * String representation.
	 * @Override
	 */
	public String toString() {
		return super.toString();
	}
}
