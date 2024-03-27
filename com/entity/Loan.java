package com.entity;

public class Loan {
	private int loanID;
	private int customerID;
	private double principalAmount;
	private double interestRate;
	private int loanTerm;
	private LoanType loanType;
	private LoanStatus loanStatus;
	private Customer customer; 

	public Loan(int loanID, int customerID, double principalAmount, double interestRate, int loanTerm,
			LoanType loanType, LoanStatus loanStatus) {
		this.loanID = loanID;
		this.customerID = customerID;
		this.principalAmount = principalAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
	}

	public Loan() {
		super();
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}
	public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Customer getCustomer(int cid)
    {
    	return customer;
    }
	@Override
	public String toString() {
		return "Loan [loanID=" + loanID + ", customerID=" + customerID + ", principalAmount=" + principalAmount
				+ ", interestRate=" + interestRate + ", loanTerm=" + loanTerm + ", loanType=" + loanType
				+ ", loanStatus=" + loanStatus + "]";
	}
}
