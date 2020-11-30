package sharedModel;

import java.io.Serializable;

/**
 * Used for sending order text through socket
 * NOT IMPLEMENTED
 * @author Evan Boerchers and Dean Kim
 *
 */

public class OrderText implements Serializable {

	/**
	 * User as Order text object 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	
	public OrderText(String text) {
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
}
