package server.model;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

import sharedModel.Item;
import sharedModel.OrderText;

/** 
 * Contains data fields and methods needed for tracking and creating OrderLines of items.
 * @author Evan Boerchers and Dean Kim
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
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.date = sdf.format(dt);
		this.id = generateId();
	}
	
	public Order(int id, String date) {
		this.id = id;
		this.date = date;
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
	public int generateOrderLine(Item item, int code) {
		
		if(orderLines == null) {
			orderLines = new LinkedList<OrderLine>();
		}
		
		OrderLine existing = findOrderLine(item);

		if (existing == null) {
			orderLines.add(new OrderLine(item));
		} else {
			existing.updateOrderQty();
			code = 3;
		}
		return code;
	}

	/**
	 * Checks if item is already on order and returns its OrderLine object if so.
	 * @param item the item to order.
	 * @return
	 */
	public OrderLine findOrderLine(Item item) {

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
	
	public OrderLine findOrderLine(String name) {

		if (orderLines != null) {
			ListIterator<OrderLine> listIterator = orderLines.listIterator();

			while (listIterator.hasNext()) {
				OrderLine temp = listIterator.next();
				if (temp.getItem().getDescription().equals(name)) {
					return temp;
				}
			}
		}
		return null;
	}
	
	public OrderLine getLastOrderLine() {
		return orderLines.getLast();
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setOrderLines(LinkedList<OrderLine> orderLines) {
		this.orderLines = orderLines;
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
