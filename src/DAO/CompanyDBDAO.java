package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import Beans.Company;
import cuponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class CompanyDBDAO implements CompanyDAO {

	@Override
	public void createCompany(Company Company) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String CreateCompanySt= "INSERT INTO COMPANY(ID, COMP_NAME , PASSWORD , EMAIL ) VALUES (?,?,?,?) "; 
			Connection con = null ; 
			con = pool.getConnection();
			PreparedStatement state;
			
			try {
				state = con.prepareStatement(CreateCompanySt);
				state.setLong(1, Company.getId());
				state.setString(2, Company.getCompName());
				state.setString(3, Company.getPassword());
				state.setString(4,  Company.getEmail());
				state.executeUpdate();
			} catch (SQLException e) {
				throw new CuponSystemException ("Failed to prepare SLQ statement" , e);
			}
			pool.returnConnection(con);
		
		
		
	}

	@Override
	public void removeCompany(Company Company) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String RemoveCompanySt = "DELETE FROM COMPANY WHERE ID=? ";
		Connection con = null;
		try {
			con = pool.getConnection();
		} catch (CuponSystemException e) {
}
		
		PreparedStatement state;
		try {
			state = con.prepareStatement(RemoveCompanySt);
			state.setLong(1, Company.getId());
			state.executeUpdate();
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to remove the company", e );
		}
		pool.returnConnection(con);
		
		
		
		
	}

	@Override
	public void updateCompany(Company Company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Company getCompany(Long id) {
		// TODO Auto-generated method stub
		return null;
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
