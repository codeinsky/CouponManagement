package dbConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;


public class ConTest {

	public static void main(String[] args) {
		Test2();
		
	}
	public static void Test1() throws SQLException {
		Thread tr1 = new Thread(new PoolCheck());
		Thread tr2 = new Thread(new PoolCheck());
		Thread tr3 = new Thread(new PoolCheck());
		Thread tr4 = new Thread(new PoolCheck());
		Thread tr5 = new Thread(new PoolCheck());
		Thread tr6 = new Thread(new PoolCheck());
		Thread tr7 = new Thread(new PoolCheck());
		Thread tr8 = new Thread(new PoolCheck());
		Thread tr9 = new Thread(new PoolCheck());
		Thread tr10 = new Thread(new PoolCheck());
		Thread tr11 = new Thread(new PoolCheck());
		tr1.start();
		tr2.start();
		tr3.start();
		tr4.start();
		tr5.start();
		tr6.start();
		tr7.start();
		tr8.start();
		tr9.start();
		tr10.start();
		tr11.start();
		
	}
	 public static void Test2() {
		 ConnectionPool pool = ConnectionPool.getConnectionPool();
		 Connection con1 = pool.getConnection();
		 Connection con2 = pool.getConnection();
		 Connection con3 = pool.getConnection();
		 Connection con4 = pool.getConnection();
		 Connection con5 = pool.getConnection();
		 Connection con6 = pool.getConnection();
		 Connection con7 = pool.getConnection();
		 Connection con8 = pool.getConnection();
		 Connection con9 = pool.getConnection();
		 Connection con10 = pool.getConnection();
		 Connection con11 = pool.getConnection();
		 Connection con12 = pool.getConnection();
		 
		 
	 }
	
		
	
	}


