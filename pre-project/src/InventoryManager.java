
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Pre-Project Exercise 


/**
 * 
 * @authors Evan Boerchers, Dean Kim
 * This program allows you to create and manage a store inventory database.
 * It creates a database and datatable, then populates that table with tools from
 *  items.txt.
 */
public class InventoryManager {
	
	/**
	 * JDBC connection from the database.
	 */
	public Connection jdbc_connection;
	/**
	 * Prepared statement object for injecting queries to database.
	 */
	public PreparedStatement statement;
	/**
	 * Parameter information for the database.
	 */
	public String databaseName = "Inventorydb", tableName = "ToolTable", dataFile = "items.txt";
	public String connectionInfo = "jdbc:mysql://localhost:3306/Inventorydb?useSSL=false",  
				  login          = "student",
				  password       = "student12345";
	
	/**
	 * Constructor method makes connection to the database.
	 */
	public InventoryManager()
	{
		try{		
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	
	// Use the jdbc connection to create a new database in MySQL. 
	// (e.g. if you are connected to "jdbc:mysql://localhost:3306", the database will be created at "jdbc:mysql://localhost:3306/InventoryDB")
	public void createDB()
	{
		try {
			String sql = "CREATE DATABASE " + databaseName;
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} 
		catch( SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a data table inside of the database to hold tools
	 */
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" +
				     "ID INT(4) NOT NULL, " +
				     "TOOLNAME VARCHAR(20) NOT NULL, " + 
				     "QUANTITY INT(4) NOT NULL, " + 
				     "PRICE DOUBLE(5,2) NOT NULL, " + 
				     "SUPPLIERID INT(4) NOT NULL, " + 
				     "PRIMARY KEY ( id ))";
		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Removes the data table from the database (and all the data held within it!)
	 */
	public void removeTable()
	{
		String sql = "DROP TABLE " + tableName;
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Removed Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Fills the data table with all the tools from the text file 'items.txt' if found
	 */
	public void fillTable()
	{
		try{
			Scanner sc = new Scanner(new FileReader(dataFile));
			while(sc.hasNext())
			{
				String toolInfo[] = sc.nextLine().split(";");
				addItem( new Item( Integer.parseInt(toolInfo[0]),
						                            toolInfo[1],
						           Integer.parseInt(toolInfo[2]),
						         Double.parseDouble(toolInfo[3]),
						           Integer.parseInt(toolInfo[4])) );
			}
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File " + dataFile + " Not Found!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Add a tool to the database table
	 * @param tool the tool to be added
	 */
	public void addItem(Item tool)
	{
		String sql = "INSERT INTO " + tableName +
				" VALUES ( %" + tool.getId() + "%, '%" + 
				tool.getName() + "%', %" + 
				tool.getQty() + "%, %" + 
				tool.getPrice() + "%, %" + 
				tool.getSupplierId() + "%);";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method should searches database table for a tool matching the toolID parameter and returns that tool.
	 * It should return null if no tools matching that ID are found.
	 * @param toolID the tool to be added
	 * @return the tool with the given ID
	 */
	public Item searchTool(int toolID)
	{
		String sql = "SELECT * FROM " + tableName + " WHERE ID=%" + toolID +"%";
		ResultSet tool;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			tool = statement.executeQuery();
			if(tool.next())
			{
				return new Item(tool.getInt("ID"),
								tool.getString("TOOLNAME"), 
								tool.getInt("QUANTITY"), 
								tool.getDouble("PRICE"), 
								tool.getInt("SUPPLIERID"));
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}

	/**
	 * Prints all the items in the database to console
	 */
	public void printTable()
	{
		try {
			String sql = "SELECT * FROM " + tableName;
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet tools = statement.executeQuery();
			System.out.println("Tools:");
			while(tools.next())
			{
				System.out.println(tools.getInt("ID") + " " + 
								   tools.getString("TOOLNAME") + " " + 
								   tools.getInt("QUANTITY") + " " + 
								   tools.getDouble("PRICE") + " " + 
								   tools.getInt("SUPPLIERID"));
			}
			tools.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method to declare an InventoryManager object, construct and query the DB.
	 * 
	 */
	public static void main(String args[])
	{
		InventoryManager inventory = new InventoryManager();
		
		// You should comment this line out once the first database is created (either here or in MySQL workbench)
//		inventory.createDB();

		inventory.createTable();
		
		System.out.println("\nFilling the table with tools");
		inventory.fillTable();

		System.out.println("Reading all tools from the table:");
		inventory.printTable();

		System.out.println("\nSearching table for tool 1002: should return 'Grommets'");
		int toolID = 1002;
		Item searchResult = inventory.searchTool(toolID);
		if(searchResult == null)
			System.out.println("Search Failed to find a tool matching ID: " + toolID);
		else
			System.out.println("Search Result: " + searchResult.toString());

		System.out.println("\nSearching table for tool 9000: should fail to find a tool");
		toolID = 9000;
		searchResult = inventory.searchTool(toolID);
		if(searchResult == null)
			System.out.println("Search Failed to find a tool matching ID: " + toolID);
		else
			System.out.println("Search Result: " + searchResult.toString());
		
		System.out.println("\nTrying to remove the table");
		inventory.removeTable();
		
		try {
			inventory.statement.close();
			inventory.jdbc_connection.close();
		} 
		catch (SQLException e) { e.printStackTrace(); }
		finally
		{
			System.out.println("\nThe program is finished running");
		}
	}
}