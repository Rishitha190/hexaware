package com.controller;
import java.util.Scanner;

import com.dao.CarLoanDao;

import com.entity.CarLoan;
public class CarLoanController {

	private CarLoanDao carLoanDao = new CarLoanDao();
	private static Scanner sc = new Scanner(System.in);

	public void addCarLoan() {
		CarLoan carLoan = new CarLoan();
		System.out.println("Enter your Id :");
		carLoan.setCustomerID(sc.nextInt()); // Set CustomerID
		// Input loan details from user
		System.out.print("Enter principal amount: ");
		double principalAmount = sc.nextDouble();
		System.out.print("Enter interest rate: ");
		double interestRate = sc.nextDouble();
		System.out.print("Enter loan term: ");
		int loanTerm = sc.nextInt();

		// Set loan details
		carLoan.setPrincipalAmount(principalAmount);
		carLoan.setInterestRate(interestRate);
		carLoan.setLoanTerm(loanTerm);

		// Input car loan specific details from user
		System.out.print("Enter car model: ");
		String carModel = sc.next();
		System.out.print("Enter car value: ");
		double carValue = sc.nextDouble();

		// Set car loan specific details
		carLoan.setCarModel(carModel);
		carLoan.setCarValue(carValue);

		// Call DAO method to add car loan
		System.out.print("Are you taking this loan? (Type yes): ");
		if(sc.next().equalsIgnoreCase("yes")) {
			
			    
		
		    boolean result = carLoanDao.createCarLoan(carLoan);
		
		    if (result) {
		        System.out.println("Car loan added successfully!");
		    } else {
		        System.out.println("Failed to add car loan.");
		    }
		} else {
		    System.out.println("No car loan requested.");
		}
		
		
	}

}
