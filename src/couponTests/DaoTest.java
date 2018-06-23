package couponTests;


import java.sql.Date;

import DAO.CuoponDBDAO;
import beans.Coupon;
import couponSystemException.CuponSystemException;

public class DaoTest {

	public static void main(String[] args) throws CuponSystemException  {
	
		
		Date startDate = Date.valueOf("2017-11-01");
		Date endDate = Date.valueOf("2017-10-05");
		Coupon cupon = new Coupon(10, "Cinema", startDate, endDate, 10, "The big day ", (double)222 , "www.google.com");
		CuoponDBDAO cup = new CuoponDBDAO();
		cup.updateCopupon(cupon);
		System.out.println(cup.getCoupon(12));
		System.out.println(cup.getAllCoupons());
		
	}

}
