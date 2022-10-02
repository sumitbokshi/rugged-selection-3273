package com.masai.exceptions;

public class AccountantException extends Exception{

	private static final long serialVersionUID = 1L;
public AccountantException() {
	super();
}
public AccountantException(String error) {
	super(error);
}
}
