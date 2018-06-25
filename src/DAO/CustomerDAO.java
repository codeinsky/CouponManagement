package DAO;

import java.util.Collection;

import beans.Customer;
import couponSystemException.CuponSystemException;

public interface CustomerDAO {
	public void createCustomer(Customer Customer) throws CuponSystemException;
	public void removeCustomer(Customer Customer) throws CuponSystemException;
	public void updateCustomer(Customer Customer) throws CuponSystemException;
	public Customer getCustomer(Long id) throws CuponSystemException;
	public Collection<Customer> getAllCustomers() throws CuponSystemException;
	public boolean login(String custName,String password);
}
