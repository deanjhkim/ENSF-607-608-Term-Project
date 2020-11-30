package sharedModel;

import java.io.Serializable;

/**
 * Contains datafields and methods for local supplier
 * 
 * @author Evan Boerchers and Dean Kim
 *
 */

public class LocalSupplier extends Supplier implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * constructs local supplier
	 * @param id
	 * @param name
	 * @param address
	 * @param contact
	 * @param supplierType
	 */
	public LocalSupplier(int id, String name, String address, String contact, String supplierType) {
		super(id, name, address, contact, supplierType);
	}

	/**
	 * Returns a string representation of supplier.
	 * 
	 * @return
	 * @override
	 */
	public String toString() {
		String s = super.toString();
		return s;
	}
}
