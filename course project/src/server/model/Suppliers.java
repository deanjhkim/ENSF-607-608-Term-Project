package server.model;


import java.util.LinkedList;
import java.util.ListIterator;

import sharedModel.Supplier;


/**
 * Contains list of all suppliers and methods for managing.
 * @author Evan Boerchers and Dean Kim
 *
 */
public class Suppliers {

	private LinkedList<Supplier> suppliers;

	/**
	 * Constructs Supplier object from supplier list.
	 * @param suppliers supplier list.
	 */
	public Suppliers(LinkedList<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	/**
	 * Returns Supplier object with given ID.
	 * @param supId the supplier ID number.
	 * @return
	 */
	public Supplier findSupplier(int supId) {

		ListIterator<Supplier> listIterator = suppliers.listIterator();

		while (listIterator.hasNext()) {
			Supplier temp = listIterator.next();
			if (temp.getId() == supId)
				return temp;
		}
		return null;
	}

	/**
	 * Returns string representation of all suppliers in supplier list.
	 * @return
	 * @override
	 */
	public String toString() {

		ListIterator<Supplier> listIterator = suppliers.listIterator();

		String s = "The suppliers on record are: \n\n";

		while (listIterator.hasNext()) {
			s = s + listIterator.next().toString() + "\n";
		}

		return s;
	}
	
	public LinkedList<Supplier> getSuppliers() {
		return this.suppliers;
	}
}
