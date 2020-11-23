package sharedModel;

public class InternationalSupplier extends Supplier {

	private String supplierType;
	private double importTax;

	public InternationalSupplier(int id, String name, String address, String contact, String supplierType,
			double importTax) {
		super(id, name, address, contact);
		this.supplierType = supplierType;
		this.importTax = importTax;
	}
	
	/**
	 * Returns a string representation of supplier.
	 * @return
	 * @override
	 */
	public String toString() {
		String s = super.toString();
		s = s + "Supplier Type: %s \nImport Tax: %.2f\n";
		return String.format(s, this.supplierType, this.importTax);
	}

}
