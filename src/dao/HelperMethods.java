package dao;

import java.util.Collection;

import beans.Coupon;
import couponSystemException.CuponSystemException;
import facades.Facade;

public interface HelperMethods {
	public boolean ifExsist(String sqlTable, String column, String item) throws CuponSystemException;

	public void removeWhere(String sqlTable, String column, long id) throws CuponSystemException;

	public Collection<Long> getCouponsBelongTo(String getColumn, String sqlTable, String whereColumn, long id)
			throws CuponSystemException;

	public Collection<Coupon> getCouponSelected(long id, String select, String refernce) throws CuponSystemException;

	public void buyCoupon(String customerId, String couponId) throws CuponSystemException;

	public void createCopuon(String companyId, String couponId) throws CuponSystemException;

	public Facade logIn(String userType, String userName, String password) throws CuponSystemException;

	public long getId(String typeId) throws CuponSystemException;
}
