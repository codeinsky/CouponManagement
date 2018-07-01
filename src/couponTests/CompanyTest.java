package couponTests;

import DAO.CompanyDBDAO;
import beans.Company;
import couponSystemException.CuponSystemException;

public class CompanyTest {
	public static void main(String[] args) {
		CompanyDBDAO compDAO = new CompanyDBDAO();
		Company first = new Company(3 , "MyCompany" , "1234" , "updateMail@mail.com");
		try {
			System.out.println(compDAO.getAllCompanies());
		} catch (CuponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}


