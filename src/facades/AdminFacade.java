package facades;

import java.util.Collection;
import DAO.CompanyDBDAO;
import DAO.CuoponDBDAO;
import DAO.CustomerDBDAO;
import DAO.SqlTableUtil;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import couponSystemException.CuponSystemException;

public class AdminFacade {

	CompanyDBDAO  companyDAO = new CompanyDBDAO();
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	CuoponDBDAO  couponDAO = new CuoponDBDAO();
	
	public void CreateCompany(Company company) {
		// check if the Company name already exists 
		try {
			if (SqlTableUtil.ifExsist("COMPANY", "COMP_NAME", company.getCompName())) {           // need test
				System.out.println("Company with name = " + company.getCompName() + "already exsists"); 
			}
			else { // need to check if to do " new company" or will get ready object 
				companyDAO.createCompany(company);
			}
		} catch (CuponSystemException e) {
			e.getMessage();}
			
	}
	
	
	// need test that all coupons belong to the company getting deleted as well ??????
	public void RemoveCompany(Company company) {
		
		try {
			Collection<Coupon> couponsList= companyDAO.getCoupons(company);
			for(Coupon coupon : couponsList) {
			// removing all coupons belonging to the company from 
			SqlTableUtil.removeWhere("COUPON", "ID", coupon.getId());
			SqlTableUtil.removeWhere("Customer_Coupon","Coupon_ID" , coupon.getId());
			SqlTableUtil.removeWhere("Company_Coupon", "Cuopon_ID", coupon.getId());
			//removing the Company from Company DB table 
			}
			companyDAO.removeCompany(company);
			} catch (CuponSystemException e) {
			e.getMessage();
		}
	}
	
	
	public void CompanyDetailsUpdate(Company company) {
		// COMPANY NAME CAN NOT BE CHANGED 
		try {
		    // checking if Admin tries to change the name by mistake 
			Company companyPriorUpdate = companyDAO.getCompany(company.getId());
			if (company.getCompName().equals(companyPriorUpdate.getCompName())) { // names are equal --> update approved
				companyDAO.updateCompany(company);} // update 
			
			else {throw new CuponSystemException ("Company NAME can not be changed");}
			
			}
			 catch (CuponSystemException e) {
				 e.getMessage();}
}
		//need TEST 
	public Collection<Company> GetAllCompanies () {
		Collection<Company> companies = null;
		try {
			companies = companyDAO.getAllCompanies();
		} catch (CuponSystemException e) {
			e.getMessage();
			}
		return companies;
	}
	
	 // need TEST 
	public Company GetCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.getCompany(id);
		} catch (CuponSystemException e) {
			e.getMessage();
			}
		return company;
	}
	
	// need TEST 
	public void AddCustomer(Customer customer) {
		// need check if there is already customer with same name 
		try {
			if (SqlTableUtil.ifExsist("CUSTOMER", "CUST_NAME", customer.getCustName())) {
			throw new CuponSystemException("Customer with Name=" + customer.getCustName() + " already exists");	
			}
			else {
				customerDAO.createCustomer(customer); // creating new customer 
			}
		} catch (CuponSystemException e) {
			e.getMessage();
			}
		
	}
	// need check 
	public void RemoveCustomer(Customer customer) {
	// all coupons purchased by the Customer need to be "released" from the table Customer-Coupon
	try {
		SqlTableUtil.removeWhere("Customer_Coupon", "Customer_ID", customer.getId()); // delete Customer Coupon History
		customerDAO.removeCustomer(customer); // remove Customer from the System 
	} catch (CuponSystemException e) {
		e.getMessage();
	}
	
	}
	// need to complete 
	public void UpdateCustomerDetails(Customer customer) {
		}
	// need complete
	public Collection<Customer> GetCustomerList(){
		return null;
	}
	// need complete
	public Customer GetCustomer() {
		return null;
	}
}

