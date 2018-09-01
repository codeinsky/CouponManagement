package threads;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import couponSystemException.CuponSystemException;
import dao.HelperMethodsDAO;
import dbConnectionPool.ConnectionPool;

public class DailyCouponExpirationTask implements Runnable {

	boolean quit = false;

	public DailyCouponExpirationTask(boolean quit) {
		super();
		this.quit = quit;

	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

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
			} catch (CuponSystemException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				Thread.sleep(10000); // delay between each "Clean" 24h 
			} catch (InterruptedException e) {
				try {
					throw new CuponSystemException("Expiration Coupons cleaner failed", e);
				} catch (CuponSystemException e1) {

				}
			}
		}
	}

}
