package sharedModel;

import java.io.Serializable;

public class OrderText implements Serializable {

	/**
	 * 
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
