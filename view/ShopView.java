package client.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import client.view.ClientView.MyListener;


public class ShopView {
	MyListener listener;
	JRadioButton searchToolNameButton;
    JRadioButton searchToolIDButton;
    JButton searchButton;
    JButton listAllButton;
	JButton checkItemQuantityButton;
	JButton decreaseQuantityButton;
	
	JTextArea textArea;
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == searchToolNameButton) {
				textArea.selectAll();
				textArea.replaceSelection("");
				textArea.append("Testing");
				textArea.update(textArea.getGraphics());
			} else if (e.getSource() == searchToolIDButton) {
				
			} else if (e.getSource() == searchButton) {
				
			} else if (e.getSource() == listAllButton) {
				
			} else if (e.getSource() == checkItemQuantityButton) {
				
			} else if (e.getSource() == decreaseQuantityButton) {
				
			} 
		}
		
	}
	
	private void run() {
		
		JFrame svframe = new JFrame("Inventory Management System");
		svframe.setLayout(new GridLayout());
		JPanel svpanel = new JPanel();
		svpanel.setLayout(new BoxLayout(svpanel, BoxLayout.X_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setAlignmentY(0f);
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

		JTextField searchField = new JTextField();
		searchField.setMaximumSize(new Dimension(300,24));
		topPanel.add(searchField);
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
		topPanel.add(checkItemPrompt);
		JLabel checkInstructPrompt = new JLabel("Enter tool name:");
		checkInstructPrompt.setFont(new Font("Verdana",1,13));
		topPanel.add(checkInstructPrompt);
		JTextField checkItemField = new JTextField();
		checkItemField.setMaximumSize(new Dimension(300,24));
		topPanel.add(checkItemField);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		topPanel.add(checkItemQuantityButton);
		
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		decreaseQuantityButton = new JButton("Decrease item quantity");
		
		JLabel decreaseQuantityPrompt = new JLabel("Decrease the quantity of an item: ");
		decreaseQuantityPrompt.setFont(new Font("Verdana",1,20));
		topPanel.add(decreaseQuantityPrompt);
		JLabel decreaseInstructPrompt = new JLabel("Enter decrease quantity:");
		decreaseInstructPrompt.setFont(new Font("Verdana",1,13));
		topPanel.add(decreaseInstructPrompt);
		JTextField decreaseQuantityField = new JTextField();
		decreaseQuantityField.setMaximumSize(new Dimension(300,24));
		topPanel.add(decreaseQuantityField);
		topPanel.add(Box.createRigidArea(new Dimension(0,15)));
		topPanel.add(decreaseQuantityButton);
		
	    
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setAlignmentY(0f);
	    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));	  
		JLabel rightTitle = new JLabel("Search Results");
//		rightTitle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	    rightTitle.setFont(new Font("Verdana",1,15));
	    bottomPanel.add(rightTitle);
	    bottomPanel.setBorder(new LineBorder(Color.BLACK));
	    
	    textArea = new JTextArea("TEST");
	    textArea.setSize(300,500);    

        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setVisible(true);

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
       
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        bottomPanel.add(scroll);
	    
        searchToolNameButton.addActionListener(listener);
        searchToolIDButton.addActionListener(listener);
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
