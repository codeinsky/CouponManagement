package DAO;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import couponSystemException.CuponSystemException;

public interface CompanyDAO {
	public void createCompany(Company Company) throws CuponSystemException; // tested , works 
	public void removeCompany(Company Company) throws CuponSystemException; // tested , works 
	public void updateCompany(Company Company) throws CuponSystemException; // tested , works 
	public Company getCompany(long id) throws CuponSystemException; // tested , works
	public Collection<Company> getAllCompanies() throws CuponSystemException ; // tested, works 
	public Boolean Login(String compName, String Passowrd) throws CuponSystemException; //not used yet
	Collection<Coupon> getCoupons(Company Compnay) throws CuponSystemException; // need test with coupons 

}
