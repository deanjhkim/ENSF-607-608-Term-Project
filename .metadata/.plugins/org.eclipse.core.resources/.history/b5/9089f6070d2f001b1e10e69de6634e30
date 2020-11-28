package client.model;

import java.util.LinkedList;
import java.util.ListIterator;

import sharedModel.Item;
import sharedModel.OrderText;
import sharedModel.Supplier;

public class LocalShop {

private LinkedList<OrderText> localTickets;
private LinkedList<Item> localItems;
private LinkedList<Supplier> localSuppliers;


public LocalShop(LinkedList<OrderText> localTickets, LinkedList<Item> localItems, LinkedList<Supplier> localSuppliers) {
	this.localTickets = localTickets;
	this.localItems = localItems;
	this.localSuppliers = localSuppliers;
}

/**
 * Find a supplier in the locally stored list of suppliers if present
 * @param supId
 * @return
 */
public Supplier findSupplier(int supId) {

	ListIterator<Supplier> listIterator = localSuppliers.listIterator();

	while (listIterator.hasNext()) {
		Supplier temp = listIterator.next();
		if (temp.getId() == supId)
			return temp;
	}
	return null;
}


/**
 * Returns the item object of the given ID number.
 * 
 * @param itemId the items id number.
 * @return
 */
public Item findItem(int itemId) {

	ListIterator<Item> listIterator = localItems.listIterator();

	while (listIterator.hasNext()) {
		Item temp = listIterator.next();
		if (temp.getId() == itemId)
			return temp;
	}
	return null;
}


/**
 * Returns string representation of all items in inventory.
 * 
 * @return
 * @Override
 */
public String toString() {

	ListIterator<Item> listIterator = localItems.listIterator();

	String s = "The items in inventory are: \n\n";

	while (listIterator.hasNext()) {
		s = s + listIterator.next().toString() + "\n";
	}

	return s;
}

}
