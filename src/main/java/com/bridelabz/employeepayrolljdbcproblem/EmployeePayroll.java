package com.bridelabz.employeepayrolljdbcproblem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeePayroll extends Base {

	public void retrieveEmployeePayrollData() throws SQLException {
		// TODO Auto-generated method stub
		connection = setUpDatabase();
		Statement statement = connection.createStatement();
		String query = "select * from employee_payroll";
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String gender = resultSet.getString(3);
			double salary = resultSet.getDouble(4);
			String date = resultSet.getString(5);
			System.out.println(id + " " + name + " " + gender + " " + salary + " " + date);
		}

		System.out.println("Retrieve all the employee payroll data");
	}

	public void insertEmployeePayrollData() throws SQLException {
		connection = setUpDatabase();
		String insertQuery = "INSERT INTO employee_payroll (name, gender, phone,department,address, salary, start) VALUES (?, ?, ?, ?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		preparedStatement.setString(1, "Terrisa");
		preparedStatement.setString(2, "F");
		preparedStatement.setDouble(3, 91680681);
		preparedStatement.setString(4, "IT");
		preparedStatement.setString(5, "Kondha");
		preparedStatement.setString(6, "25000000.0");
		preparedStatement.setString(7, "2020-05-25");

		preparedStatement.execute();

		System.out.println("Record added successfully");
	}

	public void updateEmployeePayrollData() throws SQLException {
		connection = setUpDatabase();
		String updateQuery = "update employee_payroll set salary = 3000000.00 WHERE name = 'Terrisa'";
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.executeUpdate();
		System.out.println("Record updated successfully");
	}
	public void updateWithPreparedStementEmployeePayrollData() throws SQLException {
		connection = setUpDatabase();
		String updateQuery = "update employee_payroll set salary = ? WHERE name = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.setDouble(1, 3000000.00);
		preparedStatement.setString(2, "Terrisa");
		preparedStatement.executeUpdate();
		System.out.println("Record updated successfully");
}
}
