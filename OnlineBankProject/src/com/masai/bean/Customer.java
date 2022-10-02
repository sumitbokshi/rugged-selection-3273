package com.masai.bean;


public class Customer {
private int account;
private String cust_name;
private String email;
private int password;
private int balance;
private String mobile;
private String address;
public Customer() {
	super();
}
public Customer(int account, String cust_name, String email, int password, int balance, String mobile, String address) {
	super();
	this.account = account;
	this.cust_name = cust_name;
	this.email = email;
	this.password = password;
	this.balance = balance;
	this.mobile = mobile;
	this.address = address;
}
public int getaccount() {
	return account;
}
public void setaccount(int account) {
	this.account = account;
}
public String getcust_name() {
	return cust_name;
}
public void setcust_name(String cust_name) {
	this.cust_name = cust_name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getPassword() {
	return password;
}
public void setPassword(int password) {
	this.password = password;
}
public int getBalance() {
	return balance;
}
public void setBalance(int balance) {
	this.balance = balance;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
@Override
public String toString() {
	return "CustomerDao [account=" + account + ", cust_name=" + cust_name + ", email=" + email + ", password=" + password
			+ ", balance=" + balance + ", mobile=" + mobile + ", address=" + address + "]";
}

}
