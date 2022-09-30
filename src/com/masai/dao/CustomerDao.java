package com.masai.dao;

import com.masai.bean.Customer;
import com.masai.exceptions.CustomerException;

public interface CustomerDao {
public String createAccount(Customer customer);
public Customer LoginAccount(String email,int password)throws CustomerException;
public boolean TransferMoney(int senderaccount,int BenificiaryAccount,int transferamount )throws CustomerException;
public void getBalance(int acNo)throws CustomerException;
}
