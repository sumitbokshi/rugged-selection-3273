package com.masai.Banking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.masai.bean.Customer;

import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;

import com.masai.exceptions.CustomerException;


public class Bank {
public static void main(String[] args)throws IOException {
	BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
	int choice;
	String cname="";
	String email="";
	int password;
	int balance;
	String mobile="";
	String address="";
	
	while(true) {
		System.out.println("***** Welcome to the OnlineBanking Portal ******");
		System.out.println();
	
		try {
			System.out.println("SELECT  1 for Customer and 2 for Accountant");
			choice =Integer.parseInt(sc.readLine());
			switch(choice) {
			case 1:
				while(true) {
					
				System.out.println();
					
//					System.out.println("1) Create Account");
					System.out.println("1)Login Using Credentials");
					
					try {

						System.out.println("Press 1 to create Account or Press 2 to Login||<--");
						 choice=Integer.parseInt(sc.readLine());
						 
						switch(choice) {
						case 1:
							try {
								System.out.println("Enter Your Name");
								 cname=sc.readLine();
								
								System.out.println("Enter Your Email");
								 email=sc.readLine();
						         System.out.println("Enter Your Password");
						         password=Integer.parseInt(sc.readLine());
						        System.out.println("Enter Your Balance");
						        balance=Integer.parseInt(sc.readLine());
						        System.out.println("Enter Your Mobile");
						         mobile=sc.readLine();
						        System.out.println("Enter Your Address");
						         address=sc.readLine();
						        CustomerDao cd=new CustomerDaoImpl();
						        Customer customers=new Customer();
						        customers.setCname(cname);
						        customers.setEmail(email);
						        customers.setPassword(password);
						        customers.setBalance(balance);
						        customers.setMobile(mobile);
						        customers.setAddress(address);
						        String result=cd.createAccount(customers);
						        
						        
						        if(result != null) {
						        	System.out.println("Account created Successfully");
						        }else {
						        	System.out.println("Account creation failed");
						        }
						        
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
								
							}
							break;
						case 2:
							try {
//								Scanner sc=new Scanner(System.in);
								
								System.out.println("Enter email:");
								 email =sc.readLine();
								
								System.out.println("Enter Password:");
								 password =Integer.parseInt(sc.readLine());;
								
								CustomerDao dao=new CustomerDaoImpl();
								try {
									Customer customer =dao.LoginAccount(email, password);
									System.out.println("Welcome :"+customer.getCname());
								} catch (CustomerException e) {
									// TODO Auto-generated catch block
								System.out.println(e.getMessage());
								}
							} catch (Exception e) {
								System.out.println("Enter Valid Data");
								
							}
							break;
							default:
								System.out.println("Invalid Entry");
						}
						
						
						
						}
					 
					catch (Exception e) {
						System.out.println("Enter Valid Entry!");
//						System.out.println(e.getMessage());
					}
					
					
				}
			default :
				System.out.println("Invalid Choice");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Enter the Valid Entry");
		}
	}
	

}
}
