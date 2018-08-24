package couponTests;
import couponSystemException.CuponSystemException;
import dao.CompanyDBDAO;
import dao.CuoponDBDAO;
import dao.CustomerDBDAO;
import dbConnectionPool.ConnectionPool;
import threads.DailyCouponExpirationTask;

public class MainTest {
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	CuoponDBDAO clientDBDAO = new CuoponDBDAO();
	CompanyDBDAO adminDAO = new CompanyDBDAO();
	Thread couponCleaner = new Thread(new DailyCouponExpirationTask(false));
	private MainTest() {};
	private MainTest instance;
	public  MainTest getInstance() {
		if (instance == null) {
			instance = new MainTest();
		}
		return instance;
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
