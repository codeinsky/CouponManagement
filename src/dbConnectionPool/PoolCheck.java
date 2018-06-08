package dbConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import dbConnectionPool.ConnectionPool;

public class PoolCheck implements Runnable {

	@Override
	public void run() {
		
			AddNewEmployee();
	
			
		
	}
	public void AddNewEmployee() {
		Random ran = new Random();
		int id = ran.nextInt(100);
		String sql = "INSERT INTO CUPON VALUES(" + id + ",'0 connections')";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con1 = pool.getConnection();
		try {
			Statement st = con1.createStatement();
			st.executeUpdate(sql);
			System.out.println("Employee added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.returnConnection(con1);
		
	}
	
}
