package server.model;

import sharedModel.Item;

public class Shop {
	
	private Inventory inventory;
	
	private Suppliers suppliers;
	
	private Customers customers;
	
	/**
	 * Constructs Shop object with given inventory and supplier lists.
	 * @param inv items list of inventory
	 * @param sup supplier list of suppliers
	 */
	public Shop(Inventory inv, Suppliers sup) {
		this.inventory = inv;
		this.suppliers = sup;
		inventory.setSuppliers(suppliers);
	}
	
	/**
	 * Prints all items in inventory to console.
	 */
	public void listItems() {
		System.out.println(inventory.toString());
	}
	
	/**
	 * Prints all suppliers to console.
	 */
	public void listSuppliers() {
		System.out.println(suppliers.toString());
	}
	
	/**
	 * Returns item with given ID.
	 * @param id the item ID number.
	 * @return
	 */
	public Item findItem(int id) {
		return inventory.findItem(id);
	}
	
	/**
	 * Returns item with given name;
	 * @param description items name.
	 * @return
	 */
	public Item findItem(String description) {
		return inventory.findItem(description);
	}
	
	/**
	 * Returns the shops inventory list.
	 * @return
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
}
