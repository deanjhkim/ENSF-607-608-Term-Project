

package client.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ClientView {
	MyListener listener;
	JRadioButton cIDButton;
    JRadioButton lastNameButton;
    JRadioButton clientTypeButton;
    JButton saveButton;
    JButton deleteButton;
    JButton clearButton;
	JButton searchButton;
	JButton clearSearchButton;
	JScrollPane scroll;
	JTextArea textArea;
	
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == saveButton) {
				textArea.selectAll();
				textArea.replaceSelection("");
				textArea.append("Testing");
				textArea.update(textArea.getGraphics());
			} else if (e.getSource() == deleteButton) {
				
			} else if (e.getSource() == clearButton) {
				
			} else if (e.getSource() == searchButton) {
				
			} else if (e.getSource() == clearSearchButton) {
				
			}
		}
		
	}
				
			  
	
	
	public void run() {
		JFrame cvframe = new JFrame("Client Management Screen");
		cvframe.setLayout(new GridLayout());
		JPanel cvpanel = new JPanel();
		cvpanel.setLayout(new BoxLayout(cvpanel, BoxLayout.X_AXIS));
		
		listener = new MyListener();
		
		JPanel leftPanel = new JPanel();
		leftPanel.setAlignmentY(0f);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//		GridBagConstraints lpc = new GridBagConstraints();
	    JLabel leftTitle = new JLabel("Search Clients");
	    leftTitle.setFont(new Font("Verdana",1,20));
//	    leftTitle.setVerticalAlignment(JLabel.TOP);
	    
	    

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
	    
	    JTextField searchBar = new JTextField();
	    searchBar.setPreferredSize(new Dimension(5,15));
	    searchBar.setMaximumSize(new Dimension(300,24));
	    searchPanel.add(searchBar);
	    searchPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    searchButton = new JButton("Search"); 

	    searchPanel.add(searchButton);
	    searchPanel.add(Box.createRigidArea(new Dimension(15,0)));
	    clearSearchButton = new JButton("Clear Search"); 
	    searchPanel.add(clearSearchButton);
	    searchPanel.add(Box.createRigidArea(new Dimension(5,0)));
	    leftPanel.add(searchPanel);
	    
	    textArea = new JTextArea("TEST");
	    textArea.setSize(400,400);    

        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setVisible(true);

        scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        leftPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        
        JLabel displayTitle = new JLabel("Search Results");
        leftPanel.add(displayTitle);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(scroll);
        scroll.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        searchPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        leftPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

    
	    
	   
	
	    
	   
	    
		
		JPanel rightPanel = new JPanel();
		rightPanel.setAlignmentY(0f);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));	  
		JLabel rightTitle = new JLabel("Client Information");
		rightTitle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
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
	    JTextField clientIDField = new JTextField();
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
	    JTextField firstNameField = new JTextField();
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
	    JTextField lastNameField = new JTextField();
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
	    JTextField addressField = new JTextField();
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
	    JTextField postalCodeField = new JTextField();
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
	    JTextField phoneNumberField = new JTextField();
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
	    String[] clientTypeList = new String[] {"All", "Local", "International"};
	    JComboBox<String> clientTypeBox = new JComboBox<>(clientTypeList);
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
	    clientSearchPanel.add(Box.createRigidArea(new Dimension(30,0)));
	    
	    rightPanel.add(clientSearchPanel);
	    rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	    
	    rightPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	    
		cvpanel.add(leftPanel, BorderLayout.PAGE_START);
		cvpanel.add(rightPanel, BorderLayout.PAGE_START);
		
	    saveButton.addActionListener(listener);
	    deleteButton.addActionListener(listener);
	    clearButton.addActionListener(listener);
		searchButton.addActionListener(listener);
		clearSearchButton.addActionListener(listener);
		
		cvframe.add(cvpanel);
		cvframe.setSize(1200, 800);
		cvframe.setVisible(true);
		
	}
	public static void main(String [] args ) {
		ClientView cview = new ClientView();
		cview.run();
	
	}
}
