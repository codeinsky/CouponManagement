package DAO;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import couponSystemException.CuponSystemException;

public interface CompanyDAO {
	public void createCompany(Company Company) throws CuponSystemException; 
	public void removeCompany(Company Company) throws CuponSystemException;
	public void updateCompany(Company Company) throws CuponSystemException;
	public Company getCompany(long id) throws CuponSystemException;
	public Collection<Company> getAllCompanies() throws CuponSystemException ;
	public Boolean Login(String compName, String Passowrd) throws CuponSystemException;
	Collection<Coupon> getCoupons(Company Compnay) throws CuponSystemException;

}
