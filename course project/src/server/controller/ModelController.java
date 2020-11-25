package server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import server.model.*;
import sharedModel.*;

public class ModelController implements Runnable {

	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;

	private Shop shop;
	private DBController dbc;

	public ModelController(ObjectInputStream in, ObjectOutputStream out, DBController dbc, Shop shop) {
		this.objectIn = in;
		this.objectOut = out;
		this.dbc = dbc;
		this.shop = shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

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

	public void modifyCustomer(Customer customer) {
		shop.getCustomers().modifyCustomer(customer.getId(), customer.getPhoneNumber(), customer.getfName(),
				customer.getlName(), customer.getAddress(), customer.getPostalCode(), customer.getCustomerType());
	}

	@Override
	public void run() {
		System.out.println("Model running");
		while (true) {

			Message request;

			try {
				// reading request for operation
				request = (Message) objectIn.readObject();
				request.getServerCode();
				System.out.println(request.getMessage());
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
					shop.getInventory().decreaseItem(request.getMessage());
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
					sendCustomerList(shop.getCustomers().getCustomersByLName(request.getMessage()));
					break;

				case 12: // modify customer with given id
					modifyCustomer((Customer) request.getObject());
					break;

				case 13: // Remove customer with given id
					shop.getCustomers().removeCustomer(Integer.parseInt(request.getMessage()));
					break;

				case 14: // Add customer
					shop.getCustomers().addCustomer((Customer) objectIn.readObject());
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
