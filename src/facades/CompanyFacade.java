package facades;

import java.util.Collection;

import DAO.CuoponDBDAO;
import DAO.SqlTableUtil;
import beans.Coupon;
import couponSystemException.CuponSystemException;

public class CompanyFacade {
	CuoponDBDAO couponDAO = new CuoponDBDAO();
	// tested works 
	public void createCoupon(Coupon coupon) {
		try {
			if (SqlTableUtil.ifExsist("COUPON", "TITLE", coupon.getTitle())) {           // need test
				System.out.println("Coupon with name = " + coupon.getTitle() + "already exsists"); 
			}	
			else {
				couponDAO.createCoupon(coupon);
			}
		} catch (CuponSystemException e) {
			e.getMessage();
		}

	}
	
	public void removeCoupon(Coupon coupon) {
		
		
	}
}