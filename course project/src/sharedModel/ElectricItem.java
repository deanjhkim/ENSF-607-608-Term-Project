package sharedModel;

public class ElectricItem extends Item {

	private String itemType;
	private String powerType;

	public ElectricItem(int id, String desc, int qty, double price, int supId, String itemType, String powerType) {
		super(id, desc, qty, price, supId);
		this.itemType = itemType;
		this.powerType = powerType;
	}

	/**
	 * @Override
	 */
	public String toString() {
		String s = super.toString();
		s = s + "Item Type: %s \nPower Type: %s \n";
		return String.format(s, this.itemType, this.powerType);
	}

}
