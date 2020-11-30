package server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import server.model.*;
import sharedModel.*;

/**
 * Manages model and db changes. Runs on a single server thread.
 * @author Evan Boerchers and Dean Kim
 *
 */

public class ModelController implements Runnable {

	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;

	private Shop shop;
	private DBController dbc;

	/**
	 * Creates model controller with streams.
	 * @param in
	 * @param out
	 * @param dbc
	 * @param shop
	 */
	public ModelController(ObjectInputStream in, ObjectOutputStream out, DBController dbc, Shop shop) {
		this.objectIn = in;
		this.objectOut = out;
		this.dbc = dbc;
		this.shop = shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	/**
	 * Sends list of all items
	 * @param list
	 */
	public void sendItemList(LinkedList<Item> list) {
		try {
			for (Item obj : list) {
				objectOut.writeObject(obj);
				objectOut.flush();
			}
			Message message = new Message();
			message.setClientCode(0);
			objectOut.writeObject(message);
			objectOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends list of all suppliers.
	 * @param list
	 */
	public void sendSupplierList(LinkedList<Supplier> list) {
		try {
			for (Supplier obj : list) {
				objectOut.writeObject(obj);
				objectOut.flush();
			}
			Message message = new Message();
			message.setClientCode(0);
			objectOut.writeObject(message);
			objectOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * decrease item qty by name
	 * @param name
	 */
	public synchronized void decreaseItem(String name) {
		int code = shop.getInventory().decreaseItem(name);
		dbc.decreaseItem(name);
		
		// no order was made so no mods needed to db
		if(code == 0) {
			return;
		}
		// orderline added to existing order
		else if (code == 1){
			dbc.addOrderLine(shop.getInventory().getOrder().getLastOrderLine(), 
					shop.getInventory().getOrder().getId());
		}
		// new order and orderline created
		else if (code == 2) {
			dbc.addOrder(shop.getInventory().getOrder());
		}
		
		// existing orderline updated 
		else if (code == 3) {
			dbc.updateOrderLine(shop.getInventory().getOrder().findOrderLine(name), 
					shop.getInventory().getOrder().getId());
		}
	}

	/**
	 * Sends the customer list
	 * @param list
	 */
	public void sendCustomerList(LinkedList<Customer> list) {
		try {
			for (Customer obj : list) {
				objectOut.writeObject(obj);
				objectOut.flush();
			}
			Message message = new Message();
			message.setClientCode(0);
			objectOut.writeObject(message);
			objectOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * removes customer from model and db based on id
	 * @param id
	 */
	public void removeCustomer(int id) {
		shop.getCustomers().removeCustomer(id);
		dbc.removeCustomer(id);
	}
	
	
	/**
	 * Adds customer to model and db.
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		shop.getCustomers().addCustomer(customer);
		dbc.addCustomer(customer);
	}

	/**
	 * Modifies customer information in model and db.
	 * @param customer
	 */
	public synchronized void modifyCustomer(Customer customer) {
		shop.getCustomers().modifyCustomer(customer.getId(), customer.getPhoneNumber(), customer.getfName(),
				customer.getlName(), customer.getAddress(), customer.getPostalCode(), customer.getCustomerType());
		dbc.modifyCustomer(customer);
	}

	/** 
	 * Runs the model 
	 * 
	 */
	@Override
	public void run() {
		System.out.println("Model running");
		while (true) {

			Message request;

			try {
				// reading request for operation
				request = (Message) objectIn.readObject();
				request.getServerCode();
				int code = request.getServerCode();

				switch (code) {

				case 1: // sends item list
					sendItemList(shop.getInventory().getItems());
					break;

				case 2: // sends supplier list
					sendSupplierList(shop.getSuppliers().getSuppliers());
					break;

				case 3: // sends item based on name
					objectOut.writeObject(shop.getInventory().findItem(request.getMessage()));
					break;

				case 4: // sends item based on id
					objectOut.writeObject(shop.getInventory().findItem(Integer.parseInt(request.getMessage())));
					break;

				case 5: // decreases item quantity based on name
					decreaseItem(request.getMessage());	
					break;
					
				case 6: // add item to inventory
					shop.getInventory().addItem(shop.getSuppliers(), (Item) request.getObject());
					break;

				case 7: // remove item from inventory
					shop.getInventory().removeItem(request.getMessage());
					break;

				case 8: // sends customer list
					sendCustomerList(shop.getCustomers().getCustomers());
					break;

				case 9: // sends customer with ID
					objectOut.writeObject(shop.getCustomers().getCustomersByID(Integer.parseInt(request.getMessage())));
					break;

				case 10: // sends customers list with given last name
					sendCustomerList(shop.getCustomers().getCustomersByLName(request.getMessage()));
					break;

				case 11: // sends customers list with given type
					sendCustomerList(shop.getCustomers().getCustomersByType(request.getMessage()));
					break;

				case 12: // modify customer with given id
					modifyCustomer((Customer) request.getObject());
					break;

				case 13: // Remove customer with given id
					removeCustomer(Integer.parseInt(request.getMessage()));
					break;

				case 14: // Add customer
					addCustomer((Customer) request.getObject());
					break;

				case 15: // Client done DC from server.
					System.out.println("User Disconnecting...");
					return;

				default:
					System.out.println("Invalid selection try again!");
					break;

				}

			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
