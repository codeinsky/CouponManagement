package DAO;
import java.util.Collection;

import Beans.Company;
import cuponSystemException.CuponSystemException;

public interface CompanyDAO {
	public void createCompany(Company Company) throws CuponSystemException; // if need to add argument 
	public void removeCompany(Company Company) throws CuponSystemException;
	public void updateCompany(Company Company) throws CuponSystemException;
	public Company getCompany(Long id) throws CuponSystemException;
	public Collection getAllCompanies();// need to insert Collections
	public Collection getCoupons(); 
	public Boolean Login(String compName, String Passowrd);

}
