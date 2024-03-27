package com.controller;
import java.util.Scanner;

import com.dao.CustomerDao;
import com.dao.LoanDao;
import com.entity.Customer;
import com.entity.Loan;
public class LoanController {
	 private LoanDao loanDao = new LoanDao();
	    private CustomerDao customerDao = new CustomerDao();
	    private static Scanner scanner = new Scanner(System.in);

	 

	    
	    public Customer getCustomer(int cid)
	    {
			return customerDao.getCustomer(cid);
	    	
	    }
	    

}
