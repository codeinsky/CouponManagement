package couponTests;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import dbConnectionPool.ConnectionPool;

public class LastThread extends Thread {

	@Override
	public void run() {
		
		try {
			sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Random ran = new Random();
		int id = ran.nextInt(100);
		String sql = "INSERT INTO COUPON(id , title) VALUES(" + id + ",'Last one')";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		System.out.println("Last thread is waiting for connection");
		Connection con1 = pool.getConnection();
		try {
			Statement st = con1.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pool.returnConnection(con1);
		System.out.println(currentThread().getName() + "has returned the connection");
		
	}
}
