package server.model;

import sharedModel.Item;

/**
 * Contains data fields and methods for tracking the order of a specific item.
 * @author Evan Boerchers
 *
 */
public class OrderLine {
	
	private Item item;
	
	private int qty;
	
	/**
	 * Constructs OrderLine object with quantity needed.
	 * @param item
	 */
	public OrderLine(Item item){
		this.item = item;
		this.qty = 50 - item.getQty();
		System.out.println("New order of " + this.qty + " units created.\n");
	}
	
	public OrderLine(Item item, int qty){
		this.item = item;
		this.qty = qty;
	}
	
	/**
	 * Returns string representation of OrderLine.
	 * @return
	 * @Override
	 */
	public String toString() {
		String s = "Item Description: %s\nAmount Ordered: %d\nSupplier: %s\n";
		return String.format(s, item.getDescription(), this.qty, item.getSupplier().getName());
	}
	
	/** 
	 * Updates order quantity based on quantity of item in inventory.
	 */
	public void updateOrderQty() {
		this.qty = 50 - item.getQty();
		System.out.println("Order updated to " + this.qty + " units.\n");
	}
	
	public int getQty() {
		return this.qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
}
