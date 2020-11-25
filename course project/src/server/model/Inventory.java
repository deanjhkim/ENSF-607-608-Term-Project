package server.model;

import java.util.LinkedList;
import java.util.ListIterator;

import sharedModel.Item;

/**
 * Contains data fields and methods for tracking and modifying items in shop
 * inventory.
 * 
 * @author Evan Boerchers
 *
 */
public class Inventory {

	/**
	 * Min quantity of item in stock before it is ordered.
	 */
	private static int MINQTY = 40;

	private LinkedList<Item> items;

	private Order order;

	/**
	 * Consructs inventory of given items.
	 * 
	 * @param items items in inventory.
	 */
	public Inventory(LinkedList<Item> items) {
		this.items = items;
	}

	/**
	 * Returns the item object of the given ID number.
	 * 
	 * @param itemId the items id number.
	 * @return
	 */
	public Item findItem(int itemId) {

		ListIterator<Item> listIterator = items.listIterator();

		while (listIterator.hasNext()) {
			Item temp = listIterator.next();
			if (temp.getId() == itemId)
				return temp;
		}
		return null;
	}

	/**
	 * Returns the item object of the given item name.
	 * 
	 * @param itemName the name of the item.
	 * @return
	 */
	public Item findItem(String itemName) {

		ListIterator<Item> listIterator = items.listIterator();

		while (listIterator.hasNext()) {
			Item temp = listIterator.next();
			if (temp.getDescription().equals(itemName))
				return temp;
		}
		return null;
	}

	/**
	 * Sets pointer for the suppliers object of items in inventory.
	 * 
	 * @param sup the supplier object.
	 */
	public void setSuppliers(Suppliers sup) {

		ListIterator<Item> listIterator = items.listIterator();

		while (listIterator.hasNext()) {
			Item temp = listIterator.next();
			setSupplier(sup, temp);
		}
	}
	
	public void setSupplier(Suppliers sup, Item item) {
		int supId = item.getSupplierId();
		item.setSupplier(sup.findSupplier(supId));
	}

	/**
	 * Returns string representation of all items in inventory.
	 * 
	 * @return
	 * @Override
	 */
	public String toString() {

		ListIterator<Item> listIterator = items.listIterator();

		String s = "The items in inventory are: \n\n";

		while (listIterator.hasNext()) {
			s = s + listIterator.next().toString() + "\n";
		}

		return s;
	}

	/**
	 * Decreases item of given name by 1 and orders item if needed.
	 * @param name the name of the item to decrease.
	 */
	public void decreaseItem(String name) {
		Item item = findItem(name);
		item.setQty(item.getQty() - 1);
		System.out.println("Item decreased\n");
		orderItem(item);
	}

	/**
	 * Creates order for given item if its quantity is below the minimum.
	 * @param item the item to be ordered.
	 */
	public void orderItem(Item item) {
		if (item.getQty() < MINQTY) {
			System.out.println("Generating order... \n");
			generateOrder(item);
		} else {
			System.out.print("Sufficient quantity in stock\n");
		}
	}
	
	public void addItem(Suppliers sup, Item item) {
		items.add(item);
		setSupplier(sup, item);
		
	}
	
	public void removeItem(String name) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getDescription().equals(name)) {
				items.remove(i);
			}
		}
	}

	/**
	 * Creates order for given item.
	 * @param item item ordered.
	 */
	private void generateOrder(Item item) {
		if (this.order == null) {
			this.order = new Order();
		}
		this.order.generateOrderLine(item);
	}

	public Order getOrder() {
		return this.order;
	}
	
	public LinkedList<Item> getItems() {
		return this.items;
	}
}
