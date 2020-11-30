# DB showing
USE shopdb;

SELECT * FROM supplier;
SELECT * FROM item;
SELECT * FROM customer;
SELECT * FROM orderline;
SELECT * FROM shoporder;
SELECT * FROM buys;

# Basic Retrieval Query
SELECT Item_id, Item_name FROM Item WHERE Quantity > 40;

# Retrieval Query with Ordered Results
SELECT LastName, FirstName FROM customer ORDER BY LastName DESC; 

# Nested Retrieval Query
SELECT Item_id, Item_name FROM item WHERE item_id IN (SELECT Item_id FROM orderline WHERE Order_quantity > 20);

# Retrieval Query using Joined Tables
SELECT I.Item_id, I.Item_name, O.Supplier_id, O.Order_quantity FROM (item AS I JOIN orderline AS O ON I.Item_id = O.Item_id) WHERE Order_quantity < 40;

# Update Operation with Necessary Triggers
SELECT * FROM orderline;
UPDATE shoporder SET Order_id = 20000 WHERE Order_id = 29589;
SELECT * FROM orderline;

# Deletion Operation with Necessary Triggers
DELETE FROM shoporder WHERE Order_id = 20000;
SELECT * FROM orderline;