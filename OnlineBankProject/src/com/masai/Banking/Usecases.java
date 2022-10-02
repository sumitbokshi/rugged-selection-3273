package com.masai.Banking;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.masai.bean.Customer;
import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;
import com.masai.exceptions.CustomerException;



public class Usecases {
public static void main(String[] args)throws IOException {
	BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
	int choice;
	String cust_name="";
	String email="";
	int password;
	int balance;
	String mobile="";
	String address="";
	int account;
	String UserName="";
	int Password;
while(true) {
		System.out.println("********* Welcome to Online Banking Portal*********");
		System.out.println();
		
		try {
			System.out.println("Select 1 for Accountant and 2 for Customer");
			choice =Integer.parseInt(sc.readLine());
			switch(choice) {
			case 2:
				while(true) {
					
				System.out.println();
					

					System.out.println("1)Login Account");
					
					try {

						System.out.println("Select 1 to LogIn");
						 choice=Integer.parseInt(sc.readLine());
						 
						switch(choice) {
						
						case 1:
							try {
								
					        	System.out.println("Enter email:");
							 email =sc.readLine();
							
							System.out.println("Enter Password:");
							 password =Integer.parseInt(sc.readLine());;
							
							CustomerDao dao=new CustomerDaoImpl();
							try {
								Customer customer =dao.LoginAccount(email, password);
								System.out.println("Welcome"+customer.getcust_name());
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

					}
					
					
				}
			case 1:
				System.out.println("Enter Login Details for Accountant");
				System.out.println("========================================");
				System.out.println("\n");
				System.out.println("Enter UserName");
				UserName=sc.readLine();
				System.out.println("Enter Password");
				Password=Integer.parseInt(sc.readLine());
				if(UserName.equals("sumit") && Password==12345) {
					System.out.println("Accountant Login Successfully!");
					System.out.println("\n");
					
					while(true) {
						System.out.println("Accountant Operation");
						System.out.println("==================================");
						System.out.println("\n");
						System.out.println("Press 1) Create New Customer Details");
						System.out.println("Press 2) Editing Account Details");
						System.out.println("Press 3) Removing the Account by using account Number");
						System.out.println("Press 4  Viewing Specific Account Number");
						System.out.println("Press 5) Viewing all the account details available");
						System.out.println("Press 6) Exit !!");
						
						choice =Integer.parseInt(sc.readLine());
						
						switch(choice) {
						case 1:
							try {
								System.out.println("Enter Name");
								 cust_name=sc.readLine();
								
								System.out.println("Enter Email");
								 email=sc.readLine();
						         System.out.println("Enter Password");
						         password=Integer.parseInt(sc.readLine());
						        System.out.println("Enter Balance");
						        balance=Integer.parseInt(sc.readLine());
						        System.out.println("Enter Mobile No. ");
						        int mn=Integer.parseInt(sc.readLine());
						        mobile=String.valueOf(mn);
						        System.out.println("Enter Address");
						         address=sc.readLine();
						        
						        Customer customers=new Customer();
						        customers.setcust_name(cust_name);
						        customers.setEmail(email);
						        customers.setPassword(password);
						        customers.setBalance(balance);
						        customers.setMobile(mobile);
						        customers.setAddress(address);
						        CustomerDao cd=new CustomerDaoImpl();
						        int result=cd.createAccount(customers);
						        
						        
						        if(result >0) {
						        	System.out.println("Account created Successfully");
						        	System.out.println("Account Number Generated : "+result);
						        }else {
						        	System.out.println("Failed");
						        }
						        
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
								
							}
							break;
						case 2:
		                        try {
								System.out.println("Enter customer Account Number:");
							         account=Integer.parseInt(sc.readLine());
								System.out.println("Enter Mobile Number to be Updated");
								mobile=sc.readLine();
								System.out.println("Enter Address to be Updated");
								address=sc.readLine();
								CustomerDao dao=new CustomerDaoImpl();
								
								
								try {
									String customer = dao.UpdateCustomerDetailByAccount(account,mobile,address);
									System.out.println(customer);
								} catch (CustomerException se) {
								
									System.out.println(se.getMessage());
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							try {
								
								
								System.out.println("Enter customer Account Number:");
							         account=Integer.parseInt(sc.readLine());
								
								CustomerDao dao=new CustomerDaoImpl();
								
								
								try {
									String customer = dao.DeleteCustomerDetailByAccount(account);
									System.out.println(customer);
								} catch (CustomerException se) {
								
									System.out.println(se.getMessage());
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							break;
						case 4:
							try {
								
								
								System.out.println("Enter customer Account Number:");
							         account=Integer.parseInt(sc.readLine());
								
								CustomerDao dao=new CustomerDaoImpl();
								
								
								try {
									Customer customer = dao.getCustomerdetailByAccount(account);
									System.out.println(customer);
								} catch (CustomerException se) {
								
									System.out.println(se.getMessage());
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							break;
						case 5:
							CustomerDao dao=new CustomerDaoImpl();
							try {
								List<Customer> customers=dao.getAllCustomerDetails();
								customers.forEach(s->{
									System.out.println("Customer Account :"+s.getaccount());
									System.out.println("Customer Name:"+s.getcust_name());
									System.out.println("Customer Email :"+s.getEmail());
									System.out.println("Customer Balance :"+s.getBalance());
									System.out.println("Customer Mobile :"+s.getMobile());
									System.out.println("Customer Address :"+s.getAddress());
									System.out.println("=======================");
									
								});
							} catch (CustomerException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 6:
							break;
							default:
								System.out.println("Invalid Number Selected");
						}
				if(choice==6) {
					System.out.println("Sucessfully Logged Out");
					break;
				}
					}
					
			

					
				}else {
					System.out.println("Either the UserName or Password is Wrong");
				}
				
				break;
			default :
				System.out.println("Invalid Entry");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Enter Valid Entry");
		}
	}
	
	

}
}
