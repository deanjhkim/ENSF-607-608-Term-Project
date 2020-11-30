package sharedModel;

import java.io.Serializable;

/**
 * Contains the data fields and methods for tracking a shop supplier.
 * @author Evan Boerchers and Dean Kim
 *
 */
public class Supplier implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String address;
	
	private String contact;
	
	private String supplierType;
	
	/** Constructs Supplier object and fills all fields.
	 * 
	 * @param id supplier id
	 * @param name supplier name
	 * @param address supplier address
	 * @param contact supplier contact name
	 */
	public Supplier(int id, String name, String address, String contact, String supplierType) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.supplierType = supplierType;
	}
	
	/**
	 * Returns a string representation of supplier.
	 * @return
	 * @override
	 */
	public String toString() {
		String s = "Supplier ID: %d \nSupplier Name: %s \nSupplier Address: %s \n"
				+ "Supplier Contact: %s\nSupplierType %s \n";
		
		return String.format(s, this.id, this.name, this.address, this.contact, this.supplierType);
	}
	
	public String getName() {
		return this.name;
	}

	public int getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
