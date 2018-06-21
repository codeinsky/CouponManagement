package CuponTests;

import Beans.Company;
import DAO.CompanyDBDAO;
import cuponSystemException.CuponSystemException;

public class DaoTest {

	public static void main(String[] args) {
		CompanyDBDAO companyDB = new CompanyDBDAO();
//		Company star = new Company(4, "Star", "1234", "start@gamil.com"); 
//		try {
//			companyDB.createCompany(star);
//		} catch (CuponSystemException e) {}
//		
//		Company update = new Company(2, "Start", "1234", "updated@gmail.com");
//		try {
//			companyDB.updateCompany(update);
//		} catch (CuponSystemException e) {
//		}
		try {
			Company get = companyDB.getCompany((long) 2);
			System.out.println(get.toString());
		} catch (CuponSystemException e) {
			
		}
	}

}
