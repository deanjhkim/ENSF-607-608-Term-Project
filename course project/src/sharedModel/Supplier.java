package sharedModel;
/**
 * Contains the data fields and methods for tracking a shop supplier.
 * @author Evan Boerchers
 *
 */
public class Supplier {
	
	private int id;
	
	private String name;
	
	private String address;
	
	private String contact;
	
	/** Constructs Supplier object and fills all fields.
	 * 
	 * @param id supplier id
	 * @param name supplier name
	 * @param address supplier address
	 * @param contact supplier contact name
	 */
	public Supplier(int id, String name, String address, String contact) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
	}
	
	/**
	 * Returns a string representation of supplier.
	 * @return
	 * @override
	 */
	public String toString() {
		String s = "Supplier ID: %d \nSupplier Name: %s \nSupplier Address: %s \n"
				+ "Supplier Contact: %s\n";
		
		return String.format(s, this.id, this.name, this.address, this.contact);
	}
	
	public String getName() {
		return this.name;
	}

	public int getId() {
		return id;
	}

}
