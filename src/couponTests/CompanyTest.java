package couponTests;

import java.sql.Date;
import java.sql.SQLException;
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
		CuoponDBDAO cop = new CuoponDBDAO();
		CompanyFacade comp = new CompanyFacade(4);
		Coupon mycop = cop.getCoupon(1);
		System.out.println(mycop);
		comp.removeCoupon(mycop);
		// will remove all coupons 1 , company 4 
		
		
		
}
}

