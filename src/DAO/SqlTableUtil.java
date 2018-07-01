package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import couponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class SqlTableUtil {
	
	public static boolean ifExsist(String sqlTable, String Column , String Item) throws CuponSystemException {
		boolean result = false;
		String sql = "SELECT * FROM " + sqlTable + "  WHERE " + Column +" =  ?";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con  = pool.getConnection();
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, Item);
		ResultSet rs;
		rs = st.executeQuery();
		if (rs.next()) {
		result = true;
		}
		else {
			result = false;
		}
		}catch (SQLException e) {
			throw new CuponSystemException ("Failed to calculate the query " , e) ;
		}finally {
			pool.returnConnection(con);
		}
		return result;

}
	public static void removeWhere(String sqlTable , String column , long id ) throws CuponSystemException {
		String sql = "DELET FROM " + sqlTable + "WHERE" + column +" = ?";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to delete neede coupon" ,e );
		}finally {pool.returnConnection(con);}
		
	}	
}
