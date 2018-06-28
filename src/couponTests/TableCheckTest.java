package couponTests;

import java.sql.SQLException;

import DAO.SqlTableCheck;
import couponSystemException.CuponSystemException;

public class TableCheckTest {
	public static void main(String[] args) throws CuponSystemException, SQLException {
		System.out.println(SqlTableCheck.ifExsist("COUPON", "TITLE", "Cinema"));
	}

}
