package facades;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import couponSystemException.CouponSystemException;
import dao.CompanyDBDAO;
import dao.CuoponDBDAO;
import dao.CustomerDBDAO;
import dao.HelperMethodsDAO;


public class AdminFacadeF extends Facade{
	CompanyDBDAO companyDAO = new CompanyDBDAO();
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	CuoponDBDAO couponDAO = new CuoponDBDAO();
	HelperMethodsDAO helperDAO = new HelperMethodsDAO();

	// CreateCompany tested and works
	public void createCompany(Company company) {
		// check if the Company name already exists
		try {
			if (helperDAO.ifExist("COMPANY", "COMP_NAME", company.getCompName())) { // need test
				System.out.println("Company with name = " + company.getCompName() + " already exsists");
			} else {
				company.setId(helperDAO.getId("COMPANY_ID"));
				companyDAO.createCompany(company);
			}
		} catch (CouponSystemException e) {
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
				helperDAO.removeWhere("COUPON", "ID", coupon.getId());
				helperDAO.removeWhere("Customer_Coupon", "Coupon_ID", coupon.getId());
				helperDAO.removeWhere("Company_Coupon", "Cuopon_ID", coupon.getId());
				// removing the Company from Company DB table
			}
			companyDAO.removeCompany(company);
		} catch (CouponSystemException e) {
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
				throw new CouponSystemException("Company NAME can not be changed");
			}

		} catch (CouponSystemException e) {
			e.getMessage();
		}
	}

	// tested and works
	public Collection<Company> getAllCompanies() {
		Collection<Company> companies = null;
		try {
			companies = companyDAO.getAllCompanies();
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return companies;
	}

	// tested and works
	public Company getCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.getCompany(id);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return company;
	}

	// Tested and works
	public void addCustomer(Customer customer) {
		// need check if there is already customer with same name
		try {
			if (helperDAO.ifExist("CUSTOMER", "CUST_NAME", customer.getCustName())) {
				throw new CouponSystemException("Customer with Name=" + customer.getCustName() + " already exists");
			} else {
				customer.setId(helperDAO.getId("CUSTOMER_ID"));
				customerDAO.createCustomer(customer); // creating new customer
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	// Need test when coupon purchase will be ready
	public void removeCustomer(Customer customer) {
		// all coupons purchased by the Customer need to be "released" from the table
		// Customer-Coupon
		try {
			helperDAO.removeWhere("Customer_Coupon", "Customer_ID", customer.getId()); // delete Customer Coupon
																							// History
			customerDAO.removeCustomer(customer); // remove Customer from the System
		} catch (CouponSystemException e) {
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
				throw new CouponSystemException("Customer name can't be changed");
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}
	}

	// test done , works
	public Collection<Customer> getCustomerList() {
		Collection<Customer> customerList = null;
		try {
			customerList = customerDAO.getAllCustomers();
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return customerList;
	}

	// test done , works
	public Customer getCustomer(long id) {
		Customer customer = null;
		try {
			customer = customerDAO.getCustomer(id);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return customer;
	}
}
