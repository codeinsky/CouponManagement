package DAO;

import java.util.Collection;

import Beans.Coupon;
import Beans.CouponType;

public interface CuoponDAO {
	public void createCoupon(Coupon coupon);
	public void removeCoupon(Coupon coupon);
	public void updateCopupon(Coupon coupon);
	public Coupon getCoupon(Long id);
	public Collection getAllCoupons();
	public Collection getCouponByType(CouponType type);

}
