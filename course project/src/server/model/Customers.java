package server.model;

import java.util.LinkedList;

import sharedModel.Customer;
import sharedModel.Item;

/**
 * Contains list of customer ojbects and methods needed to manage.
 * @author Evan Boerchers and Dean Kim
 *
 */
public class Customers {

	private LinkedList<Customer> customers;

	/**
	 * Constructs customer object with customers.
	 * @param customers
	 */
	public Customers(LinkedList<Customer> customers) {
		this.customers = customers;
	}

	public LinkedList<Customer> getCustomers() {
		return customers;
	}

	/**
	 * Searches for customer by id
	 * @param id
	 * @return
	 */
	public Customer getCustomersByID(int id) {

		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}

	
	/**
	 * Searches for customer by last name
	 * @param LName
	 * @return
	 */
	public LinkedList<Customer> getCustomersByLName(String LName) {
		LinkedList<Customer> temp = new LinkedList<Customer>();
		for (Customer customer : customers) {
			if (customer.getlName().equals(LName)) {
				temp.add(customer);
			}
		}
		return temp;
	}

	/**
	 * gets customers of given type
	 * @param type
	 * @return
	 */
	public LinkedList<Customer> getCustomersByType(String type) {
		LinkedList<Customer> temp = new LinkedList<Customer>();
		for (Customer customer : customers) {
			if (customer.getCustomerType().equals(type)) {
				temp.add(customer);
			}
		}
		return temp;
	}

	/**
	 * adds customer to list
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	/**
	 * removes customer from list
	 * @param id
	 */
	public void removeCustomer(int id) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId() == id) {
				customers.remove(i);
				break;
			}
		}
	}

	
	/**
	 * changes specified customer info
	 * @param id
	 * @param phoneNumber
	 * @param fName
	 * @param lName
	 * @param address
	 * @param postalCode
	 * @param customerType
	 */
	public void modifyCustomer(int id, String phoneNumber, String fName, String lName, String address,
			String postalCode, String customerType) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				customer.setFields(phoneNumber,fName, lName,  address,
					 postalCode,  customerType);
				break;
			}
		}
	}
}
