package sharedModel;

import java.io.Serializable;

/** 
 * Contains data fields for electrical items
 * 
 * @author Evan Boerchers and Dean Kim
 *
 */

public class ElectricItem extends Item implements Serializable{

	private static final long serialVersionUID = 1L;
	private String powerType;

	public ElectricItem(int id, String desc, int qty, double price, int supId, String itemType, String powerType) {
		super(id, desc, qty, price, supId, itemType);
		this.powerType = powerType;
	}

	/**
	 * String representation
	 * @Override
	 */
	public String toString() {
		String s = super.toString();
		s = s + "Power Type: %s \n";
		return String.format(s, this.powerType);
	}

}
