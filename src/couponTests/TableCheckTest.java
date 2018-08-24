package couponTests;

import java.sql.SQLException;

import couponSystemException.CuponSystemException;
import dao.SqlTableUtil;

public class TableCheckTest {
	public static void main(String[] args) throws CuponSystemException, SQLException {
		System.out.println(SqlTableUtil.ifExsist("COUPON", "TITLE", "Cinema"));
	}

}
