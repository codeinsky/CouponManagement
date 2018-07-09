package facades;
import java.util.Collection;
import java.util.HashSet;

import DAO.CuoponDBDAO;
import DAO.SqlTableUtil;
import beans.Coupon;
import couponSystemException.CuponSystemException;

public class CompanyFacade {
	private long companyIdLogged; //ID of the company that is logged in the system
	public CompanyFacade(long companyIdLogged) {
		this.companyIdLogged = companyIdLogged;
	}
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
	//  need to complete  and test 
	public void removeCoupon(Coupon coupon ) {
		Collection<Long> couponsIds ;
		try {
			// check if coupon belongs to the company before remove 
			couponsIds = SqlTableUtil.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			
				if (couponsIds.contains(coupon.getId())) { // the coupon exists , removal will be execute 
					// removes all purchased coupons from CUSTOMER_COUPON table 
					SqlTableUtil.removeWhere("CUSTOMER_COUPON", "COUPON_ID", coupon.getId()); //need debug
					SqlTableUtil.removeWhere("COMPANY_COUPON", "COUPON_ID" , coupon.getId()); // need debug
					//removes the coupon from the COUPON table
					couponDAO.removeCoupon(coupon);
				}
				else {System.out.println("Coupon you want to remove does not belong to your company");}
				
	} catch (CuponSystemException e) {
		e.printStackTrace(); 
		e.getMessage();
	}
			
}
	
	// TEST DONE , works  
	public void UpdqateCoupon(Coupon coupon) {
		// need check if that coupon belongs to the company
		Collection<Long> couponsIds ;
		try {
			couponsIds = SqlTableUtil.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println(couponsIds);
			// checks if the coupon belongs to the company 
			if (couponsIds.contains(coupon.getId())) { 
					couponDAO.updateCopupon(coupon);
					System.out.println("Proccesing the coupon update");
			}
			else {
				System.out.println("This coupon doesn't belong to your Company");
			}
				} catch (CuponSystemException e) {
					e.getMessage();
				}
			
		
		
	}
	// done and works 
	public Coupon GetCouponById (long id) {
		Coupon coupon = null;
		Collection<Long> couponsIds ;
		try {
			couponsIds = SqlTableUtil.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println(couponsIds);
			// checks if the coupon belongs to the company 
			if (couponsIds.contains(id)) { 
				coupon = couponDAO.getCoupon(id);
			}
			else {
				System.out.println("This coupon doesn't belong to your Company");
			}
				} catch (CuponSystemException e) {
					e.getMessage();
				}
		return coupon;
		
	}
	// Test done - works 
	public Collection<Coupon> GetAllCoupons() {
		Collection<Coupon> allCoupons = new HashSet<Coupon>();
		Collection<Long> couponsIds ;
		try {
			couponsIds = SqlTableUtil.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println("Coupns belong to your Company are: " + couponsIds);
			if (couponsIds.size()==0) {
				System.out.println("There are no any coupons associated to your Compnay");
			}
			else {
				for (long id : couponsIds) {
					allCoupons.add(couponDAO.getCoupon(id));
				}
					}
				
			
				} catch (CuponSystemException e) {
					e.getMessage();
				} 
			return allCoupons;
		}
	
	// Get list of coupons sorted by : TYPE , PRICE LESS THEN ,  DATE BEFORE 'yyyy-mm-dd'
	public Collection<Coupon> SortCouponBy (String select, String refernce){
		Collection<Coupon> selectedCouponsBy = null;
		try {
			selectedCouponsBy = SqlTableUtil.GetCouponSelected(companyIdLogged, select, refernce);
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		return selectedCouponsBy;
	}
	
	
}