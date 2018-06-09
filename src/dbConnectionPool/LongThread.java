package dbConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import dbConnectionPool.ConnectionPool;

public class LongThread extends Thread {

	@Override
	public void run() {
		

		Random ran = new Random();
		int id = ran.nextInt(100);
		String sql = "INSERT INTO CUPON VALUES(" + id + ",' That is 10')";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con1 = pool.getConnection();
		try {
			Statement st = con1.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.returnConnection(con1);
		System.out.println("Thread 10 has returned the connection");
		System.out.println("Now pool has " + pool.ConnectionAmoutCheck());
	}
}


