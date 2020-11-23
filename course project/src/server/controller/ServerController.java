package server.controller;

import java.util.LinkedList;

import sharedModel.*;

public class ServerController {
	
	private DBController dbc;
	
	
	
	
	public ServerController() {
	}
	
	public void setDBC(DBController dbc) {
		this.dbc = dbc;
	}

	public static void main(String[] args) {
		
		ServerController serv = new ServerController();
		DBController dbc = new DBController();
		
		serv.setDBC(dbc);
		
		LinkedList<Item> items = new LinkedList<Item>();
		
		items = dbc.getItems();
		
		for(Item item : items) {
			System.out.println(item.toString());
		}
	}
}
