package facades;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import couponSystemException.CuponSystemException;
import dao.CompanyDBDAO;
import dao.CuoponDBDAO;
import dao.CustomerDBDAO;
import dao.SqlTableUtil;

public class AdminFacade extends Facade {

	CompanyDBDAO companyDAO = new CompanyDBDAO();
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	CuoponDBDAO couponDAO = new CuoponDBDAO();

	// CreateCompany tested and works
	public void createCompany(Company company) {
		// check if the Company name already exists
		try {
			if (SqlTableUtil.ifExsist("COMPANY", "COMP_NAME", company.getCompName())) { // need test
				System.out.println("Company with name = " + company.getCompName() + " already exsists");
			} else {
				company.setId(SqlTableUtil.getId("COMPANY_ID"));
				companyDAO.createCompany(company);
			}
		} catch (CuponSystemException e) {
			e.getMessage();
		}

	}

	// need test that all coupons belong to the company getting deleted as well
	// ??????
	public void removeCompany(Company company) {

		try {
			Collection<Coupon> couponsList = companyDAO.getCoupons(company);
			for (Coupon coupon : couponsList) {
				// removing all coupons belonging to the company from
				SqlTableUtil.removeWhere("COUPON", "ID", coupon.getId());
				SqlTableUtil.removeWhere("Customer_Coupon", "Coupon_ID", coupon.getId());
				SqlTableUtil.removeWhere("Company_Coupon", "Cuopon_ID", coupon.getId());
				// removing the Company from Company DB table
			}
			companyDAO.removeCompany(company);
		} catch (CuponSystemException e) {
			e.getMessage();
		}
	}

	// tested and works
	public void companyDetailsUpdate(Company company) {
		// COMPANY NAME CAN NOT BE CHANGED
		try {
			// checking if Admin tries to change the name by mistake
			Company companyPriorUpdate = companyDAO.getCompany(company.getId());
			if (company.getCompName().equals(companyPriorUpdate.getCompName())) { // names are equal --> update approved
				companyDAO.updateCompany(company);
			} // update

			else {
				throw new CuponSystemException("Company NAME can not be changed");
			}

		} catch (CuponSystemException e) {
			e.getMessage();
		}
	}

	// tested and works
	public Collection<Company> getAllCompanies() {
		Collection<Company> companies = null;
		try {
			companies = companyDAO.getAllCompanies();
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		return companies;
	}

	// tested and works
	public Company getCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.getCompany(id);
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		return company;
	}

	// Tested and works
	public void addCustomer(Customer customer) {
		// need check if there is already customer with same name
		try {
			if (SqlTableUtil.ifExsist("CUSTOMER", "CUST_NAME", customer.getCustName())) {
				throw new CuponSystemException("Customer with Name=" + customer.getCustName() + " already exists");
			} else {
				customer.setId(SqlTableUtil.getId("CUSTOMER_ID"));
				customerDAO.createCustomer(customer); // creating new customer
			}
		} catch (CuponSystemException e) {
			e.getMessage();
		}

	}

	// Need test when coupon purchase will be ready
	public void removeCustomer(Customer customer) {
		// all coupons purchased by the Customer need to be "released" from the table
		// Customer-Coupon
		try {
			SqlTableUtil.removeWhere("Customer_Coupon", "Customer_ID", customer.getId()); // delete Customer Coupon
																							// History
			customerDAO.removeCustomer(customer); // remove Customer from the System
		} catch (CuponSystemException e) {
			e.getMessage();
		}

	}

	// Works , test done
	public void updateCustomerDetails(Customer customer) {
		try {
			// checks if customer already exists and if the name is the same
			Customer CustomerpriorUpdate = customerDAO.getCustomer(customer.getId());
			// check if Admin doesn't tries to change the name
			if (customer.getCustName().equals(CustomerpriorUpdate.getCustName())) {
				customerDAO.updateCustomer(customer);
			} else {
				throw new CuponSystemException("Customer name can't be changed");
			}
		} catch (CuponSystemException e) {
			e.getMessage();
		}
	}

	// test done , works
	public Collection<Customer> getCustomerList() {
		Collection<Customer> customerList = null;
		try {
			customerList = customerDAO.getAllCustomers();
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		return customerList;
	}

	// test done , works
	public Customer getCustomer(long id) {
		Customer customer = null;
		try {
			customer = customerDAO.getCustomer(id);
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		return customer;
	}
}
