package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import beans.Customer;
import couponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class CustomerDBDAO implements CustomerDAO {

	@Override // new test 
	public void createCustomer(Customer customer) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "INSERT INTO Customer (ID , CUST_NAME, PASSWORD) VALUES (? , ? , ?)";
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, customer.getId());
		st.setString(2, customer.getCustName());
		st.setString(3, customer.getPassword());
		st.executeUpdate();
		} catch (SQLException e) {
		throw new CuponSystemException ("Failed to create new Customer", e); 
		}finally {pool.returnConnection(con);}
		
	}

	@Override // need test
	public void removeCustomer(Customer customer) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "DELETE * FROM CUSTOMER WHERE ID=" + customer.getId(); 
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to delete Customer with id = " + customer.getId() , e);
		} finally {pool.returnConnection(con);}

		
		
		
	}

	@Override // need test 
	public void updateCustomer(Customer customer) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "UPDATE COSTOMER  SET  ID = ID ? , CUST_NAME  = ? , PASSWORD = ? ,  WHERE ID= ?" ; 
		try {
		PreparedStatement  st = con.prepareStatement(sql);
		st.setLong(1, customer.getId());
		st.setString(2, customer.getCustName());
		st.setString(3, customer.getPassword());
		st.executeUpdate();
		} catch (SQLException e) {
		throw new CuponSystemException("Failed to updated customer with id = " + customer.getId() , e);
		} finally {pool.getConnection();}
	}

	@Override
	public Customer getCustomer(Long id) throws CuponSystemException {
		Customer customer = new Customer(id, null, null);
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String query = "SELECT * FROM CUSTOMER WHERE id = " + id ; 
		Connection con = pool.getConnection();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
			if (rs.next()) {
				customer.setCustName(rs.getString("CUST_NAME"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setId(id);
			}
			else {
				System.out.println("Customer with id = " +  id + "does not exsist");
			}
		} catch (SQLException e) {
			throw new CuponSystemException ("Failed to get Customer with id=" + id  , e ) ; 
		}finally {pool.returnConnection(con);}
		
		return customer;
	}

	@Override
	public Collection<Customer> getAllCustomers() throws CuponSystemException {
		Collection<Customer> customerList = new HashSet<Customer>();		
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String query = "SELECT * FROM CUSTOMER";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
			customerList.add(new Customer(rs.getLong("ID"), rs.getString("CUST_NAME"), rs.getString("PASSWORD")));
			}
			} catch (SQLException e) {
			throw new CuponSystemException ("Failed to get all Customers from data base" , e);
		} finally {pool.returnConnection(con);}
		
		return customerList;
	}

	@Override
	public boolean login(String custName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
