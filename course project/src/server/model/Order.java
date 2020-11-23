package server.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import sharedModel.Item;
import sharedModel.OrderText;

/** 
 * Contains data fields and methods needed for tracking and creating OrderLines of items.
 * @author Evan Boerchers
 *
 */
public class Order {

	private int id;

	private String date;

	private LinkedList<OrderLine> orderLines;
	
	private OrderText orderText;

	/** 
	 * Creates Order object setting date and generating ID number.
	 */
	public Order() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		this.date = dtf.format(LocalDate.now());
		this.id = generateId();
	}

	/** 
	 * Generates a random 5 digit ID number.
	 * @return
	 */
	public int generateId() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	/**
	 * Returns string representation of the Order contents.
	 * @return
	 * @Override
	 */
	public String toString() {

		ListIterator<OrderLine> listIterator = orderLines.listIterator();

		String s = "Order ID: " + this.id + "\nDate Ordered: " + this.date + "\n\n";

		while (listIterator.hasNext()) {
			s = s + listIterator.next().toString() + "\n";
		}
		return s;
	}

	/**
	 * Creates order line for given item.
	 * @param item item to order.
	 */
	public void generateOrderLine(Item item) {
		
		if(orderLines == null) {
			orderLines = new LinkedList<OrderLine>();
		}
		
		OrderLine existing = checkOrderLine(item);

		if (existing == null) {
			System.out.println("No order line exists for this item...\n");
			orderLines.add(new OrderLine(item));
		} else {
			System.out.println("Order line exists for this item...\n");
			existing.updateOrderQty();
		}
	}

	/**
	 * Checks if item is already on order and returns its OrderLine object if so.
	 * @param item the item to order.
	 * @return
	 */
	public OrderLine checkOrderLine(Item item) {

		if (orderLines != null) {
			ListIterator<OrderLine> listIterator = orderLines.listIterator();

			while (listIterator.hasNext()) {
				OrderLine temp = listIterator.next();
				if (temp.getItem().equals(item)) {
					return temp;
				}
			}
		}
		return null;
	}
	
	public void generateOrderText() {
		
		if (orderText == null) {
			this.orderText = new OrderText(this.toString());
		} else {
			this.orderText.setText(this.toString());
		}	
	}
	
	public OrderText getOrderText() {
		return this.orderText;
	}

}
