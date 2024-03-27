package com.controller;
import java.util.Iterator;
import java.util.List;


import com.dao.LoanDao;
import com.entity.Customer;
import com.entity.Loan;

import com.exception.InvalidLoanException;

public class ILoanRepository {
	LoanController lc = new LoanController();
	HomeLoanController hlc;
	CarLoanController clc;
	LoanDao loanDao;

	public ILoanRepository() {

		loanDao = new LoanDao(); // Assuming LoanDao is already implemented
	}

	public void applyLoa(int type) {
		
		if (type ==1)
		{
			clc = new CarLoanController();
			clc.addCarLoan();
		}
		else
		{
			hlc = new HomeLoanController();
			hlc.addHomeLoan();
		}
				
		
	}

	public double calculateInterest(int loanId) throws InvalidLoanException {
		
		Loan loan = loanDao.getLoanById(loanId);
		
		if (loan == null) {
			throw new InvalidLoanException("Loan not found with ID: " + loanId);
		}
		double principalAmount = loan.getPrincipalAmount();
		double interestRate = loan.getInterestRate();
		int loanTerm = loan.getLoanTerm();
		return (principalAmount * interestRate * loanTerm) / 12;
	}

	public void loanStatus(int loanId) throws InvalidLoanException {
		Loan loan = loanDao.getLoanById(loanId);
		if (loan == null) {
			throw new InvalidLoanException("Loan not found with ID: " + loanId);
		}
		Customer customer = loan.getCustomer();
		if (customer == null) {
			throw new InvalidLoanException("Customer information not found for loan ID: " + loanId);
		}
		double creditScore = customer.getCreditScore();
		if (creditScore > 650) {
			loanDao.updateLoan(loanId, "approved");
			System.out.println("Congratulaion your loan with id :" + loanId + "is approved");
		} else {
			loanDao.updateLoan(loanId, "rejected");
			System.out.println("Soryy your loan with id :" + loanId + "is rejected");
		}
	}

	public double calculateEMI(int loanId) throws InvalidLoanException {
		Loan loan = loanDao.getLoanById(loanId);
		if (loan == null) {
			throw new InvalidLoanException("Loan not found with ID: " + loanId);
		}
		double principalAmount = loan.getPrincipalAmount();
		double interestRate = loan.getInterestRate();
		int loanTerm = loan.getLoanTerm();
		double monthlyInterestRate = interestRate / 12 / 100;
		double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTerm))
				/ (Math.pow(1 + monthlyInterestRate, loanTerm) - 1);
		return emi;
	}

	public void loanRepayment(int loanId, float amount) throws InvalidLoanException {
		Loan loan = loanDao.getLoanById(loanId);
		if (loan == null) {
			throw new InvalidLoanException("Loan not found with ID: " + loanId);
		}
		double emi = calculateEMI(loanId);
		int noOfEmisCanBePaid = (int) (amount / emi);
		if (noOfEmisCanBePaid < 1) {
			System.out.println("Payment rejected. Amount is less than one EMI.");
			System.out.println("For your laon 1 emi is : " + emi);
			System.out.println("You need pay that atleast");
		} else {

			System.out.println("Payment successful. " + noOfEmisCanBePaid + " EMIs paid.");
		}
	}

	public void getAllLoan() {
		List<Loan> loans = loanDao.getAllLoans();
		Iterator<Loan> iterator = loans.iterator();
		while (iterator.hasNext()) {
			Loan loan = iterator.next();
			System.out.println(loan);
		}
	}

	public void getLoanById(int loanId) throws InvalidLoanException {
		Loan loan = loanDao.getLoanById(loanId);
		if (loan == null) {
			throw new InvalidLoanException("Loan not found with ID: " + loanId);
		}
		System.out.println(loan);
	}

}
