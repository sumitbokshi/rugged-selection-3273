package com.masai.bean;

public class Accountant {
private String acname;
private String acmail;
private String acpass;

public  Accountant() {
	
}
public Accountant(String acname, String acmail, String acpass) {
	super();
	this.acname = acname;
	this.acmail = acmail;
	this.acpass = acpass;
}
public String getAcname() {
	return acname;
}
public void setAcname(String acname) {
	this.acname = acname;
}

public String getAcmail() {
	return acmail;
}
public void setAcmail(String acmail) {
	this.acmail = acmail;
}
public String getAcpass() {
	return acpass;
}
public void setAcpass(String acpass) {
	this.acpass = acpass;
}
@Override
public String toString() {
	return "Accountant [acname=" + acname + ", acmail=" + acmail + ", acpass=" + acpass + "]";
}
}