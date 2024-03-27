package com.entity;

public class Customer {
	private int customerID;
	private String name;
	private String emailAddress;
	private String phoneNumber;
	private String address;
	private float creditScore;

	public Customer(int customerID, String name, String emailAddress, String phoneNumber, String address,
			float creditScore) {
		this.customerID = customerID;
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.creditScore = creditScore;
	}

	public Customer() {
		super();
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(float creditScore) {
		this.creditScore = creditScore;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + ", emailAddress=" + emailAddress
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", creditScore=" + creditScore + "]";
	}

}
