package couponTests;

import DAO.SqlTableUtil;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import couponSystemException.CuponSystemException;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.Facade;

public class CompanyTest {
	public static void main(String[] args) throws CuponSystemException {
		AdminFacade admin = new AdminFacade();
		CompanyFacade companyFacade = new CompanyFacade(4);
		Company company = new Company(15,"FaceBook","1234","trade@gamail.com");
		Customer customer = new Customer(15,"Ivan", "1234");
		admin.CreateCompany(company);
		admin.AddCustomer(customer);
		Coupon cop1 = companyFacade.GetCouponById(6);
		System.out.println(cop1);
		cop1.setTitle("superSall");
		companyFacade.createCoupon(cop1);
//		System.out.println(SqlTableUtil.GetId("COMPANY_ID"));
//		System.out.println(SqlTableUtil.GetId("COUPON_ID"));
//		System.out.println(SqlTableUtil.GetId("CUSTOMER_ID"));
//		
		Facade myCustomer = SqlTableUtil.LogIn("customer", "Peter", "1234");
		Facade myCopmany = SqlTableUtil.LogIn("company", "New", "1234");
	}
}

