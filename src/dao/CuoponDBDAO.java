package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import beans.Coupon;
import beans.CouponType;
import couponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class CuoponDBDAO implements CuoponDAO {

	@Override
	public void createCoupon(Coupon coupon) throws CuponSystemException {
		String sql = "INSERT INTO COUPON(ID, TITLE , START_DATE, END_DATE, AMOUNT, TYPE , MESSAGE, PRICE , IMAGE) VALUES(?,?,?,?,?,?,?,?,?)";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			PreparedStatement state = con.prepareStatement(sql);
			state.setLong(1, coupon.getId());
			state.setString(2, coupon.getTitle());
			state.setDate(3, (Date) coupon.getStartDate());
			state.setDate(4, (Date) coupon.getEndDate());
			state.setInt(5, coupon.getAmount());
			state.setString(6, coupon.getType().toString());
			state.setString(7, coupon.getMessage());
			state.setDouble(8, coupon.getPrice());
			state.setString(9, coupon.getImage());
			state.execute();
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to add new coupon to Data Base", e);
		} finally {
			pool.returnConnection(con);
		}

	}

	@Override
	public void removeCoupon(Coupon coupon) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "DELETE FROM COUPON WHERE ID= " + coupon.getId();
		try {
			Statement state = con.createStatement();
			state.executeUpdate(sql);
			System.out.println("Coupon with id=" + coupon.getId() + " was removed");
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to remove coupon from DATA BASE ", e);
		} finally {
			pool.returnConnection(con);
		}

	}

	@Override
	public void updateCopupon(Coupon coupon) throws CuponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "UPDATE COUPON SET  " + "TITLE      = ? ," + "START_DATE = ? , " + "END_DATE = ? , "
				+ "AMOUNT   = ?, " + "TYPE     = ? , " + "MESSAGE  = ? , " + "PRICE    = ? , " + "IMAGE    = ? "
				+ "WHERE  ID=" + coupon.getId();
		try {
			PreparedStatement state = con.prepareStatement(sql);
			state.setString(1, coupon.getTitle());
			state.setDate(2, coupon.getStartDate());
			state.setDate(3, coupon.getEndDate());
			state.setInt(4, coupon.getAmount());
			state.setString(5, coupon.getType().toString());
			state.setString(6, coupon.getMessage());
			state.setDouble(7, coupon.getPrice());
			state.setString(8, coupon.getImage());
			state.executeUpdate();
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to update coupon with id = " + coupon.getId(), e);
		} finally {
			pool.returnConnection(con);
		}

	}

	@Override
	public Coupon getCoupon(long id) throws CuponSystemException {
		Coupon coupon = new Coupon();
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String query = "SELECT * FROM COUPON WHERE ID=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				coupon.setId(id);
				coupon.setTitle(rs.getString("TITLE"));
				coupon.setStartDate(rs.getDate("START_DATE"));
				coupon.setEndDate(rs.getDate("END_DATE"));
				coupon.setAmount(rs.getShort("AMOUNT"));
				coupon.setType(CouponType.valueOf(rs.getString("TYPE")));
				coupon.setMessage(rs.getString("MESSAGE"));
				coupon.setPrice(rs.getDouble("PRICE"));
				coupon.setImage(rs.getString("IMAGE"));

			} else {
				System.out.println("Coupon with id = " + id + "does not exsist in the system");
			}
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to get coupon with id = " + id + " from the data base", e);
		} finally {
			pool.returnConnection(con);
		}
		return coupon;
	}

	@Override
	public Collection<Coupon> getAllCoupons() throws CuponSystemException {
		Collection<Coupon> couponList = new HashSet<Coupon>();
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String query = "SELECT * FROM COUPON";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				couponList.add(new Coupon(rs.getInt("ID"), rs.getString("TITLE"), rs.getDate("START_DATE"),
						rs.getDate("END_DATE"), rs.getInt("AMOUNT"), CouponType.valueOf(rs.getString("TYPE")),
						rs.getString("MESSAGE"), rs.getDouble("PRICE"), rs.getString("IMAGE")));
			}
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to get coupons list", e);
		} finally {
			pool.returnConnection(con);
		}

		return couponList;

	}

	@Override //
	public Collection<Coupon> getCouponByType(CouponType type) throws CuponSystemException {
		Collection<Coupon> couponsByType = new HashSet<Coupon>();
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String query = "SELECT * FROM COUPON WHERE TYPE=?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, type.toString());
			ResultSet rs = st.executeQuery();
			if (!rs.next()) {
				System.out.println("Coupons of  " + type + "  type does not exists in the system");
			} else
				do {

					couponsByType.add(new Coupon(rs.getInt("ID"), rs.getString("TITLE"), rs.getDate("START_DATE"),
							rs.getDate("END_DATE"), rs.getInt("AMOUNT"), CouponType.valueOf(rs.getString("TYPE")),
							rs.getString("MESSAGE"), rs.getDouble("PRICE"), rs.getString("IMAGE")));
				} while (rs.next());
		} catch (SQLException e) {
			throw new CuponSystemException("Failed to get coupons from data base of type  " + type, e);
		} finally {
			pool.returnConnection(con);
		}

		return couponsByType;
	}

}
