package server.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import sharedModel.*;

public class DBController implements JDBCConnectionInfo {

	private Connection jdbc_connection;
	private Statement statement;

	public DBController() {
		try {
			jdbc_connection = DriverManager.getConnection(CONNECTIONINFO, LOGIN, PASSWORD);
			System.out.println("Connected to: " + CONNECTIONINFO + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Item> getItems() {
		LinkedList<Item> items = new LinkedList<Item>();
		String sql = "SELECT * FROM " + ITEMTABLE;
		ResultSet results;
		try {
			statement = jdbc_connection.createStatement();
			results = statement.executeQuery(sql);
			while(results.next()) {
				if(results.getString("Item_type").equals("Electrical")) {
					Item temp = new ElectricItem(results.getInt("Item_id"),
							results.getString("Item_name"),
							results.getInt("Quantity"),
							results.getDouble("Price"),
							results.getInt("Supplier_id"),
							results.getString("Item_type"),
							results.getString("Power_type"));
					items.add(temp);
				} else {
					Item temp = new NonElectricItem(results.getInt("Item_id"),
							results.getString("Item_name"),
							results.getInt("Quantity"),
							results.getDouble("Price"),
							results.getInt("Supplier_id"),
							results.getString("Item_type"));
					items.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public LinkedList<Supplier> getSuppliers() {
		LinkedList<Supplier> suppliers = new LinkedList<Supplier>();
		String sql = "SELECT * FROM " + SUPPLIERTABLE;
		ResultSet results;
		try {
			statement = jdbc_connection.createStatement();
			results = statement.executeQuery(sql);
			while(results.next()) {
				if(results.getString("Supplier_type").equals("International")) {
					Supplier temp = new InternationalSupplier(results.getInt("Supplier_id"),
							results.getString("Supplier_name"),
							results.getString("Address"),
							results.getString("Sales_contact"),
							results.getString("Supplier_type"),
							results.getDouble("Import_tax"));
					suppliers.add(temp);
				} else {
					Supplier temp = new LocalSupplier(results.getInt("Supplier_id"),
							results.getString("Supplier_name"),
							results.getString("Address"),
							results.getString("Sales_contact"),
							results.getString("Supplier_type"));
					suppliers.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suppliers;
	}
	
	public LinkedList<Customer> getCustomers() {
		LinkedList<Customer> suppliers = new LinkedList<Customer>();
		String sql = "SELECT * FROM " + CUSTOMERTABLE;
		ResultSet results;
		try {
			statement = jdbc_connection.createStatement();
			results = statement.executeQuery(sql);
			while(results.next()) {
				if(results.getString("Customer_type").equals("R")) {
					Customer temp = new ResidentialCustomer(results.getInt("Customer_id"),
							results.getString("Phone_no"),
							results.getString("FirstName"),
							results.getString("LastName"),
							results.getString("Address"),
							results.getString("Postal_code"),
							results.getString("Customer_type"));
					suppliers.add(temp);
				} else {
					Customer temp = new CommercialCustomer(results.getInt("Customer_id"),
							results.getString("Phone_no"),
							results.getString("FirstName"),
							results.getString("LastName"),
							results.getString("Address"),
							results.getString("Postal_code"),
							results.getString("Customer_type"));
					suppliers.add(temp);
					suppliers.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suppliers;
	}
}
