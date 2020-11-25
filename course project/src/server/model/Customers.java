package server.model;

import java.util.LinkedList;

import sharedModel.Customer;
import sharedModel.Item;

public class Customers {

	private LinkedList<Customer> customers;

	public Customers(LinkedList<Customer> customers) {
		this.customers = customers;
	}

	public LinkedList<Customer> getCustomers() {
		return customers;
	}

	public Customer getCustomersByID(int id) {

		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}

	public LinkedList<Customer> getCustomersByLName(String LName) {
		LinkedList<Customer> temp = new LinkedList<Customer>();
		for (Customer customer : customers) {
			if (customer.getlName().equals(LName)) {
				temp.add(customer);
			}
		}
		return temp;
	}

	public LinkedList<Customer> getCustomersByType(String type) {
		LinkedList<Customer> temp = new LinkedList<Customer>();
		for (Customer customer : customers) {
			if (customer.getCustomerType().equals(type)) {
				temp.add(customer);
			}
		}
		return temp;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void removeCustomer(int id) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId() == id) {
				customers.remove(i);
				break;
			}
		}
	}

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
