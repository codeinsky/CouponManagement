package dao;

import java.util.Collection;

import beans.Customer;
import couponSystemException.CuponSystemException;

public interface CustomerDAO {
	public void createCustomer(Customer customer) throws CuponSystemException; // test done , works

	public void removeCustomer(Customer customer) throws CuponSystemException; // test done . works

	public void updateCustomer(Customer customer) throws CuponSystemException; // test done , works

	public Customer getCustomer(long id) throws CuponSystemException; // test done , works

	public Collection<Customer> getAllCustomers() throws CuponSystemException; // test done , works

	public boolean login(String custName, String password); // not used yet
}
