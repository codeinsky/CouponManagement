package facades;

import DAO.CompanyDBDAO;
import DAO.CuoponDBDAO;
import DAO.CustomerDBDAO;
import DAO.SqlTableCheck;
import beans.Company;
import couponSystemException.CuponSystemException;

public class AdminFacade {

	CompanyDBDAO  companyDAO = new CompanyDBDAO();
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	CuoponDBDAO  couponDAO = new CuoponDBDAO();
	
	public void CreateCompany(Company company) {
		// check if the Company name already exists 
		try {
			if (SqlTableCheck.ifExsist("COMPANY", "COMP_NAME", company.getCompName())) {           // need test
				System.out.println("Company with name = " + company.getCompName() + "already exsists"); 
			}
			else { // need to check if to do " new company" or will get ready object 
				companyDAO.createCompany(company);
			}
		} catch (CuponSystemException e) {
			
		}
			
	}
}
