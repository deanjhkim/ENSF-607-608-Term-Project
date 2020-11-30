package sharedModel;

import java.io.Serializable;

/**
 * Contains datafields for internation supplier
 * @author Evan Boerchers and Dean Kim
 *
 */
public class InternationalSupplier extends Supplier implements Serializable {


	private static final long serialVersionUID = 1L;
	private double importTax;

	/**
	 * Constructs international supplier
	 * @param id
	 * @param name
	 * @param address
	 * @param contact
	 * @param supplierType
	 * @param importTax
	 */
	public InternationalSupplier(int id, String name, String address, String contact, String supplierType,
			double importTax) {
		super(id, name, address, contact, supplierType);
		this.importTax = importTax;
	}
	
	/**
	 * Returns a string representation of supplier.
	 * @return
	 * @override
	 */
	public String toString() {
		String s = super.toString();
		s = s + "Import Tax: %.2f\n";
		return String.format(s, this.importTax);
	}

	public double getImportTax() {
		return importTax;
	}

	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}

}
