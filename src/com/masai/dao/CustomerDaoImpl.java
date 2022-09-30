package com.masai.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.masai.bean.Customer;
import com.masai.exceptions.CustomerException;

import com.masai.utility.DBUtil;
import com.mysql.cj.xdevapi.DbDoc;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public String createAccount(Customer customer) {
		String message="Account Not created";
		try(Connection conn= DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("insert into customer(cname,email,password,balance,mobile,address) values(?,?,?,?,?,?)");
			
			ps.setString(1, customer.getCname());
			ps.setString(2, customer.getEmail());
			ps.setInt(3, customer.getPassword());
			ps.setInt(4, customer.getBalance());
			ps.setString(5, customer.getMobile());
			ps.setString(6, customer.getAddress());
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				
			message=" Account created  Successfully !";
			
			}
			
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public Customer LoginAccount(String email, int password) throws CustomerException {
		Customer customer=null;
		try(Connection conn=DBUtil.provideConnection()) {
			if(email=="" || password == 0) {
				System.out.println("All Fields are Required");
				
			}
			PreparedStatement ps=conn.prepareStatement("select * from customer where email=? and password=?");
			ps.setString(1, email);
			ps.setInt(2, password);
			ResultSet rs=ps.executeQuery();
			BufferedReader scan=new BufferedReader(new InputStreamReader(System.in));
			if(rs.next()) {
				int a=rs.getInt("caccount");
				String n=rs.getString("cname");
				String e=rs.getString("email");
				int p=rs.getInt("password");
				int b=rs.getInt("balance");
				String m=rs.getString("mobile");
				String ad=rs.getString("address");
				
				customer=new Customer(a,n,e,p,b,m,ad);
			
				int choice=5;
				int transferamount=0;
				int senderaccount=rs.getInt("caccount");
				int BenificiaryAccount;
				while(true){
					try {
						System.out.println("Welcome "+rs.getString("cname"));
						System.out.println("1) Transfer Money");
						System.out.println("2) View Balance");
						System.out.println("3) Logout");
						System.out.println("Press 1 to money Tranfer or Press 2 to view Blance or Press 3 to Logout");
						choice=Integer.parseInt(scan.readLine());
						if(choice==1) {
							System.out.println("Enter Beneficiary or Receiver Account Number ");
							BenificiaryAccount=Integer.parseInt(scan.readLine());
							System.out.println("Enter Transfer Amount");
							transferamount=Integer.parseInt(scan.readLine());
						    CustomerDao dao=new CustomerDaoImpl();
						    if(dao.TransferMoney(senderaccount, BenificiaryAccount, transferamount)) {
						    	System.out.println("Money transfer SuccessFully to account number "+BenificiaryAccount);
						    }else {
						    	System.out.println("Money tranfer Fail");
						    }
						}else if(choice==2) {
							CustomerDao dao=new CustomerDaoImpl();
							dao.getBalance(senderaccount);
						}else if(choice==3) {
							break;
						}else {
							System.out.println("Enter the Valid Input Field");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					System.out.println(e2.getMessage());
					}
				}
				
				
				// End Of Logic
			}else {
				throw new CustomerException("Either email is wrong or password"); 
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CustomerException(e.getMessage());
			
		}
		return customer;
	}

	@Override
	public boolean TransferMoney(int senderaccount, int BenificiaryAccount, int transferamount) throws CustomerException {
		try(Connection conn=DBUtil.provideConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ps =conn.prepareStatement("select * from customer where caccount=?");
			ps.setInt(1, senderaccount);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("balance")<transferamount) {
					System.out.println("Insufficient Balance");
					return false;
				}
			}
			Statement st= conn.createStatement();
			// Debit part
			conn.setSavepoint();
			PreparedStatement ps1= conn.prepareStatement("update customer set balance=balance-?");
			ps1.setInt(1, transferamount);
			if(ps1.executeUpdate()>0) {
				System.out.println("Amount has been Debited from Your bank Account");
			}
			// Credit Part
			PreparedStatement ps2= conn.prepareStatement("update customer set balance=balance+?");
//			ps1.setInt(1, transferamount);
//			if(ps1.executeUpdate()>0) {
//				System.out.println("Amount has been Credited to the benificiary bank Account");
//			}
			conn.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			conn.rollback();
		}
		return false;
		
	}

	@Override
	public void getBalance(int acNo) throws CustomerException {
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from customer where caccount=?");
			ps.setInt(1, acNo);
			ResultSet rs=ps.executeQuery();
			System.out.println("-----------------------------------------------");
			System.out.printf("%12s %10s %10s\n" ,"Account NO","Name","Balance");
			while(rs.next()) {
				System.out.printf("%12d %10s %10d.00\n",rs.getInt("caccount"),rs.getString("cname"),rs.getInt("balance"));
				System.out.println("-------------------------------------------");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
