package facades;

import DAO.CompanyDBDAO;
import DAO.CuoponDBDAO;
import DAO.CustomerDBDAO;

public class AdminFacade {

	CompanyDBDAO  companyDAO = new CompanyDBDAO();
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	CuoponDBDAO  couponDAO = new CuoponDBDAO();
	
	
}
