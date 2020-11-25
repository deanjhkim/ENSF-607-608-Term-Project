package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.model.*;
import sharedModel.*;

public class ServerController {
	
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private Socket socket;
	private ExecutorService pool;
	private ServerSocket serverSocket;
	
	private Shop shop;
	private DBController dbc;

	
	public ServerController() {
		try {
			serverSocket = new ServerSocket(9898);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDBC(DBController dbc) {
		this.dbc = dbc;
	}
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
	public void runServer() {
		System.out.println("Server Running!");
		try {
			while (true) {
				socket = serverSocket.accept();
				System.out.println("Client Connection");
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				
				objectOut = new ObjectOutputStream(outputStream);
				objectIn = new ObjectInputStream(inputStream);
				
				ModelController mc = new ModelController(objectIn, objectOut, dbc, shop);
				pool.execute(mc);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			objectIn.close();
			objectOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) { 
		
		// Creating objects
		ServerController serv = new ServerController();
		DBController dbc = new DBController();
		Shop shop = new Shop(new Inventory(dbc.getItems()), new Suppliers(dbc.getSuppliers()), new Customers(dbc.getCustomers()));
		
		// Setting object aggregation
		serv.setShop(shop);
		serv.setDBC(dbc);
		
		serv.runServer();
	
	}
	
	
}
