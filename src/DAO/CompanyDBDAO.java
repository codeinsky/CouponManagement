package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import Beans.Company;
import cuponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class CompanyDBDAO implements CompanyDAO {

	@Override
	public void createCompany(Company Company) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String createCompanySt= "INSERT INTO COMPANY(ID, COMP_NAME , PASSWORD , EMAIL ) VALUES (?,?,?,?) "; 
			Connection con = null ; 
			con = pool.getConnection();
			PreparedStatement state;
			
			try {
				state = con.prepareStatement(createCompanySt);
				state.setLong(1, Company.getId());
				state.setString(2, Company.getCompName());
				state.setString(3, Company.getPassword());
				state.setString(4,  Company.getEmail());
				state.executeUpdate();
			} catch (SQLException e) {
				throw new CuponSystemException ("Failed to add ne compnay in DataBase " , e);
			}
			pool.returnConnection(con);
		
		
		
	}

	@Override
	public void removeCompany(Company Company) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String removeCompanySt = "DELETE FROM COMPANY WHERE ID=? ";
		Connection con = null;
		try {
			con = pool.getConnection();
		} catch (CuponSystemException e) {
}
		
		PreparedStatement state;
		try {
			state = con.prepareStatement(removeCompanySt);
			state.setLong(1, Company.getId());
			state.executeUpdate();
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to remove the company", e );
		}
		pool.returnConnection(con);
		
		
		
		
	}

	@Override //can be changed to update one one item per company 
	public void updateCompany(Company Company) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String updateCompany = "UPDATE COMPANY  SET COMP_NAME = ? , PASSWORD = ? , EMAIL = ?  WHERE ID= ?"; 
		PreparedStatement state;
		try {
		state = con.prepareStatement(updateCompany);
		state.setString(1, Company.getCompName());
		state.setString(2, Company.getPassword());
		state.setString(3, Company.getEmail());
		state.setLong(4, Company.getId());
		state.executeUpdate(); 
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to update the Copmany" , e);
		}
		pool.returnConnection(con);
	}

	
	@Override
	public Company getCompany(Long id) throws CuponSystemException {
		Company company = null ;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con =pool.getConnection();
		Statement st;
		
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			throw new CuponSystemException("Failed to pull data from DataBase" , e1); 
		}
		String query = "SELECT * FROM COMPANY WHERE id=2" ; 
		ResultSet rs ; 
		
			try {
				rs = st.executeQuery(query);
				while (rs.next()) {
				company.setCompName(rs.getString(1));
				company.setPassword(rs.getString(2));
				company.setEmail(rs.getString(3));
				company.setId(rs.getLong(4));
				}
			} catch (SQLException e) {
				throw new CuponSystemException ("" , e);
			}
		
		
		return company;
		
	}

	@Override
	public Collection getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection getCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Login(String compName, String Passowrd) {
		// TODO Auto-generated method stub
		return null;
	}

}
