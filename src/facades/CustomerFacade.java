package facades;
import java.sql.Date;
import java.util.Collection;
import DAO.CuoponDBDAO;
import DAO.SqlTableUtil;
import beans.Coupon;
import couponSystemException.CuponSystemException;

public class CustomerFacade {
	long customerLogged;

	public CustomerFacade(long customerLogged) {
		this.customerLogged = customerLogged;
	}
	
	CuoponDBDAO couponDAO = new CuoponDBDAO();
	
	public void PurchaseCoupon(Coupon coupon) {
		Coupon checkedCoupon=null;
		boolean dateFlag = false;
		boolean amountFlag = false;
		boolean alreadyPurchasedFlag = false;
		try {
			checkedCoupon = couponDAO.getCoupon(coupon.getId());
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		//check if coupon was already purchased by the Customer 
		Collection<Long> purchasedCouponsIds = null;
		try {
			purchasedCouponsIds=SqlTableUtil.getCouponsBelongTo("COUPON_ID", "CUSTOMER_COUPON", "CUSTOMER_ID", customerLogged);
			if (purchasedCouponsIds.contains(coupon.getId())) {
					System.out.println("You have already purchased that Coupon");
					alreadyPurchasedFlag = false;}
			else {
				    alreadyPurchasedFlag = true ; }
		
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		
		
		//check if coupon amount more then > 1 and  
		if (checkedCoupon.getAmount()>1) {
			amountFlag = true;
		}
		
		else {
			amountFlag = false;
		}
		
		//check if not expired 
		Date couponEndDate = checkedCoupon.getEndDate();
		long time = System.currentTimeMillis();
		Date currentDate = new Date(time);
		if ((couponEndDate.compareTo(currentDate)) > 0) {
			System.out.println("Sorry, the coupon you want to buy is expired");
				dateFlag = false; 
		}
		else {
				dateFlag = true;

		}
		//action - decrees amount and upDate Customer Coupon
		
		if (dateFlag  && amountFlag && alreadyPurchasedFlag) {
			int amount = checkedCoupon.getAmount();
			checkedCoupon.setAmount(amount-1);
			try {
				SqlTableUtil.buyCoupon(String.valueOf(customerLogged) ,String.valueOf(checkedCoupon.getId()));
			} catch (CuponSystemException e) {
				e.getMessage();
			}
			
		}
		
	}
	
}
