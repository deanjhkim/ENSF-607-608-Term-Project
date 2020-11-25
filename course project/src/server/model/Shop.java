package server.model;

import java.util.LinkedList;

import sharedModel.*;

public class Shop {
	
	private Inventory inventory;
	
	private Suppliers suppliers;
	
	private Customers customers;
	
	/**
	 * Constructs Shop object with given inventory and supplier lists.
	 * @param inv items list of inventory
	 * @param sup supplier list of suppliers
	 */
	public Shop(Inventory inv, Suppliers sup, Customers customers) {
		this.inventory = inv;
		this.suppliers = sup;
		this.customers = customers;
		inventory.setSuppliers(suppliers);
	}
	
	
	
	public Suppliers getSuppliers() {
		return suppliers;
	}
	
	public Customers getCustomers() {
		return customers;
	}
	
	/**
	 * Returns the shops inventory list.
	 * @return
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
}
	
