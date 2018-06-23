package DAO;

import java.util.Collection;

import beans.Customer;

public interface CustomerDAO {
	public void createCustomer(Customer Customer);
	public void removeCustomer(Customer Customer);
	public void updateCustomer(Customer Customer);
	public Customer getCustomer(Long id);
	public Collection getAllCustomers();
	public boolean login(String custName,String password);
}
