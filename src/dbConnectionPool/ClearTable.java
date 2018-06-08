package dbConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClearTable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ClearTable();
	}
	
	public void ClearTable() {
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
