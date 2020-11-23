package server.model;

import java.util.LinkedList;

import sharedModel.Customer;

public class Customers {

	private LinkedList<Customer> customers;
	
	public Customers(LinkedList<Customer> customers) {
		this.customers = customers;
	}
}
