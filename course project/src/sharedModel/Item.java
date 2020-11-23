package sharedModel;

/**
 * Contains data fields an methods for tracking item in inventory of shop and
 * its supplier.
 * 
 * @author Evan Boerchers
 *
 */
public class Item {

	private int id;

	private String description;

	private int qty;

	private double price;

	private int supplierId;

	private Supplier supplier;

	/**
	 * Constructs Item object and fills fields.
	 * @param id item ID.
	 * @param desc item name.
	 * @param qty item quantity.
	 * @param price item price.
	 * @param supId items supplier ID.
	 */
	public Item(int id, String desc, int qty, double price, int supId) {
		this.id = id;
		this.description = desc;
		this.qty = qty;
		this.price = price;
		this.supplierId = supId;
	}

	/**
	 * Returns string representation of Item.
	 * @Override
	 */
	public String toString() {
		String s = "Item ID: %d \nItem Description: %s \nItem Quantity: %d \n"
				+ "Item Price: %.2f \nItem SupplierID: %d\n";

		return String.format(s, this.id, this.description, this.qty, this.price, this.supplierId);
	}
	
	/**
	 * Decreases item quantity by one.
	 */
	public void decreaseQty() {
		this.qty--;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public int getSupplierId() {
		return supplierId;
	}


	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
