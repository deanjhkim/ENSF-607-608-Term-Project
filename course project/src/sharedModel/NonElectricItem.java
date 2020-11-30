package sharedModel;

import java.io.Serializable;

/**
 * Contains datafields and methods for non electric items
 * @author Evan Boerchers
 *
 */

public class NonElectricItem extends Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs non electric item
	 * @param id
	 * @param desc
	 * @param qty
	 * @param price
	 * @param supId
	 * @param itemType
	 */
	public NonElectricItem(int id, String desc, int qty, double price, int supId, String itemType) {
		super(id, desc, qty, price, supId, itemType);
	}

	/**
	 * String representation of item
	 * @Override
	 */
	public String toString() {
		String s = super.toString();
		return s;
	}
}
