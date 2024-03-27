package com.controller;
import java.util.Scanner;

import com.dao.CustomerDao;
import com.entity.Customer;
public class CustomerController implements CustomerControllerInterface {
	CustomerDao cd = new CustomerDao();
	static Scanner sc = new Scanner(System.in);
	@Override
	public void addCustomer() {
		
		Customer c = new Customer();
		
		System.out.print("Enter your name : ");
		c.setName(sc.next());

		System.out.print("Enter your email : ");
		c.setEmailAddress(null);

		System.out.print("Enter your Address : ");
		c.setAddress(sc.next());
		
		System.out.print("Enter your Phone Number : ");
		c.setPhoneNumber(sc.next());
		
		
		
		
	}

	@Override
	public void updateCustomer() {
		// TODO Auto-generated method stub
		
	}
 
}
