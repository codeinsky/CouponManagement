package dbConnectionPool;

import java.sql.Connection;

public class ConTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		System.out.println(ConnectionPool.availbleConnections);
		System.out.println(ConnectionPool.ConnectionsInUsage);
		Connection con = pool.getConnection();
		System.out.println(ConnectionPool.availbleConnections);
		System.out.println(ConnectionPool.ConnectionsInUsage);
		pool.returnConnection(con);
		System.out.println(ConnectionPool.availbleConnections);
		System.out.println(ConnectionPool.ConnectionsInUsage);
		pool.closeConnections();
		System.out.println("Done");
	
	}

}
