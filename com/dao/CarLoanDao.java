package com.dao;

public class CarLoanDao extends LoanDao {
	private String carModel;
	private double carValue;

	public CarLoan(int loanID, int customerID, double principalAmount, double interestRate, int loanTerm,
			LoanStatus loanStatus, String carModel, double carValue) {
		super(loanID, customerID, principalAmount, interestRate, loanTerm, LoanType.CarLoan, loanStatus);
		this.carModel = carModel;
		this.carValue = carValue;
	}

	public CarLoan() {
		super();
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public double getCarValue() {
		return carValue;
	}

	public void setCarValue(double carValue) {
		this.carValue = carValue;
	}

	@Override
	public String toString() {
		return "CarLoan{" + "loanID=" + getLoanID() + ", customerID=" + getCustomerID() + ", principalAmount="
				+ getPrincipalAmount() + ", interestRate=" + getInterestRate() + ", loanTerm=" + getLoanTerm()
				+ ", loanType=" + getLoanType() + ", loanStatus=" + getLoanStatus() + ", carModel='" + carModel + '\''
				+ ", carValue=" + carValue + '}';
	}
}
