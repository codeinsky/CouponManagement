package CuponTests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import cuponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class ClearTable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ClearTable();
		} catch (CuponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ClearTable() throws CuponSystemException {
		String sql = "Delete  from cupon";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con1 = pool.getConnection();
		try {
			Statement st = con1.createStatement();
			st.executeUpdate(sql);
			System.out.println("Employee add");
			pool.returnConnection(con1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
