package couponSystemException;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;

public class CuponSystemException extends Exception {

	private static final long serialVersionUID = 1L;

	// Connection pool close exception
	public CuponSystemException(String string) {
		System.out.println(string);
	}

	// INTERUPTED EXCEPTION
	public CuponSystemException(String string, InterruptedException e) {
		System.out.println(string + e);
	}

	// SQL EXCEPTION
	public CuponSystemException(String string, SQLException e) {
		System.out.println(string + e);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		// TODO Auto-generated method stub
		return super.fillInStackTrace();
	}

	@Override
	public synchronized Throwable getCause() {
		// TODO Auto-generated method stub
		return super.getCause();
	}

	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		// TODO Auto-generated method stub
		return super.getStackTrace();
	}

	@Override
	public synchronized Throwable initCause(Throwable arg0) {
		// TODO Auto-generated method stub
		return super.initCause(arg0);
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream arg0) {
		// TODO Auto-generated method stub
		super.printStackTrace(arg0);
	}

	@Override
	public void printStackTrace(PrintWriter arg0) {
		// TODO Auto-generated method stub
		super.printStackTrace(arg0);
	}

	@Override
	public void setStackTrace(StackTraceElement[] arg0) {
		// TODO Auto-generated method stub
		super.setStackTrace(arg0);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	// test
	/*
	 * public void deviation() { System.out.println("Pls do not 0 for deviation"); }
	 */

	public void dataBase() {
		System.out.println("Issue with Data Base connection ");
	}
}
