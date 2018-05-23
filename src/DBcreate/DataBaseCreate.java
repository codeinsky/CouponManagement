package DBcreate;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseCreate {
	public static void main(String[] args) {
		
	
	String url = "jdbc:derby://localhost:1527/CuponSystemDB;create=true;";
	try (Connection con = DriverManager.getConnection(url);) {
		String sql = "Create table Cupon(ID INT , NAME VARCHAR(20))";
		java.sql.Statement st = con.createStatement();
		st.executeUpdate(sql);
		

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

}
}
