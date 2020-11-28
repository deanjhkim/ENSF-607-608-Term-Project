package server.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import server.model.Inventory;
import server.model.Order;
import server.model.OrderLine;
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
			while (results.next()) {
				if (results.getString("Item_type").equals("Electrical")) {
					Item temp = new ElectricItem(results.getInt("Item_id"), results.getString("Item_name"),
							results.getInt("Quantity"), results.getDouble("Price"), results.getInt("Supplier_id"),
							results.getString("Item_type"), results.getString("Power_type"));
					items.add(temp);
				} else {
					Item temp = new NonElectricItem(results.getInt("Item_id"), results.getString("Item_name"),
							results.getInt("Quantity"), results.getDouble("Price"), results.getInt("Supplier_id"),
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
			while (results.next()) {
				if (results.getString("Supplier_type").equals("International")) {
					Supplier temp = new InternationalSupplier(results.getInt("Supplier_id"),
							results.getString("Supplier_name"), results.getString("Address"),
							results.getString("Sales_contact"), results.getString("Supplier_type"),
							results.getDouble("Import_tax"));
					suppliers.add(temp);
				} else {
					Supplier temp = new LocalSupplier(results.getInt("Supplier_id"), results.getString("Supplier_name"),
							results.getString("Address"), results.getString("Sales_contact"),
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
			while (results.next()) {
				if (results.getString("Customer_type").equals("R")) {
					Customer temp = new ResidentialCustomer(results.getInt("Customer_id"),
							results.getString("Phone_no"), results.getString("FirstName"),
							results.getString("LastName"), results.getString("Address"),
							results.getString("Postal_code"), results.getString("Customer_type"));
					suppliers.add(temp);
				} else {
					Customer temp = new CommercialCustomer(results.getInt("Customer_id"), results.getString("Phone_no"),
							results.getString("FirstName"), results.getString("LastName"), results.getString("Address"),
							results.getString("Postal_code"), results.getString("Customer_type"));
					suppliers.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suppliers;
	}

	public Order getTodaysOrder(Inventory inventory) {
		String sql = "SELECT * FROM " + ORDERTABLE + " WHERE Order_date=?";
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Order order = null;
		ResultSet results;
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setDate(1, Date.valueOf((sdf.format(dt))));
			results = ps.executeQuery();
			results.next();
			order = new Order(results.getInt("Order_id"), results.getDate("Order_date").toString());
			order.setOrderLines(getOrderLines(order.getId(), inventory));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return order;
	}

	public LinkedList<OrderLine> getOrderLines(int orderId, Inventory inventory) {
		String sql = "SELECT * FROM " + ORDERLINETABLE + " WHERE Order_id=?";
		LinkedList<OrderLine> orderLines = new LinkedList<OrderLine>();
		ResultSet results;
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setInt(1, orderId);
			results = ps.executeQuery();
			while (results.next()) {
				OrderLine temp = new OrderLine(inventory.findItem(results.getInt("Item_id")),
						results.getInt("Order_quantity"));
				orderLines.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderLines;
	}

	public void decreaseItem(String name) {
		String sql = "UPDATE " + ITEMTABLE + " SET Quantity = Quantity - 1 WHERE Item_name = ?";
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addCustomer(Customer customer) {
		String sql = "INSERT INTO " + CUSTOMERTABLE
				+ " (Customer_id, Phone_no, FirstName, LastName, Address, Postal_code, Customer_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getPhoneNumber());
			ps.setString(3, customer.getfName());
			ps.setString(4, customer.getlName());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getPostalCode());
			ps.setString(7, customer.getCustomerType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeCustomer(int id) {
		String sql = "DELETE FROM " + CUSTOMERTABLE + " WHERE Customer_id = ?";
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modifyCustomer(Customer customer) {
		String sql = "UPDATE " + CUSTOMERTABLE
				+ " SET Phone_no=?, FirstName=?, LastName=?, Address=?, Postal_code=?, Customer_type=? WHERE Customer_id =?";
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setInt(7, customer.getId());
			ps.setString(1, customer.getPhoneNumber());
			ps.setString(2, customer.getfName());
			ps.setString(3, customer.getlName());
			ps.setString(4, customer.getAddress());
			ps.setString(5, customer.getPostalCode());
			ps.setString(6, customer.getCustomerType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addOrderLine(OrderLine lastOrderLine, int orderId) {
		String sql = "INSERT INTO " + ORDERLINETABLE
				+ " (Order_id, Supplier_id, Item_id, Order_quantity) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setInt(1, orderId);
			ps.setInt(2, lastOrderLine.getItem().getSupplierId());
			ps.setInt(3, lastOrderLine.getItem().getId());
			ps.setInt(4, lastOrderLine.getQty());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addOrder(Order order) {
		String sql = "INSERT INTO " + ORDERTABLE + " (Order_id, Order_date) VALUES (?, ?)";
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setInt(1, order.getId());
			ps.setDate(2, Date.valueOf((order.getDate())));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		addOrderLine(order.getLastOrderLine(), order.getId());
	}

	public void updateOrderLine(OrderLine findOrderLine, int orderId) {
		String sql = "UPDATE " + ORDERLINETABLE + " SET Order_quantity=? WHERE Order_id=? AND Item_id=? AND Supplier_id=? ";
		try {
			PreparedStatement ps = jdbc_connection.prepareStatement(sql);
			ps.setInt(1, findOrderLine.getQty());
			ps.setInt(2, orderId);
			ps.setInt(3, findOrderLine.getItem().getId());
			ps.setInt(4, findOrderLine.getItem().getSupplierId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
