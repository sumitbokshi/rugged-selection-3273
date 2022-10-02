package com.masai.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.masai.bean.Customer;
import com.masai.exceptions.CustomerException;
import com.masai.utility.DBUtil;


public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int createAccount(Customer customer) {
		int message=0;
		try(Connection conn= DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("insert into customer(cust_name,email,password,balance,mobile,address) values(?,?,?,?,?,?)");
			
			ps.setString(1, customer.getcust_name());
			ps.setString(2, customer.getEmail());
			ps.setInt(3, customer.getPassword());
			ps.setInt(4, customer.getBalance());
			ps.setString(5, customer.getMobile());
			ps.setString(6, customer.getAddress());
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				

			PreparedStatement ps1=conn.prepareStatement("select account from customer where email=?");
			ps1.setString(1, customer.getEmail());
			
			ResultSet rs=ps1.executeQuery();
			if(rs.next()) {
				message=rs.getInt(1);
			}
		}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return message;
	}

	@Override
	public Customer LoginAccount(String email, int password) throws CustomerException {
		Customer customer=null;
		try(Connection conn=DBUtil.provideConnection()) {
			if(email=="" || password == 0) {
				System.out.println("Please provide all details");
				
			}
			PreparedStatement ps=conn.prepareStatement("select * from customer where email=? and password=?");
			ps.setString(1, email);
			ps.setInt(2, password);
			ResultSet rs=ps.executeQuery();
			BufferedReader scan=new BufferedReader(new InputStreamReader(System.in));
			if(rs.next()) {
				int a=rs.getInt("account");
				String n=rs.getString("cust_name");
				String e=rs.getString("email");
				int p=rs.getInt("password");
				int b=rs.getInt("balance");
				String m=rs.getString("mobile");
				String ad=rs.getString("address");
				
				customer=new Customer(a,n,e,p,b,m,ad);
				int choice=5;
				int transferamount=0;
				int senderaccount=rs.getInt("account");
				int BenificiaryAccount;
				while(true){
					try {

						System.out.println("Welcome "+rs.getString("cust_name"));
						System.out.println("1) Select 1 for Transferring Money");
						System.out.println("2) Select 2 to View Balance");
						System.out.println("3) Select 3 to Logout");
						choice=Integer.parseInt(scan.readLine());
						if(choice==1) {
							System.out.println("Enter Receiver Account Number ");
							BenificiaryAccount=Integer.parseInt(scan.readLine());
							System.out.println("Enter Transfer Amount");
							transferamount=Integer.parseInt(scan.readLine());
						    CustomerDao dao=new CustomerDaoImpl();
						    if(dao.TransferMoney(senderaccount, BenificiaryAccount, transferamount)) {
						    	System.out.println("Money transfered to account number : "+BenificiaryAccount);
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
				
				
			
			}else {
				throw new CustomerException("User does not exist"); 
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CustomerException(e.getMessage());
			
		}
		return customer;
	}
	@Override
	public Customer getCustomerdetailByAccount(int account) throws CustomerException {
		Customer customer=null;
		
		try(Connection conn=DBUtil.provideConnection()){
			
			PreparedStatement ps=conn.prepareStatement("select * from customer where account=?");
					ps.setInt(1, account);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				int a=rs.getInt("account");
				String n =rs.getString("cust_name");
				String e=rs.getString("email");
				int p=rs.getInt("password");
				int b=rs.getInt("balance");
				String m=rs.getString("mobile");
				String ad=rs.getString("address");
				
				
				customer=new Customer(a,n,e,p,b,m,ad);
			}else {
				throw new CustomerException("Customer does not exist with Account no "+account);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomerException(e.getMessage());
		}		
				
	    
		
		
		return customer;
	}

	@Override
	public boolean TransferMoney(int senderaccount, int BenificiaryAccount, int transferamount) throws CustomerException {
		try(Connection conn=DBUtil.provideConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ps =conn.prepareStatement("select * from customer where account=?");
			ps.setInt(1, senderaccount);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("balance")<transferamount) {
					System.out.println("Insufficient Balance");
					return false;
				}
			}
			conn.setSavepoint();
			PreparedStatement ps1= conn.prepareStatement("update customer set balance=balance-? where account=?");
			ps1.setInt(1, transferamount);
			ps1.setInt(2, senderaccount);
			if(ps1.executeUpdate()>0) {
				System.out.println("Amount has been Debited from Your bank Account");
			}
			
			PreparedStatement ps2= conn.prepareStatement("update customer set balance=balance+? where account=?");
			ps2.setInt(1, transferamount);
			ps2.setInt(2, BenificiaryAccount);
			if(ps2.executeUpdate()>0) {
				System.out.println("Amount has been Credited to the benificiary bank Account");
			}
			conn.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return false;
		
	}

	@Override
	public void getBalance(int acNo) throws CustomerException {
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from customer where account=?");
			ps.setInt(1, acNo);
			ResultSet rs=ps.executeQuery();
			System.out.println("-----------------------------------------------");
			System.out.printf("%12s %10s %10s\n" ,"Account NO","Name","Balance");
			while(rs.next()) {
				System.out.printf("%12d %10s %10d.00\n",rs.getInt("account"),rs.getString("cust_name"),rs.getInt("balance"));
				System.out.println("-------------------------------------------");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}



	@Override
	public List<Customer> getAllCustomerDetails() throws CustomerException {
		List<Customer> customers =new ArrayList<>();
		
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from customer");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				int a=rs.getInt("account");
				String n =rs.getString("cust_name");
				String e=rs.getString("email");
				int p=rs.getInt("password");
				int b=rs.getInt("balance");
				String m=rs.getString("mobile");
				String ad=rs.getString("address");
				Customer customer=new Customer(a,n,e,p,b,m,ad);
				customers.add(customer);
			}
		} catch (SQLException e) {
		
			throw new CustomerException(e.getMessage());
		}
		
		if(customers.size()==0) {
			throw new CustomerException("No Customer found...");
		}
		
		return customers;
	}

	@Override
	public String DeleteCustomerDetailByAccount(int account) throws CustomerException {
		String message="Given Account Number Does Not Exist to Delete";
		try(Connection conn= DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("delete from customer where account=?");
			
			ps.setInt(1, account);
			
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				
			message=" Account Deleted  Successfully !";
			
			}
			
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String UpdateCustomerDetailByAccount(int account,String mobile,String address) throws CustomerException {
		String message="Unable to update your account details";
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("update customer set mobile=?,address=? where account=?");
			ps.setString(1,mobile);
			ps.setString(2, address);
			ps.setInt(3, account);
			int y=ps.executeUpdate();
			if(y>0) {
				message="Account Details has been Updated Successfully";
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return message;
	}

}

