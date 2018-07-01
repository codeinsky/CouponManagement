package couponTests;

import java.sql.SQLException;

import DAO.SqlTableUtil;
import couponSystemException.CuponSystemException;

public class TableCheckTest {
	public static void main(String[] args) throws CuponSystemException, SQLException {
		System.out.println(SqlTableUtil.ifExsist("COUPON", "TITLE", "Cinema"));
	}

}
