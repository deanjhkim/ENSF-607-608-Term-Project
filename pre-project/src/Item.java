

/**
 * Contains data fields an methods for tracking item in inventory of shop and
 * its supplier.
 * 
 * @author Evan Boerchers
 *
 */
public class Item {

	private int id;

	private String name;

	private int qty;

	private double price;

	private int supplierId;


	/**
	 * Constructs Item object and fills fields.
	 * @param id item ID.
	 * @param desc item name.
	 * @param qty item quantity.
	 * @param price item price.
	 * @param supId items supplier ID.
	 */
	public Item(int id, String name, int qty, double price, int supId) {
		this.id = id;
		this.name = name;
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
				+ "Item Price: %.2f \nItem SupplierID: %d\n ";

		return String.format(s, this.id, this.name, this.qty, this.price, this.supplierId);
	}
	
	/**
	 * Decreases item quantity by one.
	 */
	public void decreaseQty() {
		this.qty--;
	}

	public String getName() {
		return name;
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
	
	public double getPrice() {
		return price;
	}

}
