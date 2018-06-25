package DAO;

import java.util.Collection;

import beans.Coupon;
import beans.CouponType;
import couponSystemException.CuponSystemException;

public interface CuoponDAO {
	public void createCoupon(Coupon coupon) throws CuponSystemException; // test done - works 
	public void removeCoupon(Coupon coupon) throws CuponSystemException; // test done - works 
	public void updateCopupon(Coupon coupon) throws CuponSystemException; // test done - works 
	public Coupon getCoupon(long id) throws CuponSystemException; // test done - works
	public Collection<Coupon> getAllCoupons() throws CuponSystemException; // test done - works ; 
	public Collection<Coupon> getCouponByType(CouponType type) throws CuponSystemException; // test done  - works 

}
