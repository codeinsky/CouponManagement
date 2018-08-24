package couponTests;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import couponSystemException.CuponSystemException;
import dao.SqlTableUtil;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;
import facades.Facade;

public class CompanyTest {
	public static void main(String[] args) throws CuponSystemException {
		AdminFacade admin = new AdminFacade();
		CompanyFacade companyFacade = new CompanyFacade(4);
		Company company = new Company(15,"FaceBook","1234","trade@gamail.com");
		Customer customer = new Customer(15,"Ivan", "1234");
		admin.createCompany(company);
		admin.addCustomer(customer);
		Coupon cop1 = companyFacade.getCouponById(6);
		System.out.println(cop1);
		cop1.setTitle("superSall");
		companyFacade.createCoupon(cop1);
//		System.out.println(SqlTableUtil.GetId("COMPANY_ID"));
//		System.out.println(SqlTableUtil.GetId("COUPON_ID"));
//		System.out.println(SqlTableUtil.GetId("CUSTOMER_ID"));
//		
		Facade myCustomer = SqlTableUtil.logIn("customer", "Ivan", "1234");
		Facade myCopmany = SqlTableUtil.logIn("company", "New", "1234");
		CustomerFacade cust = (CustomerFacade) myCustomer;
		cust.getAllMyCoupons();
	}
}

