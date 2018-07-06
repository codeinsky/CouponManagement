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
		Coupon mycop = cop.getCoupon(4);
		mycop.setPrice(40.20);
		System.out.println(mycop);
		CompanyFacade comp = new CompanyFacade(3);
		comp.UpdqateCoupon(mycop);
		comp.removeCoupon(mycop);
		
		
		
}
}

