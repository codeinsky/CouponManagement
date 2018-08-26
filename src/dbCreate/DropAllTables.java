package dbCreate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import couponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class DropAllTables {

	public static void main(String[] args) throws SQLException, CuponSystemException {
		// TODO Auto-generated method stub
		String[] statements = new String[8];
		statements[0]="DROP TABLE Company ";
		statements[1]="DROP TABLE Customer";
		statements[2]="DROP TABLE Coupon";
		statements[3]="DROP TABLE Customer_Coupon";
		statements[4]="DROP TABLE Company_Coupon";
		statements[5]="DROP TABLE Company_ID";
		statements[6]="DROP TABLE CUSTOMER_ID";
		statements[7]="DROP TABLE COUPON_ID";
		String sql=null;
		for (int i = 0; i < statements.length; i++) {
		sql = statements[i];	
		
		Connection con ;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		con = pool.getConnection();
		Statement st = con.createStatement();
		st.execute(sql);
		pool.returnConnection(con);
		
		
		}

	}

}
