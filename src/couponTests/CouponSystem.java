package couponTests;
import couponSystemException.CuponSystemException;
import dao.CompanyDBDAO;
import dao.CuoponDBDAO;
import dao.CustomerDBDAO;
import dao.HelperMethodsDAO;
import dbConnectionPool.ConnectionPool;
import facades.AdminFacadeF;
import facades.CompanyFacadeF;
import facades.CustomerFacadeF;
import facades.Facade;
import threads.DailyCouponExpirationTask;

public class CouponSystem {
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	CuoponDBDAO clientDBDAO = new CuoponDBDAO();
	CompanyDBDAO adminDAO = new CompanyDBDAO();
	HelperMethodsDAO helper = new HelperMethodsDAO();
	Thread couponCleaner = new Thread(new DailyCouponExpirationTask(false));
	private CouponSystem() {};
	private static CouponSystem instance;
	public  static CouponSystem getInstance() {
		if (instance == null) {
			instance = new CouponSystem();
		}
		return instance;
	}

 public Facade logIn(String userType , String userName, String password) {
	 Facade facade =null;
	 try {
		facade = helper.logIn(userType, userName, password);
	} catch (CuponSystemException e) {
		e.getMessage();
	}
//	 // casting the facade for needed type ???
//	 switch (userType) {
//	 case ("admin"): facade = (AdminFacadeF)facade;
//	 break ;
//	 case ("company"): facade  = (CompanyFacadeF)facade;
//	 break; 
//	 case ("customer"): facade = (CustomerFacadeF)facade;
//	 }
	 return facade;
	 
 }
 public void shutDown() {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		try {
			pool.closeConnections();
		} catch (CuponSystemException e) {
			e.getMessage();
		}
	}
	
	

}
