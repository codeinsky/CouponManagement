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
import facades.CompanyFacade;
import facades.CustomerFacade;
import facades.Facade;

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
			
		case "customerCouponsByPrice" :  sql = "SELECT * FROM COUPON " + 
				"INNER JOIN CUSTOMER_COUPON " + 
				"ON Coupon.ID=customer_coupon.COUPON_ID " + 
				"WHERE PRICE < " + refernce + 
				"AND customer_id = " + id;
			break; 
			
		case "customerCouponsByDate" : sql = "SELECT * FROM COUPON " + 
				"INNER JOIN CUSTOMER_COUPON " + 
				"ON Coupon.ID=customer_coupon.COUPON_ID " + 
				"WHERE END_DATE < " + refernce + 
				"AND customer_id = " + id;
			break;
			
		case "customerCouponsByType" : sql = "SELECT * FROM COUPON " + 
				"INNER JOIN CUSTOMER_COUPON " + 
				"ON Coupon.ID=customer_coupon.COUPON_ID " + 
				"WHERE TYPE = '" + refernce +"' " + 
				"AND customer_id = " + id;
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
	
	public static void buyCoupon(String customerId , String couponId ) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
		String sql = "INSERT INTO CUSTOMER_COUPON VALUES("+customerId +"," + couponId + ")";
		Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new CuponSystemException("Failed updateing coupon purchase " , e);
		}finally {pool.returnConnection(con);}
		
		
	}
	
	public static void createCopuon(String companyId , String couponId ) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
		String sql = "INSERT INTO COMPANY_COUPON VALUES("+companyId +"," + couponId + ")";
		Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new CuponSystemException("Failed updateing coupon purchase " , e);
		}finally {pool.returnConnection(con);}
		
		
	}
	
	public Facade LogIn(String userType, String userName , String password) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		Facade facade = null;
		try {
		switch(userType) {
		case "company" : {
			String sql = "SELECT ID , PASSWORD  FROM COMPANY WHERE COMP_NAME=' "+ userName +"'";
			Statement st;
				st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				if(rs.getString("Password").equals(password)){
					facade = new CompanyFacade(rs.getLong("ID"));
					System.out.println("You are LoggedIN");
				}
				else {System.out.println("Wrong Password , please try again");}
				}
			else {System.out.println("Wrong User Name doesn't exist, please again");
			}
			
		}
			case "customer": {
			String sql = "SELECT ID , PASSWORD  FROM CUSTOMER WHERE CUST_NAME=' " + userName + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("Password").equals(password)){
					facade = new CustomerFacade(rs.getLong("ID"));
					System.out.println("You are LoggedIN");
					}
				else {System.out.println("Wrong Password, please try again");}
			}
			else {System.out.println("User do not exists, please try again");
				
			}
			
		}
		}
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to LogON, please try again" , e); 
		}
		return facade;
	}
	
		public static long GetId(String typeId) throws CuponSystemException {
			long id= 0;
			String sql= null; 
			ConnectionPool pool = ConnectionPool.getConnectionPool();
			Connection con = pool.getConnection();
			switch(typeId) {
			case "COUPON_ID": 
				sql = "SELECT MAX (COUPON_ID) FROM COUPON_ID";
				break;
			case "CUSTOMER_ID":
				sql = "SELECT MAX (CUSTOMER_ID) FROM CUSTOMER_ID";
				break;
			case "COMPANY_ID":
				 sql = "SELECT MAX (COMPANY_ID) FROM COMPANY_ID";
				 break;
			}
			try {
			// gets last ID exists in the table 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				id = rs.getLong(1);
			}
			// increments new ID 
				String sqlUpdate = "INSERT INTO "+ typeId +" VALUES(" +(id+1)+ ")";
				st.executeUpdate(sqlUpdate);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CuponSystemException("Failed to crete new ID ",e);
			}finally {pool.returnConnection(con);
			}
			//
			
			return id; 
		}
	
}
