package server.controller;

public interface JDBCConnectionInfo {
	
	static final String CONNECTIONINFO = "jdbc:mysql://localhost:3306/shopdb?useSSL=false";
	static final String LOGIN = "student";
	static final String PASSWORD = "student12345";

	static final String ITEMTABLE = "item";
	static final String SUPPLIERTABLE = "supplier";
	static final String ORDERTABLE = "shoporder";
	static final String ORDERLINETABLE = "orderline";
	static final String CUSTOMERTABLE = "customer";
	static final String BUYSTABLE = "buys";
}
