package sharedModel;

public class NonElectricItem extends Item {

	private String itemType;

	public NonElectricItem(int id, String desc, int qty, double price, int supId, String itemType) {
		super(id, desc, qty, price, supId);
		this.itemType = itemType;
	}

	/**
	 * @Override
	 */
	public String toString() {
		String s = super.toString();
		s = s + "Item Type: %s";
		return String.format(s, this.itemType);
	}
}
