package com.controller;

import java.util.Scanner;

import com.dao.HomeLoanDao;
import com.dao.LoanDao;
import com.entity.HomeLoan;

public class HomeLoanController {
	private HomeLoanDao homeLoanDao = new HomeLoanDao();
    private static Scanner sc = new Scanner(System.in);

    public void addHomeLoan() {
        HomeLoan homeLoan = new HomeLoan();
        System.out.println("Enter your Id :");
        homeLoan.setCustomerID(sc.nextInt()); // Set CustomerID
        

        // Input loan details from user
        System.out.print("Enter principal amount: ");
        double principalAmount = sc.nextDouble();
        System.out.print("Enter interest rate: ");
        double interestRate = sc.nextDouble();
        System.out.print("Enter loan term: ");
        int loanTerm = sc.nextInt();

        // Set loan details
        homeLoan.setPrincipalAmount(principalAmount);
        homeLoan.setInterestRate(interestRate);
        homeLoan.setLoanTerm(loanTerm);

        // Input home loan specific details from user
        System.out.print("Enter property address: ");
        String propertyAddress = sc.next();
        System.out.print("Enter property value: ");
        double propertyValue = sc.nextDouble();

        // Set home loan specific details
        homeLoan.setPropertyAddress(propertyAddress);
        homeLoan.setPropertyValue(propertyValue);

        // Call DAO method to add home loan
        
        System.out.print("Are you taking this loan? (Type yes): ");
		if(sc.next().equalsIgnoreCase("yes")) {
		   
			boolean result = homeLoanDao.createHomeLoan(homeLoan);
	        if (result) {
	            System.out.println("Home loan added successfully!");
	        } else {
	            System.out.println("Failed to add home loan.");
	        }
		} else {
		    System.out.println("No Home loan requested.");
		}
        
        
    }
}
