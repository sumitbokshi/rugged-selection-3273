package com.masai.usecases;

import java.util.Scanner;

import com.masai.bean.Customer;
import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;

public class CreateAccountUseCase {
public static void main(String[] args) {
	Scanner sc= new Scanner(System.in);
	CustomerDaoImpl stm= new CustomerDaoImpl();
	
	boolean status = true;
	
	while(status) {
		System.out.println("\n Choose Option only press Number.. \n");
		System.out.println("1. Create Account \r\n"
				+ "	2. update his details.\r\n"
				+ "	3. See all the available Accounts Active\r\n"
				+ " 4. Exit.\r\n");
		
		System.out.println("=================================================== \n");
		int num=sc.nextInt();
		
		switch (num) {
		case 1: {
			System.out.println("Register using Account Details ");
			
			System.out.println("Enter Student Email id : ");
			String email=sc.next();
			
			System.out.println("Enter Student Password : ");
			String password=sc.next();
			
			System.out.println("Enter Course Name : ");
			String courseName=sc.next();
			
			System.out.println("Enter Batch name : ");
			String batch=sc.next();
			
			System.out.println("Enter Course id : ");
			int id=sc.nextInt();
			
			
		}
}
}
}
}