package couponTests;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import dbConnectionPool.ConnectionPool;

public class PoolCheck extends Thread {

	@Override
	public void run() {
		
		
			
		
		Random ran = new Random();
		int id = ran.nextInt(100);
		String sql = "INSERT INTO COUPON(id , title) VALUES(" + id + ",' That is 10')";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con1 = pool.getConnection();
		try {
			Statement st = con1.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.returnConnection(con1);
		}
		
	
}

