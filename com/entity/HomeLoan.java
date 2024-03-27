package com.entity;

public class HomeLoan extends Loan {
	private String propertyAddress;
	private double propertyValue;

	public HomeLoan(int loanID, int customerID, double principalAmount, double interestRate, int loanTerm,
			LoanStatus loanStatus, String propertyAddress, double propertyValue) {
		super(loanID, customerID, principalAmount, interestRate, loanTerm, LoanType.HomeLoan, loanStatus);
		this.propertyAddress = propertyAddress;
		this.propertyValue = propertyValue;
	}

	public HomeLoan() {
		super();
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public String toString() {
		return "HomeLoan{" + "loanID=" + getLoanID() + ", customerID=" + getCustomerID() + ", principalAmount="
				+ getPrincipalAmount() + ", interestRate=" + getInterestRate() + ", loanTerm=" + getLoanTerm()
				+ ", loanType=" + getLoanType() + ", loanStatus=" + getLoanStatus() + ", propertyAddress='"
				+ propertyAddress + '\'' + ", propertyValue=" + propertyValue + '}';
	}
}
