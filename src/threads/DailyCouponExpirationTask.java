package threads;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import couponSystemException.CouponSystemException;
import dao.HelperMethodsDAO;
import dbConnectionPool.ConnectionPool;

// TODO: Auto-generated Javadoc
/**
 * Thread used for removing (delete expired coupons).
 * Compares each coupon end Date to the current date if coupon 
 * is expired will be removed from:
 * 		- 
 * 		- 
 * 		- 
 * Runs every 24h. Could be adjusted with sleep Argument. 
 * The Class DailyCouponExpirationTask.
 */
public class DailyCouponExpirationTask implements Runnable {

	/** The quit. */
	boolean quit = false;

	/**
	 * Instantiates a new daily coupon expiration task.
	 *
	 * @param quit the quit - Quit flag is used to stop the thread looping 
	 */
	public DailyCouponExpirationTask(boolean quit) {
		super();
		this.quit = quit;

	}

	/**
	 * Checks if is quit.
	 * Flag is used to stop the thread work. 
	 * Quit is true - stops the thread 
	 * 
	 * @return true, if is quit
	 */
	public boolean isQuit() {
		return quit;
	}

	/**
	 * Sets the quit.
	 *
	 * @param quit the new quit
	 */
	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		HelperMethodsDAO helper = new HelperMethodsDAO();
		while (quit == false) {
			Collection<Long> expiredCouponsIds = new HashSet<Long>();
			ConnectionPool pool = ConnectionPool.getConnectionPool();
			long time = System.currentTimeMillis();
			Date date = new Date(time);
			System.out.println(date);
			String sql = "SELECT ID FROM COUPON WHERE END_DATE < '" + date + "'";
			Connection con;
			try {
				con = pool.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					expiredCouponsIds.add(rs.getLong("ID"));
				}
				System.out.println(expiredCouponsIds);
				pool.returnConnection(con);
				for (long id : expiredCouponsIds) {
					helper.removeWhere("COUPON", "ID", id);
					helper.removeWhere("COMPANY_COUPON", "COUPON_ID", id);
					helper.removeWhere("CUSTOMER_COUPON", "COUPON_ID", id);
				}
			} catch (CouponSystemException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				Thread.sleep(10000); // delay between each "Clean" 24h 
			} catch (InterruptedException e) {
				try {
					throw new CouponSystemException("Expiration Coupons cleaner failed", e);
				} catch (CouponSystemException e1) {

				}
			}
		}
	}

}
