package dbConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con1 = pool.getConnection();
		// String query1 = "ALTER TABLE cupon DROP COLUMN SURNAME";
		String query2 = "INSERT INTO CUPON(ID) VALUES (1)";
		// Statement st = con1.createStatement();
		//st.executeUpdate(query1);
		pool.returnConnection(con1);
		Connection con2 = pool.getConnection();
		Statement st2 = con2.createStatement();
		pool.returnConnection(con2);
		st2.executeUpdate(query2);
		pool.closeConnections();
		System.out.println("test");
	
	
	}

}
