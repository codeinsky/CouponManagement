package CuponTests;

import Beans.Company;
import DAO.CompanyDBDAO;
import cuponSystemException.CuponSystemException;

public class DaoTest {

	public static void main(String[] args) {
		CompanyDBDAO companyDB = new CompanyDBDAO();
		Company star = new Company(2, "Star", "1234", "start@gamil.com"); 
//		try {
//			companyDB.createCompany(star);
//		} catch (CuponSystemException e) {
//			
//		}
			try {
				companyDB.removeCompany(star);
			} catch (CuponSystemException e) {
				
			}
	}

}
