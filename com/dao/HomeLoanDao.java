package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.entity.HomeLoan;
import com.util.DBUtil;

public class HomeLoanDao extends LoanDao {
	public boolean createHomeLoan(HomeLoan homeLoan) {
		String loanSql = "INSERT INTO Loan (CustomerID,principalAmount, interestRate,LoanType, loanTerm) VALUES (?, ?, ?, ?, ?)";
		String homeLoanSql = "INSERT INTO HomeLoan (loanId, propertyAddress, propertyValue) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement loanStmt = null;
		PreparedStatement homeLoanStmt = null;

		try {
			conn = DBUtil.getDBConn();

			loanStmt = conn.prepareStatement(loanSql, Statement.RETURN_GENERATED_KEYS);
			loanStmt.setInt(1, homeLoan.getCustomerID());
			loanStmt.setDouble(2, homeLoan.getPrincipalAmount());
			loanStmt.setDouble(3, homeLoan.getInterestRate());
			loanStmt.setInt(4, 2);
			loanStmt.setInt(5, homeLoan.getLoanTerm());
			int rowsInserted = loanStmt.executeUpdate();

			if (rowsInserted == 0) {
				throw new SQLException("Creating loan failed, no rows affected.");
			}

			try (ResultSet generatedKeys = loanStmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					homeLoan.setLoanID(generatedKeys.getInt(1));
					System.out.println("your loan is placed and your loan id is : " + generatedKeys.getInt(1));
					
				} else {
					throw new SQLException("Creating loan failed, no ID obtained.");
				}
			}

			homeLoanStmt = conn.prepareStatement(homeLoanSql);
			homeLoanStmt.setInt(1, homeLoan.getLoanID());
			homeLoanStmt.setString(2, homeLoan.getPropertyAddress());
			homeLoanStmt.setDouble(3, homeLoan.getPropertyValue());
			homeLoanStmt.executeUpdate();

			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false; // Failed
		} finally {
			// Close resources
			try {
				if (loanStmt != null) {
					loanStmt.close();
				}
				if (homeLoanStmt != null) {
					homeLoanStmt.close();
				}
				if (conn != null) {
					conn.setAutoCommit(true); // Reset auto-commit mode
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean deleteHomeLoan(int loanId) {
		return false;
		// Implementation
	}

	public void updateHomeLoan(int loanId, String newPropertyAddress, double newPropertyValue) {
		// Implementation
	}

	public HomeLoan getHomeLoanById(int loanId) {
		return null;
		// Implementation
	}
}
