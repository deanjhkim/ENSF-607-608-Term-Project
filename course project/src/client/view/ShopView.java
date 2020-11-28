package client.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import client.controller.*;
import sharedModel.*;

public class ShopView {
	MyListener listener;
	JRadioButton searchToolNameButton;
    JRadioButton searchToolIDButton;
    JButton searchButton;
    JButton listAllButton;
	JButton checkItemQuantityButton;
	JButton decreaseQuantityButton;
	JTextField searchField;
	JTextField itemIDField;
	JTextField itemNameField;
	JTextField quantityField;
	JTextField priceField;
	JTextField itemTypeField;
	JTextField powerTypeField;
	JTextField supplierIDField;
	JTextField decreaseQuantityField;
	ClientController client;
	
	JTextArea textArea;
	DefaultListModel<Item> itemNames;
	JList <Item> itemList;
	JScrollPane scroll;
	JPanel listPanel;
	
	Item item;
	Supplier supplier;
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				String userInput = searchField.getText();
				

				
				
				if (e.getSource() == searchButton && searchToolNameButton.isSelected()) {
					
					itemNames.clear();
					Item temp = client.retrieveItemByName(userInput);
					
					itemNames.addElement(temp);
//						itemList.updateUI();
					itemList.addMouseListener(mouseListener);
//						update();
				} else if (e.getSource() == searchButton && searchToolIDButton.isSelected()) {
			
					itemNames.clear();
					Item temp = client.retrieveItemById(userInput);
					
					itemNames.addElement(temp);
					itemList.addMouseListener(mouseListener);
//						update();
				} 
				
				else if (e.getSource() == listAllButton) {
					itemNames.clear();
					
					LinkedList<Item> allitems = client.retrieveItems();
					
					for (Item temp : allitems) {
						itemNames.addElement(temp);
					}
					
					itemList.addMouseListener(mouseListener);
				
				
				} else if (e.getSource() == decreaseQuantityButton) {
					
	//				String decreaseBy = decreaseQuantityField.getText();
					item = itemList.getSelectedValue();
					client.decreaseItemQuantity(item.getDescription());
					
					itemNames.clear();
					itemList.clearSelection();
					update();
					
				
			} 
		}
		
	}
	
	MouseListener mouseListener = new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
	        if (e.getClickCount() == 1) {


	            item = (Item) itemList.getSelectedValue();
	          
	            itemIDField.setText(Integer.toString(item.getId()));
	            itemNameField.setText(item.getDescription());
	            quantityField.setText(Integer.toString(item.getQty()));
	            priceField.setText(Double.toString(item.getPrice()));
	            itemTypeField.setText(item.getItemType());
//	            powerTypeField.setText(item.getPhoneNumber());
	            supplierIDField.setText(Integer.toString(item.getSupplierId()));


	         }
	    }
	};
	
	private void update() {
		
          
          itemIDField.setText(Integer.toString(item.getId()));
          itemNameField.setText(item.getDescription());
          quantityField.setText(Integer.toString(item.getQty()));
          priceField.setText(Double.toString(item.getPrice()));
          itemTypeField.setText(item.getItemType());
//          powerTypeField.setText(item.getPhoneNumber());
          supplierIDField.setText(Integer.toString(item.getSupplierId()));
	}
	
	private void run() {
		
		client = new ClientController("localhost", 9898);
		JFrame svframe = new JFrame("Inventory Management System");
		svframe.setLayout(new GridLayout());
		JPanel svpanel = new JPanel();
		svpanel.setLayout(new BoxLayout(svpanel, BoxLayout.X_AXIS));
		
		JPanel topPanel = new JPanel();
//		topPanel.setAlignmentY(0f);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
//		GridBagConstraints lpc = new GridBagConstraints();
	    JLabel topTitle = new JLabel("Search Clients");
	    topTitle.setFont(new Font("Verdana",1,20));
//	    leftTitle.setVerticalAlignment(JLabel.TOP);
	    
	    listener = new MyListener();
	    
//	    topPanel.add(topTitle);
	    topPanel.setBorder(new LineBorder(Color.BLACK));
	    
	    
	    searchToolNameButton = new JRadioButton("Tool Name");
	    searchToolIDButton = new JRadioButton("Tool ID");
	    listAllButton = new JButton("List");
		checkItemQuantityButton = new JButton("Client ID");
	    
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		JLabel searchPrompt = new JLabel("Select the item search method:");
		searchPrompt.setFont(new Font("Verdana",1,20));
		topPanel.add(searchPrompt);
		
		ButtonGroup searchOptions = new ButtonGroup();
		searchOptions.add(searchToolNameButton);
		topPanel.add(searchToolNameButton);
		searchOptions.add(searchToolIDButton);
		topPanel.add(searchToolIDButton);
		
		topPanel.add(Box.createRigidArea(new Dimension(15,0)));
		JLabel enterSearchPrompt = new JLabel("Enter the name or ID of the tool:");
		enterSearchPrompt.setFont(new Font("Verdana",1,13));
		topPanel.add(enterSearchPrompt);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));

		searchField = new JTextField();
		searchField.setMaximumSize(new Dimension(300,24));
		topPanel.add(Box.createRigidArea(new Dimension(15,0)));
		topPanel.add(searchField);
		searchField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		searchButton = new JButton("Search: ");
		topPanel.add(searchButton);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
	    
		JLabel listPrompt = new JLabel("List all items in the shop: ");
		listPrompt.setFont(new Font("Verdana",1,20));
		topPanel.add(listPrompt);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		topPanel.add(listAllButton);
		
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		
		checkItemQuantityButton = new JButton("Check item quantity");
		
		JLabel checkItemPrompt = new JLabel("Check the quantity of an item: ");
		checkItemPrompt.setFont(new Font("Verdana",1,20));
//		topPanel.add(checkItemPrompt);
		JLabel checkInstructPrompt = new JLabel("Enter tool name:");
		checkInstructPrompt.setFont(new Font("Verdana",1,13));
//		topPanel.add(checkInstructPrompt);
		JTextField checkItemField = new JTextField();
		checkItemField.setMaximumSize(new Dimension(300,24));
//		topPanel.add(checkItemField);
//		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
//		topPanel.add(checkItemQuantityButton);
		
//		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		decreaseQuantityButton = new JButton("Decrease item quantity");
		
		JLabel decreaseQuantityPrompt = new JLabel("Decrease the quantity of an item: ");
		decreaseQuantityPrompt.setFont(new Font("Verdana",1,20));
		topPanel.add(decreaseQuantityPrompt);
		JLabel decreaseInstructPrompt = new JLabel("Enter decrease quantity:");
		decreaseInstructPrompt.setFont(new Font("Verdana",1,13));
//		topPanel.add(decreaseInstructPrompt);
		decreaseQuantityField = new JTextField();
		decreaseQuantityField.setMaximumSize(new Dimension(300,24));
//		topPanel.add(decreaseQuantityField);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		topPanel.add(decreaseQuantityButton);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		
		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.setBorder(new LineBorder(Color.BLACK));
		itemNames = new DefaultListModel<Item>();
	    itemList = new JList<Item>(itemNames);
	    itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    scroll = new JScrollPane (itemList);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    JLabel displayLabel = new JLabel("Search results: ");
	    displayLabel.setFont(new Font("Verdana",1,20));
	    listPanel.add(displayLabel);
		listPanel.add(scroll);
		topPanel.add(listPanel);
	    
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setPreferredSize(new Dimension(300,2000));
//	    bottomPanel.setAlignmentY(0f);
	    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));	  
		JLabel rightTitle = new JLabel("Search Results");
//		rightTitle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	    rightTitle.setFont(new Font("Verdana",1,15));
	    bottomPanel.add(rightTitle);
	    bottomPanel.setBorder(new LineBorder(Color.BLACK));
	    
	    JPanel itemIDPanel = new JPanel();
	    itemIDPanel.setLayout( new BoxLayout(itemIDPanel, BoxLayout.X_AXIS));
	    itemIDPanel.setMaximumSize(new Dimension(700, 45));
	    itemIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel clientIDlabel = new JLabel("Item ID:");
	    itemIDPanel.add(clientIDlabel);
	    itemIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    itemIDField = new JTextField();
	    itemIDPanel.setPreferredSize(new Dimension(5,15));
	    itemIDPanel.setMaximumSize(new Dimension(300,24));
	    itemIDPanel.add(itemIDField);
	    itemIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    bottomPanel.add(itemIDPanel);
	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel itemNamePanel = new JPanel();
	    itemNamePanel.setLayout( new BoxLayout(itemNamePanel, BoxLayout.X_AXIS));
	    itemNamePanel.setMaximumSize(new Dimension(700, 45));
	    itemNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel firstNameLabel = new JLabel("Item Name:");
	    itemNamePanel.add(firstNameLabel);
	    itemNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    itemNameField = new JTextField();
	    itemNameField.setPreferredSize(new Dimension(5,15));
	    itemNameField.setMaximumSize(new Dimension(300,24));
	    itemNamePanel.add(itemNameField);
	    itemNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    bottomPanel.add(itemNamePanel);
	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel quantityPanel = new JPanel();
	    quantityPanel.setLayout( new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
	    quantityPanel.setMaximumSize(new Dimension(700, 45));
	    quantityPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel lastNameLabel = new JLabel("Quantity:");
	    quantityPanel.add(lastNameLabel);
	    quantityPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    quantityField = new JTextField();
	    quantityField.setPreferredSize(new Dimension(5,15));
	    quantityField.setMaximumSize(new Dimension(300,24));
	    quantityPanel.add(quantityField);
	    quantityPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    bottomPanel.add(quantityPanel);
	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel pricePanel = new JPanel();
	    pricePanel.setLayout( new BoxLayout(pricePanel, BoxLayout.X_AXIS));
	    pricePanel.setMaximumSize(new Dimension(700, 45));
	    pricePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel addressLabel = new JLabel("Price:");
	    pricePanel.add(addressLabel);
	    pricePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    priceField = new JTextField();
	    priceField.setPreferredSize(new Dimension(5,15));
	    priceField.setMaximumSize(new Dimension(300,24));
	    pricePanel.add(priceField);
	    pricePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    bottomPanel.add(pricePanel);
	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel itemTypePanel = new JPanel();
	    itemTypePanel.setLayout( new BoxLayout(itemTypePanel, BoxLayout.X_AXIS));
	    itemTypePanel.setMaximumSize(new Dimension(700, 45));
	    itemTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel postalCodeLabel = new JLabel("Item Type:");
	    itemTypePanel.add(postalCodeLabel);
	    itemTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    itemTypeField = new JTextField();
	    itemTypeField.setPreferredSize(new Dimension(5,15));
	    itemTypeField.setMaximumSize(new Dimension(300,24));
	    itemTypePanel.add(itemTypeField);
	    itemTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    bottomPanel.add(itemTypePanel);
	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
//	    JPanel powerTypePanel = new JPanel();
//	    powerTypePanel.setLayout( new BoxLayout(powerTypePanel, BoxLayout.X_AXIS));
//	    powerTypePanel.setMaximumSize(new Dimension(700, 45));
//	    powerTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
//	    JLabel phoneNumberLabel = new JLabel("Power Type:");
//	    powerTypePanel.add(phoneNumberLabel);
//	    powerTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
//	    powerTypeField = new JTextField();
//	    powerTypeField.setPreferredSize(new Dimension(5,15));
//	    powerTypeField.setMaximumSize(new Dimension(300,24));
//	    powerTypePanel.add(powerTypeField);
//	    powerTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
//	    
//	    bottomPanel.add(powerTypePanel);
//	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));

	    JPanel supplierIDPanel = new JPanel();
	    supplierIDPanel.setLayout( new BoxLayout(supplierIDPanel, BoxLayout.X_AXIS));
	    supplierIDPanel.setMaximumSize(new Dimension(700, 45));
	    supplierIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel supplierIDLabel = new JLabel("Supplier ID:");
	    supplierIDPanel.add(supplierIDLabel);
	    supplierIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    supplierIDField = new JTextField();
	    supplierIDField.setPreferredSize(new Dimension(5,15));
	    supplierIDField.setMaximumSize(new Dimension(300,24));
	    supplierIDPanel.add(supplierIDField);
	    supplierIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    bottomPanel.add(supplierIDPanel);
	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    
        
       
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
       
	    
//        searchToolNameButton.addActionListener(listener);
//        searchToolIDButton.addActionListener(listener);
        searchButton.addActionListener(listener);
        listAllButton.addActionListener(listener);
    	checkItemQuantityButton.addActionListener(listener);
    	decreaseQuantityButton.addActionListener(listener);
	    
	    svpanel.add(topPanel);
	    svpanel.add(bottomPanel);
	    svframe.add(svpanel);
	    svframe.setSize(1200, 800);
	    svframe.setVisible(true);
		
	}
	
	
	public static void main(String [] args ) {
		ShopView sview = new ShopView();
		sview.run();
	
	}



}
