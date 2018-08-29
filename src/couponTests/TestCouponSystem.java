package couponTests;

import java.sql.Date;

import beans.Company;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import facades.AdminFacadeF;
import facades.CompanyFacadeF;


public class TestCouponSystem {
public static void main(String[] args) {
	/**
	 * 
	 * 											Admin Methods Test 
	 *	1.createCompany(Company company) - done 
	 *	2.removeCompany(Company company) - done and works. removes company per ID - if need change to Company name
	 * 	3.companyDetailsUpdate(Company company) - done and tested, Company can not be changed. finds company per ID and changes all details 
	 * 	4.getAllCompanies() - done , works . Returns all companies with ID , Name , Password , Email. Empty Coupon ID list 
	 * 	5.addCustomer(Customer customer) - done , works 
	 * 	6.removeCustomer(Customer customer) - done , removes per Id . 
     *	7.updateCustomerDetails(Customer customer) - done , name can not be changed. Password changed successfully 
	 * 	8.getCustomerList() - done , companies list retrieved
	 * 
	 * 	
	 * 									   	Company Methods Test
	 * 
	 * 	1. CreateCoupon - done , works . Date example Date.valueOf("2018-01-01")", type example  CouponType.valueOf("Food")
	 *	2. RemoveCoupon - done , works . Checks if coupons belongs to LoggedIn Company 
	 *	3. UpdateCoupon - done , works . Update per Coupon Id and checks if coupons belongs to the company wants to update 
	 *	4. GetCouponByID - done works . Returns single coupon belongs to the company 
	 *	5. GetAllCoupons - 
	 * 	6. SortCoupons 
     *	7. LogIN  - done , works 
     * 
	 *										
	 * 
	 **/
	
	
	
	Coupon coupon = new Coupon(1, "Cinema", Date.valueOf("2018-01-01"), Date.valueOf("2018-01-29") , 10 , CouponType.valueOf("Food"), "Best deal" , 34.87 , "www.image.jpge");
	Coupon coupon1 = new Coupon(2, "BigMag", Date.valueOf("2017-06-01"), Date.valueOf("2019-01-31") , 10 , CouponType.valueOf("Food"), "Best deal" , 34.87 , "www.image.jpge");
	Coupon coupon2 = new Coupon(1, "FishAndMe", Date.valueOf("2016-01-01"), Date.valueOf("2020-01-29") , 10 , CouponType.valueOf("Food"), "Best deal" , 34.87 , "www.image.jpge");
	Coupon coupon3 = new Coupon(1, "TripOut", Date.valueOf("2012-01-01"), Date.valueOf("2019-01-29") , 10 , CouponType.valueOf("Food"), "Best deal" , 34.87 , "www.image.jpge");
	Coupon coupon4 = new Coupon(7, "BestHotelDeal", Date.valueOf("2012-01-01"), Date.valueOf("2014-01-29") , 10 , CouponType.valueOf("Health"), "FthatDeal" , 34.87 , "www.image.jpge");
	
    Company testCompany = new Company(3, "Intel" , "abcd" , "intel@intel.com");
    Company testCompany1 = new Company(6, "Teva" , "12345" , "newTeva@teva.co.il");
    Company testCompany2 = new Company(5, "Microsft" , "1111" , "microsoft@micro.com");
    Company testCompany3 = new Company(4, "Apple" , "abcd" , "Apple@pple.com");
    Company testCompany4 = new Company(5, "Amdocs" , "3333" , "amdocs@amdocs.com");
    
    
    Customer customer = new Customer(11, "Ivan", "1234");
    Customer customer1 = new Customer(12, "Pet", "1111");
    Customer customer2 = new Customer(13, "Jon", "2222");
    Customer customer3 = new Customer(14, "Smon", "3333");
    Customer customer4 = new Customer(25, "Jeny111", "changed");
    
    
	CouponSystem system = CouponSystem.getInstance();
	CompanyFacadeF company = (CompanyFacadeF)system.logIn("company", "Amdocs", "3333");
//	company.createCoupon(coupon);
//	company.createCoupon(coupon1);
//	company.createCoupon(coupon2);
//	company.createCoupon(coupon3);
//	company.createCoupon(coupon4);
	
//	System.out.println(company.getCouponById(6));
	System.out.println(company.getAllCoupons());
	//company.createCoupon(coupon);
//	AdminFacadeF admin = (AdminFacadeF) system.logIn("admin", "admin", "1234");
//	admin.createCompany(testCompany);
//	admin.createCompany(testCompany1);
//	admin.createCompany(testCompany2);
//	admin.createCompany(testCompany3);
//	admin.createCompany(testCompany4);
	
//	admin.addCustomer(customer);
//	admin.addCustomer(customer1);
//	admin.addCustomer(customer2);
//	admin.addCustomer(customer3);
//	admin.addCustomer(customer4);
	
//	admin.removeCompany(testCompany);
//	admin.removeCompany(testCompany1);
//	admin.removeCompany(testCompany2);
//	admin.removeCompany(testCompany3);
//	admin.removeCompany(testCompany4);
//	admin.removeCompany(testCompany);
	
//	admin.removeCustomer(customer);
//	admin.removeCustomer(customer1);
//	admin.removeCustomer(customer2);
//	admin.removeCustomer(customer3);
//	admin.removeCustomer(customer4);
	
	//get all companies - works 
//	admin.companyDetailsUpdate(testCompany3);
//	System.out.println(admin.getAllCompanies());
	
//	System.out.println(admin.getCustomerList());
}
}
