package dbConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// single ton class pool of connections 
public class ConnectionPool {
	
	// declaring all variables 
private Set<Connection> connectionPool= new HashSet<>();
String url = "jdbc:derby://localhost:1527/CuponSystemDB;"; // database URL , driver , port 
public static int availbleConnections; 
public static int ConnectionsInUsage;
private int connectionCount = 10; 
	
private static ConnectionPool instance; // single ton instance of ConnectionPool
// private single ton constructor 
private  ConnectionPool() {
		 
		for (int i = 0; i < connectionCount ; i++) {
			connectionPool.add(createConnection());
			System.out.println("Connection " + i + " is created");
			availbleConnections ++;
		}
	}
// 



	// creating new connection 	
private Connection createConnection() {
	Connection connection = null ; 
	try {Connection conn = DriverManager.getConnection(url); 
		connection = conn; 
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return connection;
	}

	//  Retrieving  connection from the pool  
 public  Connection getConnection() {
	 Connection con = null; 
	 Iterator<Connection> it = connectionPool.iterator();
	 if (it.hasNext()) {
		 con = it.next();
	 }
	 else {try {
		wait();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	 availbleConnections --; 
	 ConnectionsInUsage ++;
	 return con;
 }
 
 	// Returning connection from the pool 
 public void returnConnection(Connection con) {
	 connectionPool.add(con);
	// notify();
	 availbleConnections ++; 
	 ConnectionsInUsage --;
 }
 
 //Closing all connections 
 public void closeConnections() {
	for (Connection con  : connectionPool) {
		try {
			System.out.println("Connection closed");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	System.out.println("All connections are closed");
		}
 
 
 
 //Single ton of a ConnectionPool Class create and return to the main class
 
 public static ConnectionPool getConnectionPool() {
	 if (instance==null) {
		 instance = new ConnectionPool();
	 } 
	 return instance; 
 } 
 


}
