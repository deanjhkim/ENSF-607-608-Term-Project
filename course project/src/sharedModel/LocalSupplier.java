package sharedModel;

public class LocalSupplier extends Supplier {

	private String supplierType;

	public LocalSupplier(int id, String name, String address, String contact, String supplierType) {
		super(id, name, address, contact);
		this.supplierType = supplierType;
	}

	/**
	 * Returns a string representation of supplier.
	 * 
	 * @return
	 * @override
	 */
	public String toString() {
		String s = super.toString();
		s = s + "Supplier Type: %s \n";
		return String.format(s, this.supplierType);
	}
}
