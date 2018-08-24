package dbConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import couponSystemException.CuponSystemException;

public class ConnectionPool {
	private Set<Connection> connectionPool = new HashSet<>();
	private Set<Connection> poolBackUP = connectionPool; // new reference for connection pool close
	String url = "jdbc:derby://localhost:1527/CuponSystemDB;"; // database URL , driver , port
	private int connectionCount = 10;
	private static ConnectionPool instance; // single ton instance of ConnectionPool
	// private single ton constructor

	private ConnectionPool() {

		for (int i = 0; i < connectionCount; i++) {
			try {
				connectionPool.add(createConnection());
			} catch (CuponSystemException e) {
			}
		}
	}

	// CREATING NEW CONNECTION
	private Connection createConnection() throws CuponSystemException {
		Connection connection = null;
		try {
			Connection conn = DriverManager.getConnection(url);
			connection = conn;
		} catch (SQLException e) {
			throw new CuponSystemException("DataBase connection initialization failed  ", e);
		}
		return connection;
	}

	// RETRIVING CONNCTION FROM THE POOL
	public synchronized Connection getConnection() throws CuponSystemException {
		try {
			while (connectionPool.isEmpty()) {
				// System.out.println("No connection left , wait"); - used for Connection pool
				// checks
				wait();
			}
		} catch (InterruptedException e) {
			throw new CuponSystemException("Data Base Connection Manager failed", e);
		}
		Iterator<Connection> it = connectionPool.iterator();
		Connection con = it.next();
		connectionPool.remove(con);
		System.out.println(connectionPool.size() + " Connections left in the Pool");
		return con;
	}

	// RETURNING CONNCTION BACK TO THE POOL
	public synchronized void returnConnection(Connection con) {
		connectionPool.add(con);
		notifyAll();
		System.out.println("There is " + connectionPool.size() + " after returning  ");
	}

	public int connectionAmoutCheck() {
		return connectionPool.size();
	}

	// CLOSEING ALL CONNECTIONS
	public void closeConnections() throws CuponSystemException {
		if (connectionPool.size() < 10) {
			System.out.println("Please do not shut down, data still transfering with data-base");
			try {
				System.out.println("Ten seconds delay to let all connection finish transfer data ");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new CuponSystemException("Connection pool shut down failed", e);
			}
			for (Connection BuCon : poolBackUP) {
				try {
					BuCon.close();
				} catch (SQLException e) {
					throw new CuponSystemException("Connection close failed", e);
				}
			}
		} else {
			for (Connection con : connectionPool) {
				try {
					System.out.println("Connection closed");
					con.close();
				} catch (SQLException e) {
					throw new CuponSystemException("Connection close failed", e);
				}
			}
			System.out.println("All connections are closed");
		}
	}

	// Single ton of a ConnectionPool Class create and return to the main class

	public static ConnectionPool getConnectionPool() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
}
