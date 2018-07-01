package beans;

import java.util.ArrayList;
import java.util.Collection;

public class Company {
	private long id; 
	private String compName; 
	private String password;
	private String email;
	private Collection<Coupon> coupon = new ArrayList<Coupon>(); 
	
	public Company() {
		super();
	}
	public Company(long id, String compName, String password, String email) { // if need construct to collection ??
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	//	this.coupon = coupon;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupon=" + coupon + "]";
	}
	

}
