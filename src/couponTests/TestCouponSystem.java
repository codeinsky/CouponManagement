package couponTests;

import beans.Company;
import beans.Customer;
import facades.AdminFacadeF;


public class TestCouponSystem {
public static void main(String[] args) {
	/**
	 * 
	 * Admin Method Test 
	 *	1.createCompany(Company company) - done 
	 *	2.removeCompany(Company company) - done and works. removes company per ID - if need change to Company name
	 * 	3.companyDetailsUpdate(Company company) - 
	 * 	4.getAllCompanies()
	 * 	5.addCustomer(Customer customer)
	 * 	6.removeCustomer(Customer customer) - done 
     *	7.updateCustomerDetails(Customer customer)
	 * 	8.getCustomerList()
     * 
	 * 									Admin Test Results
	 * 1. Log in - works tested 
	 * 2. Create company works 
	 * 		- Company with same name not create and message shown - done 
	 * 		- Counter works , program adds incrementing Id based on previous one - done  
	 * 		- Connection Pool works right , all connection were returned back to the pool - done 
	 * 		
	 * 3. Add customer - works and tested 
	 * 		- Customer with existing name was not created , and message was shown - done 
	 * 		- Counter works and Id table update wit every new customer - done 
	 * 		- All connection returned -  done 
	 * 
	 **/

    Company testCompany = new Company(3, "Intel" , "abcd" , "intel@intel.com");
    Company testCompany1 = new Company(2, "Teva" , "1234" , "teva@teva.com");
    Company testCompany2 = new Company(5, "Microsft" , "1111" , "microsoft@micro.com");
    Company testCompany3 = new Company(5, "Apple" , "2222" , "Apple@pple.com");
    Company testCompany4 = new Company(5, "Amdocs" , "3333" , "amdocs@amdocs.com");
    
    
    Customer customer = new Customer(2, "Ivan", "1234");
    Customer customer1 = new Customer(2, "Peter", "1111");
    Customer customer2 = new Customer(2, "John", "2222");
    Customer customer3 = new Customer(2, "Saimon", "3333");
    Customer customer4 = new Customer(2, "Jeny", "3333");
    
    
	CouponSystem system = CouponSystem.getInstance();
	AdminFacadeF admin = (AdminFacadeF) system.logIn("admin", "admin", "1234");
//	admin.createCompany(testCompany);
//	admin.createCompany(testCompany1);
//	admin.createCompany(testCompany2);
//	admin.createCompany(testCompany3);
//	admin.createCompany(testCompany4);
//	
//	admin.addCustomer(customer);
//	admin.addCustomer(customer1);
//	admin.addCustomer(customer2);
//	admin.addCustomer(customer3);
//	admin.addCustomer(customer4);
//	
	admin.removeCompany(testCompany);
//	admin.removeCompany(testCompany1);
//	admin.removeCompany(testCompany2);
//	admin.removeCompany(testCompany3);
//	admin.removeCompany(testCompany4);
//	admin.removeCompany(testCompany);
}
}
