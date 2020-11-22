DROP DATABASE IF EXISTS shopdb;
CREATE DATABASE shopdb;
USE shopdb;

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier
	(
    Supplier_id int NOT NULL,
    Supplier_name VARCHAR(20) NOT NULL,
    Address VARCHAR(20),
    Sales_contact VARCHAR(15), 
    Supplier_type VARCHAR(15),
    Import_tax float,
    PRIMARY KEY (Supplier_id)
    );

DROP TABLE IF EXISTS item;
CREATE TABLE item
	(
    Item_id int NOT NULL,
    Item_name VARCHAR(20) NOT NULL,
    Quantity int,
    Price float, 
    Item_type VARCHAR(15),
    Power_type VARCHAR(15),
    Supplier_id int,
    PRIMARY KEY (Item_id),
    FOREIGN KEY (Supplier_id) REFERENCES supplier(Supplier_id)
    );
    
DROP TABLE IF EXISTS customer;
CREATE TABLE customer
	(
    Customer_id int NOT NULL,
    Phone_no VARCHAR(20),
    FirstName VARCHAR(15),
    LastName VARCHAR(15),
    Address VARCHAR(20), 
    Postal_code VARCHAR(6),
    Customer_type VARCHAR(15),
    PRIMARY KEY (Customer_id)
    );
    
DROP TABLE IF EXISTS shoporder;
CREATE TABLE shoporder
	(
    Order_id int NOT NULL,
    Order_date DATE,
    PRIMARY KEY (Order_id)
    );
    
DROP TABLE IF EXISTS orderline;
CREATE TABLE orderline
	(
    Order_id int NOT NULL,
    Supplier_id int NOT NULL,
    Item_id int NOT NULL,
    Order_quantity int NOT NULL,
    FOREIGN KEY (Order_id) REFERENCES shoporder(Order_id),
	FOREIGN KEY (Supplier_id) REFERENCES supplier(Supplier_id),
    FOREIGN KEY (Item_id) REFERENCES item(Item_id)
    );
    
DROP TABLE IF EXISTS buys;
CREATE TABLE buys
	(
    Customer_id int NOT NULL,
    Item_id int NOT NULL,
    Quantity int NOT NULL,
    FOREIGN KEY (Customer_id) REFERENCES customer(Customer_id),
    FOREIGN KEY (Item_id) REFERENCES item(Item_id)
    );

