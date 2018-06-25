package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import beans.Company;
import beans.Coupon;
import couponSystemException.CuponSystemException;
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
			PreparedStatement state;
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
	public Company getCompany(long id) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con =pool.getConnection();
		Company company = new Company();
		Statement st;
		
		try {
			st = con.createStatement();
			String query = "SELECT * FROM COMPANY WHERE ID=" + id; 
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
			company.setId(id);
			company.setCompName(rs.getString("COMP_NAME"));
			company.setPassword(rs.getString("PASSWORD"));
			company.setEmail(rs.getString("EMAIL"));
			}
			else {
				throw new CuponSystemException ("Copmany does not exist in the system");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			throw new CuponSystemException ("Failed to parse DataBase Result ");
			}
		pool.returnConnection(con);
		return company;
	}

	@Override
	public Collection<Company> getAllCompanies() throws CuponSystemException {
		Collection<Company> companyList = new HashSet<Company>();
		String query = "SELECT * FROM  COMPANY" ; 
		ResultSet rs; 
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			Statement state = con.createStatement();
			rs = state.executeQuery(query);
			while (rs.next()) {
				companyList.add(new Company(rs.getLong("ID"), rs.getString("COMP_NAME"), rs.getString("PASSWORD"), rs.getString("EMAIL")));
			}
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to get all Companies from DATA BASE" , e);
		}
		pool.returnConnection(con);
		return companyList;
	}

	
	
	@Override 
	// need run check with main , need to finish at home collection adding 
	// need to test with main , very important ! ! ! ! ! ! ! ! ! ! ! ! !
	public Collection<Coupon>getCoupons(Company company) throws CuponSystemException {
		Collection<Coupon> couponList = new HashSet<Coupon>(); // Collection of coupons returned by the method 
		Collection<Long> couponIdList = new HashSet<Long>(); //List with all coupons ID's belong to Company 
		String queryJoinTable = "SELECT * FROM COMPANY_COUPON WHERE ID = " + company.getId() ;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
		Statement state  = con.createStatement();
		ResultSet rs = state.executeQuery(queryJoinTable);
		while (rs.next()) {
			couponIdList.add(rs.getLong("COUPON_ID"));
		}
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to get Coupons from Data Base", e);
		} finally {pool.returnConnection(con);}
		// getting coupon objects from Coupon DB table per  Company 
		for (long id : couponIdList) {
			CuoponDBDAO getCop = new CuoponDBDAO();
			couponList.add(getCop.getCoupon(id));
			}
		if (couponList.size()==0) {
			System.out.println("No any Coupons associated with Company :" + company.getCompName());
		}
		return couponList;
		
	}

	@Override // need TEST 
	public Boolean Login(String compName, String Passowrd) throws CuponSystemException {
		Boolean result=false ; 
		String query = "SELECT PASSWORD FROM COMPANY WHERE COMP_NAME =" + compName;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		Statement state;
		try {
			state = con.createStatement();
		} catch (SQLException e) {
			throw new CuponSystemException ("Company name did not found, pls try again", e );
		}
		ResultSet rs;
		try {
			rs = state.executeQuery(query);
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to get user details from Data Base", e);
		}
		
		try {
			if (rs.getString("PASSWORD").equals(Passowrd)) {
				result = true;
			}
			else {
				System.out.println("Wrong password , pls try again");
			}
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to retrive password from DATA BASE" , e);
		} finally {pool.returnConnection(con);}
		return result ;
	}

	

	
}
