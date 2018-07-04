package couponTests;

import java.sql.Date;
import java.sql.SQLException;

import DAO.COMPANY_COUPON;
import DAO.CompanyDBDAO;
import DAO.CuoponDBDAO;
import DAO.SqlTableUtil;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import couponSystemException.CuponSystemException;
import facades.AdminFacade;
import facades.CompanyFacade;

public class CompanyTest {
	public static void main(String[] args) throws CuponSystemException, SQLException {
		
		System.out.println(SqlTableUtil.getAllCouponsBelongTo("CUOPON_ID" , "COMPANY_COUPON ", "COMPANY_ID", 15));
//		CompanyDBDAO compDAO = new CompanyDBDAO();
//		CuoponDBDAO cupDAO = new CuoponDBDAO();
//		Company first = new Company(4 , "MyCompa" , "1234678" , "updateMail@mail.com");
//		
//		AdminFacade admin = new AdminFacade();
//		CompanyFacade com = new CompanyFacade();
////		Customer cust = new Customer(7, "Peter7", "Mypass");
//	
////		for (int i = 0; i < 10 ; i++) {
////			
//		Date date1 = new Date(10);
//		Date date2 = new Date(10);
//		Coupon cup = new Coupon(20, "Art" , date1, date2, 10 , CouponType.valueOf("Food"),"Welcome", 30.45 , "www.google.com" );
//		com.createCoupon(cup);
////		cupDAO.createCoupon(cup);
////		}
////		admin.RemoveCustomer(cust);
////		
	}
}


