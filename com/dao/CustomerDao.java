package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.entity.Customer;
import com.util.DBUtil;
public class CustomerDao {
	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	

	public void addCustomer(Customer customer)
	{
		try {
			con = DBUtil.getDBConn();
			String query = "INSERT INTO Customers (Name, EmailAddress, PhoneNumber,Address) VALUES (?, ?, ?, ?)";
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmailAddress());
			ps.setString(3, customer.getPhoneNumber());
			ps.setString(4, customer.getAddress());

			int noOfRowsInserted = ps.executeUpdate();

			if (noOfRowsInserted > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Hey there! Delighted to have you join our Bank!!");
					System.out.println("This is Your Id: " + id);
					
				} else {
					System.out.println("Failed to retrieve userId");
				}
			} else {
				System.out.println("No rows inserted");
			}
		} catch (SQLException e) {
			
				System.out.println("Error while creating user: " + e.getMessage());
			
		} finally {
			closeResources();

		}
		
	}
	
	public Customer getCustomer(int cid)
	{
		Customer c = new Customer();
		try {
			ps = con.prepareStatement("select * from customer where CustomerID = "+cid);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				c.setCustomerID(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setEmailAddress(rs.getString(3));
				c.setPhoneNumber(rs.getString(4));
				c.setAddress(rs.getString(5));
				c.setCreditScore(rs.getFloat(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return c;
		
	}
	
	
	private void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
