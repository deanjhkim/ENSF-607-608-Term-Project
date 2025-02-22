package testclient;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Scanner;

import sharedModel.*;

public class TestClient {

	Socket socket;
	ObjectInputStream objectIn;
	ObjectOutputStream objectOut;
	Scanner scan;

	public TestClient(String serverName, int portNumber) {
		try {
			scan = new Scanner(System.in);
			socket = new Socket(serverName, portNumber);
			// keyboard input stream
			System.out.println("Connected to Server!");
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			objectOut = new ObjectOutputStream(outputStream);
			objectIn = new ObjectInputStream(inputStream);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prints command menu to console.
	 */
	public void printMenu() {
		System.out.println("Choose one of the following options: ");
		System.out.println("1. Retrieve list of Items.");
		System.out.println("2. Retrieve list of Suppliers");
		System.out.println("3. Retrieve item by name.");
		System.out.println("4. Retrieve item by id.");
		System.out.println("5. Decrease item quantity by name.");
		System.out.println("6. Add Item to Inventory.");
		System.out.println("7. Delete Item from Inventory.");
		System.out.println("8. Retreive list of Customers.");
		System.out.println("9. Retreive customer by ID.");
		System.out.println("10. Retreive list of customers by LastName.");
		System.out.println("11. Retreive list of customers by Type.");
		System.out.println("12. Modify customer with ID.");
		System.out.println("13. Remove customer.");
		System.out.println("14. Add customer.");
		System.out.println("15. Quit.");
		System.out.println();
		System.out.println("Enter the number of your selection: ");
	}

	/**
	 * runs command prompt interface.
	 */
	public void menu() {

		while (true) {

			printMenu();

			int choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {

			case 1: // get item list
				LinkedList<Item> items = retrieveItems();

				System.out.println("Printing Items");
				for (Item item : items) {
					System.out.println(item);
				}
				break;

			case 2: // get supplier list
				LinkedList<Supplier> suppliers = retrieveSuppliers();

				System.out.println("Printing Suppliers");
				for (Supplier supplier : suppliers) {
					System.out.println(supplier);
				}
				break;

			case 3: // get item by name
				Item item = retrieveItemByName("Googly Eyes");

				System.out.println(item);
				break;

			case 4: // get item by id
				Item item2 = retrieveItemById("1000");

				System.out.println(item2);
				break;

			case 5: // decrease item quantity with name
				decreaseItemQuantity("Wing Bats");
				break;

			case 6: // add item
				Item itemNew = new ElectricItem(6000, "Coco puffs", 21, 21.2, 8010, "Electrical", "DC");
				addItemToInventory(itemNew);
				break;

			case 7: // remove item with name
				removeItemFromInventory("Handy Pandies");
				break;

			case 8: // get customers
				LinkedList<Customer> customers = retrieveCustomers();

				System.out.println("Printing Customers");
				for (Customer customer : customers) {
					System.out.println(customer);
				}
				break;

			case 9: // get customer with id
				Customer customer = retrieveCustomerById("1024");
				System.out.println(customer);
				break;

			case 10: // get customer list with given last name
				LinkedList<Customer> customers2 = retrieveCustomersByLName("Smith");
				for (Customer customer2 : customers2) {
					System.out.println(customer2);
				}
				break;

			case 11: // get customer list with given type
				LinkedList<Customer> customers3 = retrieveCustomersByType("C");
				for (Customer customer3 : customers3) {
					System.out.println(customer3);
				}
				break;
				
			case 12: // modify customer with given ID
				Customer customer4 = new Customer(1020, "number", "John", "Jim", "address", "TSDWSD", "R");
				modifyCustomer(customer4);
				break;

			case 13: // remove a customer based on id
				removeCustomer("1023");
				break;

			case 14: // add a customer
				Customer customer5 = new Customer(1029, "number", "John", "Jim", "address", "TSDWSD", "R");
				addCustomer(customer5);
				break;

			case 15: // DC from server end application
				disconnect();
				return;

			default:
				System.out.println("Invalid selection try again!");
				break;
			}
		}
	}

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

	public Customer retrieveCustomerById(String id) {
		Message message = new Message();
		message.setServerCode(9);
		message.setMessage(id);
		Customer customer = null;
		try {
			objectOut.writeObject(message);
			objectOut.flush();
			customer = (Customer) objectIn.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return customer;
	}

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

	public static void main(String[] args) throws IOException {
		TestClient client = new TestClient("localhost", 9898);

		client.menu();

	}
}