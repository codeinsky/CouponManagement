package dao;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import couponSystemException.CuponSystemException;

public interface CompanyDAO {
	public void createCompany(Company company) throws CuponSystemException; // tested , works

	public void removeCompany(Company company) throws CuponSystemException; // tested , works

	public void updateCompany(Company company) throws CuponSystemException; // tested , works

	public Company getCompany(long id) throws CuponSystemException; // tested , works

	public Collection<Company> getAllCompanies() throws CuponSystemException; // tested, works

	public Boolean Login(String compName, String passowrd) throws CuponSystemException; // not used yet

	Collection<Coupon> getCoupons(Company compnay) throws CuponSystemException; // need test with coupons

}
