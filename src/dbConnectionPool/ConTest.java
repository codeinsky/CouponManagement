package dbConnectionPool;



public class ConTest {

	public static void main(String[] args) {
	ConnectionPool pool = ConnectionPool.getConnectionPool();
	Thread tr1 = new PoolCheck();
	Thread tr2 = new PoolCheck();
	Thread tr3 = new PoolCheck();
	Thread tr4 = new PoolCheck();
	Thread tr5 = new PoolCheck();
	Thread tr6 = new PoolCheck();
	Thread tr7 = new PoolCheck();
	Thread tr8 = new PoolCheck();
	Thread tr9 = new PoolCheck();
	Thread tr10 = new LongThread();
	Thread tr11 = new LastThread();
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

	
		
	
	}


