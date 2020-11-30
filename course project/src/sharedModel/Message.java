package sharedModel;

import java.io.Serializable;

/**
 * Used for messaging between object sockets
 * @author Evan Boerchers and Dean Kim.
 *
 */

public class Message implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private int clientCode;
	
	private int serverCode;

	private String message;
	
	private Object object;
	
	public int getClientCode() {
		return clientCode;
	}

	public void setClientCode(int clientCode) {
		this.clientCode = clientCode;
	}


	public int getServerCode() {
		return serverCode;
	}

	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
