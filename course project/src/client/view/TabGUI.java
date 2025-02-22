package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import client.controller.ViewController;
import sharedModel.Customer;
import sharedModel.Item;

/**
 * Tabbed GUI with welcome screen that has both Client and Shop views.
 * @authors Evan Boerchers, Dean Kim 
 *
 */
public class TabGUI {
	
	/**
	 * The action listener for the GUI.
	 */
	MyListener listener;
	/**
	 * ViewController class that contains methods for GUI.
	 */
	ViewController view;
	/**
	 * Model list containing current search results.
	 */
	DefaultListModel<Item> itemNames;
	/**
	 * List to display current search results.
	 */
	JList <Item> itemList;
	/**
	 * The search display panel.
	 */
	JScrollPane scroll;
	/**
	 * The panel containing the search list.
	 */
	JPanel listPanel;
	/**
	 * The currently selected item
	 */
	Item item;
	/**
	 * Contains the types of clients.
	 */
	JComboBox<String> clientTypeBox;
	/**
	 * Model containing the customer search results
	 */
	DefaultListModel<Customer> customerNames;
	/**
	 * List displaying the customer search results
	 */
	JList <Customer>custList; 
	/**
	 * The current customer selected
	 */
	Customer customer;
	/**
	 * GUI components.
	 */
	JRadioButton cIDButton;
    JRadioButton lastNameButton;
    JRadioButton clientTypeButton;
    JButton saveButton;
    JButton deleteButton;
    JButton clearButton;
	JButton searchButtonClient;
	JButton clearSearchButton;
	JButton addButton;
	JScrollPane scroll2;
	JTextField searchBar;
	JTextField clientIDField;
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField addressField;
	JTextField postalCodeField;
	JTextField phoneNumberField;
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
	JPanel cvpanel, svpanel, wpanel;

	
	
	/**
	 * Constructor method connects to backend via ViewController. 
	 */
	public TabGUI () {
		view = new ViewController();
		listener = new MyListener();
		
	}
	/**
	 * Overridden action listener class updates GUI based on button selections and text field input.
	 * @author deanj
	 *
	 */
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String userInput = searchField.getText();
			String id = searchBar.getText();
			
			if (e.getSource() == saveButton) {
				customer.setFields(phoneNumberField.getText(), firstNameField.getText(), lastNameField.getText(), addressField.getText(), postalCodeField.getText(), getClientTypeChar());
				view.modifyCustomer(customer);
			} else if (e.getSource() == deleteButton) {
				view.removeCustomer(Integer.toString(customer.getId()));
			} else if (e.getSource() == clearButton) {
				clientIDField.setText("");
				firstNameField.setText("");
				lastNameField.setText("");
				addressField.setText("");
				postalCodeField.setText("");
				phoneNumberField.setText("");
			} else if (e.getSource() == searchButtonClient) {
				id = searchBar.getText();
				System.out.println(id);
				
				if (cIDButton.isSelected()) {
					customer = view.retrieveCustomerById(id);
					customerNames.clear();
					customerNames.addElement(customer);					
					custList.addMouseListener(mouseListenerCustomer);

				} else if(lastNameButton.isSelected()) {
					customerNames.clear();
					LinkedList <Customer> customer = view.retrieveCustomersByLName(id);
					for (Customer temp : customer) {
						customerNames.addElement(temp);	
						
					}
					custList.addMouseListener(mouseListenerCustomer);
					
					
				} else if(clientTypeButton.isSelected()) {
					customerNames.clear();

					LinkedList<Customer> customers3 = view.retrieveCustomersByType(id);
					
					for (Customer temp : customers3) {
						customerNames.addElement(temp);	
						
					}
					custList.addMouseListener(mouseListenerCustomer);
				}
				
			} else if (e.getSource() == clearSearchButton) {
				customerNames.clear();
			} else if (e.getSource() == addButton ) {
				customer = new Customer(Integer.parseInt(clientIDField.getText()), phoneNumberField.getText(), firstNameField.getText(), lastNameField.getText(), addressField.getText(), postalCodeField.getText(), getClientTypeChar());
				view.addCustomer(customer);
			} else if (e.getSource() == searchButton && searchToolNameButton.isSelected()) {
				
				itemNames.clear();
				Item temp = view.retrieveItemByName(userInput);
				
				itemNames.addElement(temp);

				itemList.addMouseListener(mouseListenerItem);

			} else if (e.getSource() == searchButton && searchToolIDButton.isSelected()) {
		
				itemNames.clear();
				Item temp = view.retrieveItemById(userInput);
				
				itemNames.addElement(temp);
				itemList.addMouseListener(mouseListenerItem);
			} 
			
			else if (e.getSource() == listAllButton) {
				itemNames.clear();
				
				LinkedList<Item> allitems = view.retrieveItems();
				
				for (Item temp : allitems) {
					itemNames.addElement(temp);
				}
				
				itemList.addMouseListener(mouseListenerItem);
			
			
			} else if (e.getSource() == decreaseQuantityButton) {
				
				item = itemList.getSelectedValue();
				view.decreaseItemQuantity(item.getDescription());
				
				itemNames.clear();
				itemList.clearSelection();
				update();
				
			
			} 
		}	
		
	}
	
	/**
	 * Checks the selection in the clientTypeBox and translates to the convention used in the SQL database.
	 * @return
	 */
	private String getClientTypeChar() {
		if (clientTypeBox.getSelectedIndex() == 1) {
			return "R";
		}
		else if(clientTypeBox.getSelectedIndex() == 2) {
			return "C";
		} else {
			return "";
		}
		
	}			
	
	/**
	* Updates the info fields of the GUI with the current item selection.
	*/
	private void update() {
		
         
         itemIDField.setText(Integer.toString(item.getId()));
         itemNameField.setText(item.getDescription());
         quantityField.setText(Integer.toString(item.getQty()));
         priceField.setText(Double.toString(item.getPrice()));
         itemTypeField.setText(item.getItemType());
         supplierIDField.setText(Integer.toString(item.getSupplierId()));
	}
	
	/**
	 * Fills information fields in the GUI with the selected items details.
	 */
	
	MouseListener mouseListenerItem = new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {


            item = (Item) itemList.getSelectedValue();
          
            itemIDField.setText(Integer.toString(item.getId()));
            itemNameField.setText(item.getDescription());
            quantityField.setText(Integer.toString(item.getQty()));
            priceField.setText(Double.toString(item.getPrice()));
            itemTypeField.setText(item.getItemType());
            supplierIDField.setText(Integer.toString(item.getSupplierId()));


         }
    	}
	 };
			 
	
	/**
	* Fills information fields in the GUI with the selected customer details.
	*/
	 
	MouseListener mouseListenerCustomer = new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {


            customer = (Customer) custList.getSelectedValue();
          
            clientIDField.setText(Integer.toString(customer.getId()));
			firstNameField.setText(customer.getfName());
			lastNameField.setText(customer.getlName());
			addressField.setText(customer.getAddress());
			postalCodeField.setText(customer.getPostalCode());
			phoneNumberField.setText(customer.getPhoneNumber());
			
			String s = customer.getCustomerType();
			if (s.equalsIgnoreCase("R")) {
				clientTypeBox.setSelectedIndex(1);
			} else if ( s.equalsIgnoreCase("C")) {
				clientTypeBox.setSelectedIndex(2);
			}

         	}
    	}
	};

	/**
	 *  Creates the Client View panel.
	 */
	public void cvPanel( ) {
		cvpanel = new JPanel();
		cvpanel.setLayout(new BoxLayout(cvpanel, BoxLayout.X_AXIS));
		

		
		JPanel leftPanel = new JPanel();
		leftPanel.setAlignmentY(0f);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
	    JLabel leftTitle = new JLabel("Search Clients");
	    leftTitle.setFont(new Font("Verdana",1,20));
	   

	    leftPanel.add(leftTitle);
	    leftPanel.setBorder(new LineBorder(Color.BLACK));
	    
	    cIDButton = new JRadioButton("Client ID");
	    lastNameButton = new JRadioButton("Last Name");
	    clientTypeButton = new JRadioButton("Client Type");
	    
	    ButtonGroup searchOptions = new ButtonGroup();
	    searchOptions.add(cIDButton);
	    leftPanel.add(cIDButton);
	    searchOptions.add(lastNameButton);
	    leftPanel.add(lastNameButton);
	    searchOptions.add(clientTypeButton);
	    leftPanel.add(clientTypeButton);
	   
	    leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    JLabel searchPrompt = new JLabel("Enter the search parameter below:");
	    searchPrompt.setFont(new Font("Verdana",1,20));
	    leftPanel.add(searchPrompt);
	    
	    
	    leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel searchPanel = new JPanel();
	    searchPanel.setLayout( new BoxLayout(searchPanel, BoxLayout.X_AXIS));
	    searchPanel.setMaximumSize(new Dimension(900, 45));
	    
	    searchBar = new JTextField();
	    searchBar.setPreferredSize(new Dimension(5,15));
	    searchBar.setMaximumSize(new Dimension(300,24));
	    searchPanel.add(searchBar);
	    searchPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    searchButtonClient = new JButton("Search"); 

	    searchPanel.add(searchButtonClient);
	    searchPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    clearSearchButton = new JButton("Clear Search"); 
	    searchPanel.add(clearSearchButton);
	    searchPanel.add(Box.createRigidArea(new Dimension(5,0)));
	    leftPanel.add(searchPanel);
	    
        customerNames = new DefaultListModel<Customer>();
        custList = new JList<Customer>(customerNames);
		custList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll2 = new JScrollPane (custList);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        leftPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        
        JLabel displayTitle = new JLabel("Search Results");
        leftPanel.add(displayTitle);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(scroll2);
        scroll2.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        searchPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        leftPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

		
		JPanel rightPanel = new JPanel();
		rightPanel.setAlignmentY(0f);

		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));	  
		JLabel rightTitle = new JLabel("Client Information");
		rightTitle.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		rightPanel.setPreferredSize(new Dimension(400,1700));
	    rightTitle.setFont(new Font("Verdana",1,20));
	    rightPanel.add(rightTitle);
	    rightPanel.setBorder(new LineBorder(Color.BLACK));
		
	    
	    JPanel clientIDPanel = new JPanel();
	    clientIDPanel.setLayout( new BoxLayout(clientIDPanel, BoxLayout.X_AXIS));
	    clientIDPanel.setMaximumSize(new Dimension(700, 45));
	    clientIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel clientIDlabel = new JLabel("Client ID:");
	    clientIDPanel.add(clientIDlabel);
	    clientIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    clientIDField = new JTextField();
	    clientIDField.setPreferredSize(new Dimension(5,15));
	    clientIDField.setMaximumSize(new Dimension(300,24));
	    clientIDPanel.add(clientIDField);
	    clientIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    rightPanel.add(clientIDPanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel firstNamePanel = new JPanel();
	    firstNamePanel.setLayout( new BoxLayout(firstNamePanel, BoxLayout.X_AXIS));
	    firstNamePanel.setMaximumSize(new Dimension(700, 45));
	    firstNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel firstNameLabel = new JLabel("First Name:");
	    firstNamePanel.add(firstNameLabel);
	    firstNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    firstNameField = new JTextField();
	    firstNameField.setPreferredSize(new Dimension(5,15));
	    firstNameField.setMaximumSize(new Dimension(300,24));
	    firstNamePanel.add(firstNameField);
	    firstNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    rightPanel.add(firstNamePanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel lastNamePanel = new JPanel();
	    lastNamePanel.setLayout( new BoxLayout(lastNamePanel, BoxLayout.X_AXIS));
	    lastNamePanel.setMaximumSize(new Dimension(700, 45));
	    lastNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel lastNameLabel = new JLabel("Last Name:");
	    lastNamePanel.add(lastNameLabel);
	    lastNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    lastNameField = new JTextField();
	    lastNameField.setPreferredSize(new Dimension(5,15));
	    lastNameField.setMaximumSize(new Dimension(300,24));
	    lastNamePanel.add(lastNameField);
	    lastNamePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    rightPanel.add(lastNamePanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel addressPanel = new JPanel();
	    addressPanel.setLayout( new BoxLayout(addressPanel, BoxLayout.X_AXIS));
	    addressPanel.setMaximumSize(new Dimension(700, 45));
	    addressPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel addressLabel = new JLabel("Address:");
	    addressPanel.add(addressLabel);
	    addressPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    addressField = new JTextField();
	    addressField.setPreferredSize(new Dimension(5,15));
	    addressField.setMaximumSize(new Dimension(300,24));
	    addressPanel.add(addressField);
	    addressPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    rightPanel.add(addressPanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel postalCodePanel = new JPanel();
	    postalCodePanel.setLayout( new BoxLayout(postalCodePanel, BoxLayout.X_AXIS));
	    postalCodePanel.setMaximumSize(new Dimension(700, 45));
	    postalCodePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel postalCodeLabel = new JLabel("Postal Code:");
	    postalCodePanel.add(postalCodeLabel);
	    postalCodePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    postalCodeField = new JTextField();
	    postalCodeField.setPreferredSize(new Dimension(5,15));
	    postalCodeField.setMaximumSize(new Dimension(300,24));
	    postalCodePanel.add(postalCodeField);
	    postalCodePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    rightPanel.add(postalCodePanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel phoneNumberPanel = new JPanel();
	    phoneNumberPanel.setLayout( new BoxLayout(phoneNumberPanel, BoxLayout.X_AXIS));
	    phoneNumberPanel.setMaximumSize(new Dimension(700, 45));
	    phoneNumberPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel phoneNumberLabel = new JLabel("Phone Number:");
	    phoneNumberPanel.add(phoneNumberLabel);
	    phoneNumberPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    phoneNumberField = new JTextField();
	    phoneNumberField.setPreferredSize(new Dimension(5,15));
	    phoneNumberField.setMaximumSize(new Dimension(300,24));
	    phoneNumberPanel.add(phoneNumberField);
	    phoneNumberPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    rightPanel.add(phoneNumberPanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel clientTypePanel = new JPanel();
	    clientTypePanel.setLayout( new BoxLayout(clientTypePanel, BoxLayout.X_AXIS));
	    clientTypePanel.setMaximumSize(new Dimension(700, 45));
	    clientTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel clientTypeLabel = new JLabel("Client Type:");
	    clientTypePanel.add(clientTypeLabel);
	    clientTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    String[] clientTypeList = new String[] {"All", "Residential", "Commercial"};
	    clientTypeBox = new JComboBox<>(clientTypeList);
	    clientTypePanel.add(clientTypeBox);
	    phoneNumberPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    rightPanel.add(clientTypePanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    JPanel clientSearchPanel = new JPanel();
	    clientSearchPanel.setLayout( new BoxLayout(clientSearchPanel, BoxLayout.X_AXIS));
	    clientSearchPanel.setMaximumSize(new Dimension(700, 45));
	    clientSearchPanel.add(Box.createRigidArea(new Dimension(100,0)));
	    saveButton = new JButton("Save"); 
	    clientSearchPanel.add(saveButton);
	    clientSearchPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    deleteButton = new JButton("Delete"); 
	    clientSearchPanel.add(deleteButton);
	    clientSearchPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    clearButton = new JButton("Clear"); 
	    clientSearchPanel.add(clearButton);
	    clientSearchPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    addButton = new JButton("Add"); 
	    clientSearchPanel.add(addButton);
	    clientSearchPanel.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    rightPanel.add(clientSearchPanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    rightPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	    
		cvpanel.add(leftPanel);
		cvpanel.add(rightPanel);
		
	    saveButton.addActionListener(listener);
	    deleteButton.addActionListener(listener);
	    clearButton.addActionListener(listener);
		searchButtonClient.addActionListener(listener);
		clearSearchButton.addActionListener(listener);
		addButton.addActionListener(listener);
	}
	 
	/**
	 * Creates the Shop View panel
	 */
	public void svPanel () {
		svpanel = new JPanel();
		svpanel.setLayout(new BoxLayout(svpanel, BoxLayout.X_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
	    JLabel topTitle = new JLabel("Search Clients");
	    topTitle.setFont(new Font("Verdana",1,20));
	    
	    topPanel.setBorder(new LineBorder(Color.BLACK));
	    searchToolNameButton = new JRadioButton("Tool Name");
	    searchToolIDButton = new JRadioButton("Tool ID");
	    listAllButton = new JButton("List");
		checkItemQuantityButton = new JButton("Client ID");
	    
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		JLabel searchPromptShop = new JLabel("Select the item search method:");
		searchPromptShop.setFont(new Font("Verdana",1,20));
		topPanel.add(searchPromptShop);
		
		ButtonGroup searchOptions2 = new ButtonGroup();
		searchOptions2.add(searchToolNameButton);
		topPanel.add(searchToolNameButton);
		searchOptions2.add(searchToolIDButton);
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
		JLabel checkInstructPrompt = new JLabel("Enter tool name:");
		checkInstructPrompt.setFont(new Font("Verdana",1,13));
		JTextField checkItemField = new JTextField();
		checkItemField.setMaximumSize(new Dimension(300,24));
		
		decreaseQuantityButton = new JButton("Decrease item quantity");
		JLabel decreaseQuantityPrompt = new JLabel("Decrease the quantity of an item: ");
		decreaseQuantityPrompt.setFont(new Font("Verdana",1,20));
		topPanel.add(decreaseQuantityPrompt);
		JLabel decreaseInstructPrompt = new JLabel("Enter decrease quantity:");
		decreaseInstructPrompt.setFont(new Font("Verdana",1,13));
		decreaseQuantityField = new JTextField();
		decreaseQuantityField.setMaximumSize(new Dimension(300,24));
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
	    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));	  
		JLabel botTitle = new JLabel("Search Results");
		botTitle.setFont(new Font("Verdana",1,15));
	    bottomPanel.add(botTitle);
	    bottomPanel.setBorder(new LineBorder(Color.BLACK));
	    
	    JPanel itemIDPanel = new JPanel();
	    itemIDPanel.setLayout( new BoxLayout(itemIDPanel, BoxLayout.X_AXIS));
	    itemIDPanel.setMaximumSize(new Dimension(700, 45));
	    itemIDPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    JLabel itemIDlabel = new JLabel("Item ID:");
	    itemIDPanel.add(itemIDlabel);
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
	    JLabel itemNameLabel = new JLabel("Item Name:");
	    itemNamePanel.add(itemNameLabel);
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
	    JLabel quantityLabel = new JLabel("Quantity:");
	    quantityPanel.add(quantityLabel);
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
	    JLabel priceLabel = new JLabel("Price:");
	    pricePanel.add(priceLabel);
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
	    JLabel itemTypeLabel = new JLabel("Item Type:");
	    itemTypePanel.add(itemTypeLabel);
	    itemTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    itemTypeField = new JTextField();
	    itemTypeField.setPreferredSize(new Dimension(5,15));
	    itemTypeField.setMaximumSize(new Dimension(300,24));
	    itemTypePanel.add(itemTypeField);
	    itemTypePanel.add(Box.createRigidArea(new Dimension(15,0)));
	    
	    bottomPanel.add(itemTypePanel);
	    bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    

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
       
	   
        searchButton.addActionListener(listener);
        listAllButton.addActionListener(listener);
    	checkItemQuantityButton.addActionListener(listener);
    	decreaseQuantityButton.addActionListener(listener);
	    
	    svpanel.add(topPanel);
	    svpanel.add(bottomPanel);
		
	}
	
	/**
	 * Creates the Welcome Panel.
	 */
	public void wPanel () {
		wpanel = new JPanel();
		wpanel.setLayout(new BoxLayout(wpanel, BoxLayout.X_AXIS));
		wpanel.add(Box.createRigidArea(new Dimension(0,15)));
		
		wpanel.setLayout(new BoxLayout(wpanel, BoxLayout.X_AXIS));
		wpanel.add(Box.createHorizontalGlue());
		JLabel welcomePrompt = new JLabel("Select the Client or Shop view.");
		welcomePrompt.setFont(new Font("Verdana",1,20));
		welcomePrompt.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
		welcomePrompt.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		wpanel.add(welcomePrompt, BorderLayout.CENTER);		
		wpanel.add(Box.createHorizontalGlue());
		
	}
	
	/**
	 * Create the panels and add them to the tabbed frame.
	 */
	public void run() {
	    
	    JFrame frame = new JFrame ("Tool Shop App");
	    JTabbedPane tabPane = new JTabbedPane();
	    
	    wPanel();
	    svPanel();
	    cvPanel();
	    
	    tabPane.add("Home", wpanel);
	    tabPane.add("Shop View", svpanel);
	    tabPane.add("Client View", cvpanel);
	    
	    frame.add(tabPane);
	    frame.setSize(1200,800);
	    frame.setVisible(true);
	    
	}
	
	/**
	 * Construct a TabGUI object and displays it.
	 * @param args
	 */
	public static void main (String [] args) {
		
		TabGUI tg = new TabGUI();
		tg.run();
	}
}
