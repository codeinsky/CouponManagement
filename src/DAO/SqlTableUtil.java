package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import beans.Coupon;
import beans.CouponType;
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
		String sql = "DELETE FROM " + sqlTable + " WHERE " + column + " = ?";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
			throw new CuponSystemException ("Failed to delete needed coupon " ,e );
		}finally {pool.returnConnection(con);}
		
	}	
	
	public static  Collection<Long> getCouponsBelongTo(String getColumn, String sqlTable,String whereColumn , long id) throws CuponSystemException  {
	Collection<Long> couponsList = new HashSet<Long>();
	ConnectionPool pool = ConnectionPool.getConnectionPool();
	Connection con = pool.getConnection();
	try {
		String sql  = "SELECT "+ getColumn +" FROM " +  sqlTable + " WHERE " + whereColumn + " = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, id);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			couponsList.add(rs.getLong(getColumn));
			} }catch (SQLException e) {
				throw new CuponSystemException ("Failed to execute the SQL query ",e );
			} finally {pool.returnConnection(con);}
		return couponsList;
	}
	
	public static Collection<Coupon> GetCouponSelected(long id , String select , String refernce) throws CuponSystemException {
		Collection<Coupon> selectedCoupons = new HashSet<Coupon>();
		String sql = null; 
		switch(select) {
		case "Type" :  sql = "SELECT * FROM COUPON " + 
				"INNER JOIN COMPANY_COUPON " + 
				"ON Coupon.ID=company_coupon.COUPON_ID " + 
				"WHERE TYPE = '" + refernce +"' " + 
				"AND company_id = " + id;
			break;
			
		case "Price":  sql = "SELECT * FROM COUPON " + 
				"INNER JOIN COMPANY_COUPON " + 
				"ON Coupon.ID=company_coupon.COUPON_ID " + 
				"WHERE PRICE < " + refernce + 
				"AND company_id = " + id;
			break;
			
		case "Date" :  sql = "SELECT * FROM COUPON " + 
				"INNER JOIN COMPANY_COUPON " + 
				"ON Coupon.ID=company_coupon.COUPON_ID " + 
				"WHERE END_DATE < " + refernce + 
				"AND company_id = " + id;
			break;
		
		}
		
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
				selectedCoupons.add(new Coupon(
						rs.getLong("ID"),
						rs.getString("TITLE"),
						rs.getDate("START_DATE"),
						rs.getDate("END_DATE"),
						rs.getInt("AMOUNT"),
						CouponType.valueOf(rs.getString("TYPE")),
						rs.getString("MESSAGE"),
						rs.getDouble("PRICE"),
						rs.getString("IMAGE")
						));
			} 
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {pool.returnConnection(con);}
		
		return selectedCoupons;
	}
	
	
}
