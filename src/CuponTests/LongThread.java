package CuponTests;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import cuponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class LongThread extends Thread {

	@Override
	public void run() {
		

		Random ran = new Random();
		int id = ran.nextInt(100);
		String sql = "INSERT INTO COUPON(id , title) VALUES(" + id + ",' That is 10')";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con1 = null;
		try {
			con1 = pool.getConnection();
			Statement st = con1.createStatement();
			st.executeUpdate(sql);
		} catch (CuponSystemException | SQLException e1) {
			
		}
		
		
	
		pool.returnConnection(con1);
		System.out.println("Thread 10 has returned the connection");
		System.out.println("Now pool has " + pool.ConnectionAmoutCheck());
	}
}


