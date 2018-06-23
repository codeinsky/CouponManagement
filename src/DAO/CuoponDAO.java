package DAO;

import java.util.Collection;

import beans.Coupon;
import beans.CouponType;
import couponSystemException.CuponSystemException;

public interface CuoponDAO {
	public void createCoupon(Coupon coupon) throws CuponSystemException;
	public void removeCoupon(Coupon coupon) throws CuponSystemException;
	public void updateCopupon(Coupon coupon) throws CuponSystemException;
	public Coupon getCoupon(long id) throws CuponSystemException;
	public Collection getAllCoupons() throws CuponSystemException;
	public Collection getCouponByType(CouponType type);

}
