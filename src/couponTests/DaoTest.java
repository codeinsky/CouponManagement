package couponTests;


import java.sql.Date;

import DAO.CompanyDBDAO;
import DAO.CuoponDBDAO;
import beans.Coupon;
import beans.CouponType;
import couponSystemException.CuponSystemException;

public class DaoTest {

	public static void main(String[] args) throws CuponSystemException  {
		CompanyDBDAO cop = new CompanyDBDAO();
		CuoponDBDAO cup=  new CuoponDBDAO();
//
	for (Coupon copp : cup.getCouponByType(CouponType.Resturans)) {
		System.out.println(copp);
	}
	
		 
	}
	   

}
