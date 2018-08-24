package couponTests;

import beans.Customer;
import couponSystemException.CuponSystemException;
import dao.CustomerDBDAO;

public class CustomerCheck {
public static void main(String[] args) throws CuponSystemException {
	CustomerDBDAO custDB = new CustomerDBDAO();
	Customer customer = new Customer(5, "John", "2222");
	System.out.println(custDB.getAllCustomers());
	}
}

