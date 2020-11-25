package sharedModel;

import java.io.Serializable;

public class NonElectricItem extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonElectricItem(int id, String desc, int qty, double price, int supId, String itemType) {
		super(id, desc, qty, price, supId, itemType);
	}

	/**
	 * @Override
	 */
	public String toString() {
		String s = super.toString();
		return s;
	}
}
