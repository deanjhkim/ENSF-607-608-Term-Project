package client.controller;

import client.model.LocalShop;

/**
 * 
 * @author deanj
 *
 */
public class ModelController {
	
	private LocalShop ls;
	private DocumentManager dm;

	
	public ModelController(LocalShop ls) {
		this.ls = ls;
	}


	public DocumentManager getDm() {
		return dm;
	}


	public void setDm(DocumentManager dm) {
		this.dm = dm;
	}


}
