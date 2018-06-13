package DAO;
import Beans.Company;

public interface CompanyDAO {
	public void createCompany(Company Company); // if need to add argument 
	public void removeCompany(Company Company);
	public void updateCompany();
	public Company getCompany();
	public  void getAllCompanies();// need to insert Collections
	public  void getCoupons(); 
	public  void Login(String compName, String Passowrd);

}
