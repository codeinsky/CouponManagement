package facades;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

import beans.Coupon;
import couponSystemException.CuponSystemException;
import dao.CuoponDBDAO;
import dao.SqlTableUtil;

public class CustomerFacade extends Facade {
	long customerLogged;

	// has a constructor for ID reference for customer activities
	public CustomerFacade(long customerLogged) {
		this.customerLogged = customerLogged;
	}

	CuoponDBDAO couponDAO = new CuoponDBDAO();

	// works and tested
	public void purchaseCoupon(Coupon coupon) {
		Coupon checkedCoupon = null;
		boolean dateFlag = false;
		boolean amountFlag = false;
		boolean alreadyPurchasedFlag = false;
		try {
			checkedCoupon = couponDAO.getCoupon(coupon.getId());
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		// check if coupon was already purchased by the Customer
		Collection<Long> purchasedCouponsIds = null;
		try {
			purchasedCouponsIds = SqlTableUtil.getCouponsBelongTo("COUPON_ID", "CUSTOMER_COUPON", "CUSTOMER_ID",
					customerLogged);
			if (purchasedCouponsIds.contains(coupon.getId())) {
				System.out.println("You have already purchased that Coupon");
				alreadyPurchasedFlag = false;
			} else {
				alreadyPurchasedFlag = true;
			}

		} catch (CuponSystemException e) {
			e.getMessage();
		}

		// check if coupon amount more then > 1 and
		if (checkedCoupon.getAmount() > 1) {
			amountFlag = true;
		}

		else {
			amountFlag = false;
		}

		// check if not expired
		Date couponEndDate = checkedCoupon.getEndDate();
		long time = System.currentTimeMillis();
		Date currentDate = new Date(time);
		if ((couponEndDate.compareTo(currentDate)) > 0) {
			System.out.println("Sorry, the coupon you want to buy is expired");
			dateFlag = false;
		} else {
			dateFlag = true;

		}
		// action - decrees amount and upDate Customer Coupon

		if (dateFlag && amountFlag && alreadyPurchasedFlag) {
			int amount = checkedCoupon.getAmount();
			checkedCoupon.setAmount(amount - 1);
			try {
				couponDAO.updateCopupon(checkedCoupon);
				SqlTableUtil.buyCoupon(String.valueOf(customerLogged), String.valueOf(checkedCoupon.getId()));
			} catch (CuponSystemException e) {
				e.getMessage();
			}

		}

	}

	// works and tested
	public Collection<Coupon> getAllMyCoupons() {
		Collection<Coupon> myCoupons = new HashSet<Coupon>();
		Collection<Long> couponsId = null;
		try {
			couponsId = SqlTableUtil.getCouponsBelongTo("COUPON_ID", "CUSTOMER_COUPON", "CUSTOMER_ID", customerLogged);
			System.out.println(couponsId);
			for (long id : couponsId) {
				myCoupons.add(couponDAO.getCoupon(id));
			}
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		return myCoupons;
	}

	// getting coupons purchased by logged Customer
	// to get coupons by PRICE pass select = " customerCouponsByPrice"
	// to get coupons by DATE pass select = " customerCouponsByDate"
	// to get coupons by TYPE pass select = " customerCouponsByType"
	// reference pass :
	//
	// PRICE example till price reference = "300"
	// DATE example till date reference = "MM-DD-YYYY"
	// TYPE example till type reference = " Resturans,
	// Electricity,
	// Food,
	// Health,
	// Sports,
	// Campning,
	// Travelling"
	public Collection<Coupon> getMyCouponsSortedByType(String select, String reference) {
		Collection<Coupon> mySortedCoupons = null;
		try {
			mySortedCoupons = SqlTableUtil.getCouponSelected(customerLogged, select, reference);
		} catch (CuponSystemException e) {
			e.getMessage();
		}
		return mySortedCoupons;
	}

}
