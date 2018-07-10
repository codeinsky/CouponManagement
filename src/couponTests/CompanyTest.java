package couponTests;


import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import facades.CustomerFacade;

public class CompanyTest {
	public static void main(String[] args) throws CuponSystemException, SQLException {
		CustomerFacade customer = new CustomerFacade(1);
		CuoponDBDAO cup = new CuoponDBDAO();
		Coupon coupon1 = cup.getCoupon(5);
		customer.PurchaseCoupon(coupon1);

		
		
// 		string to SQL date 		
//		String startDate="10-05-2018";
//		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
//		java.util.Date date = null;
//		try {
//			date = sdf1.parse(startDate);
//		} catch (ParseException e) {
//			
//		}
//		Date sqlStartDate = new java.sql.Date(date.getTime()); 

	 
		
		
		
}
}

