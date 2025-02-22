
package client.controller;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import sharedModel.*;

/**
 * Has methods for functionality of GUI, communicates to backend using ClientController object.
 * @authors Evan Boerchers, Dean Kim 
 *
 */
public class ViewController {
	
	/**
	 * The connected input stream from the ClientController.
	 */
	ObjectInputStream objectIn;
	/**
	 * The connected output stream from the ClientController.
	 */
	ObjectOutputStream objectOut;
	/**
	 * The ClientController connected to the backend.
	 */
	private ClientController cc;
	
	/**
	 * Constructor method creates new ClientController object connected to backend.
	 */
	public ViewController () {
		
		
		cc = new ClientController("localhost", 9898);
		this.objectIn = cc.getObjectIn();
		this.objectOut = cc.getObjectOut();
		
	}

	/**
	 * Returns all items in the SQL DB.
	 * @return LinkedList with all Items in the DB
	 */
	public LinkedList<Item> retrieveItems() {
		Message message = new Message();
		message.setServerCode(1);
		LinkedList<Item> items = new LinkedList<Item>();
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			while (true) {
				Object obj = objectIn.readObject();

				if (obj instanceof Message)
					break;

				items.add((Item) obj);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	/**
	 * Retrieves all Suppliers in the DB.
	 * @return LinkedList with all Suppliers in the DB
	 */
	public LinkedList<Supplier> retrieveSuppliers() {
		Message message = new Message();
		message.setServerCode(2);
		LinkedList<Supplier> suppliers = new LinkedList<Supplier>();
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			while (true) {
				Object obj = objectIn.readObject();

				if (obj instanceof Message)
					break;

				suppliers.add((Supplier) obj);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return suppliers;
	}
	
	/**
	 * Searches DB for item by name
	 * @param name the name of the item
	 * @return the Item with the given name
	 */
	public Item retrieveItemByName(String name) {
		Message message = new Message();
		message.setServerCode(3);
		message.setMessage(name);
		Item item = null;
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			item = (Item) objectIn.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	/**
	 * Searches DB for item by ID
	 * @param id the id of the item
	 * @return the Item with the given ID
	 */
	public Item retrieveItemById(String id) {
		Message message = new Message();
		message.setServerCode(4);
		message.setMessage(id);
		Item item = null;
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			item = (Item) objectIn.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	/**
	 * Decreases the quantity of the item by 1.
	 * @param name the name of the item to be decreased
	 */
	public void decreaseItemQuantity(String name) {
		Message message = new Message();
		message.setServerCode(5);
		message.setMessage(name);
		try {
			objectOut.writeObject(message);
			objectOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * adds item to Inventory
	 * @param item item to be added to Inventory
	 */
	public void addItemToInventory(Item item) {
		Message message = new Message();
		message.setServerCode(6);
		message.setObject(item);
		try {
			objectOut.writeObject(message);
			objectOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes item from inventory
	 * @param name the name of the item to remove
	 */
	public void removeItemFromInventory(String name) {
		Message message = new Message();
		message.setServerCode(7);
		message.setMessage(name);
		try {
			objectOut.writeObject(message);
			objectOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves all customers from DB
	 * @return LinkedList containing all customers
	 */
	public LinkedList<Customer> retrieveCustomers() {
		Message message = new Message();
		message.setServerCode(8);
		LinkedList<Customer> customers = new LinkedList<Customer>();
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			while (true) {
				Object obj = objectIn.readObject();

				if (obj instanceof Message)
					break;

				customers.add((Customer) obj);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return customers;
	}
	/**
	 * Searches DB for customer with ID
	 * @param id the ID of the customer
	 * @return customer the customer with the given ID
	 */
	public Customer retrieveCustomerById(String id) {
		Message message = new Message();
		message.setServerCode(9);
		message.setMessage(id);
		Customer customer = null;
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			customer = (Customer) objectIn.readObject();
			System.out.println(customer.toString());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	/**
	 * Searches DB for customers by last name
	 * @param LName the last name to be searched for
	 * @return LinkedList containing all customers with given last name
	 */
	public LinkedList<Customer> retrieveCustomersByLName(String LName) {
		Message message = new Message();
		message.setServerCode(10);
		message.setMessage(LName);
		LinkedList<Customer> customers = new LinkedList<Customer>();
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			while (true) {
				Object obj = objectIn.readObject();

				if (obj instanceof Message)
					break;

				customers.add((Customer) obj);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return customers;
	}
	/**
	 * Returns all customers of a specific type
	 * @param type the specified type
	 * @return customers LinkedList of all customers of that type
	 */
	public LinkedList<Customer> retrieveCustomersByType(String type) {
		Message message = new Message();
		message.setServerCode(11);
		message.setMessage(type);
		LinkedList<Customer> customers = new LinkedList<Customer>();
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			while (true) {
				Object obj = objectIn.readObject();

				if (obj instanceof Message)
					break;

				customers.add((Customer) obj);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	/**
	 * Modifies customer info in the DB
	 * @param customer customer with updated fields
	 */
	public void modifyCustomer(Customer customer) {
		Message message = new Message();
		message.setServerCode(12);
		message.setObject(customer);
		try {
			objectOut.writeObject(message);
			objectOut.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes customer from DB with given ID
	 * @param id the ID for which a customer will be removed
	 */
	public void removeCustomer(String id) {
		Message message = new Message();
		message.setServerCode(13);
		message.setMessage(id);
		try {
			objectOut.writeObject(message);
			objectOut.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a customer to the DB
	 * @param customer customer to be added
	 */
	public void addCustomer(Customer customer) {
		Message message = new Message();
		message.setServerCode(14);
		message.setObject(customer);
		try {
			objectOut.writeObject(message);
			objectOut.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Disconnects from the connection.
	 */
	public void disconnect() {
		Message message = new Message();
		message.setServerCode(15);
		try {
			objectOut.writeObject(message);
			objectOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Run the view controller while the GUI is running.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		
		while(true) {
			
		}

		

	}

	
}