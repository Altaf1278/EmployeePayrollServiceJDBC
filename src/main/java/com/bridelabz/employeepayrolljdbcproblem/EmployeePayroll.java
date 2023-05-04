package com.bridelabz.employeepayrolljdbcproblem;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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

	public static void getEmployeesByJoiningDateRange(LocalDate startDate, LocalDate endDate) throws SQLException {
		connection = setUpDatabase();
		String query = "SELECT * FROM employee_payroll WHERE start BETWEEN ? AND ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setDate(1, Date.valueOf(startDate));
		preparedStatement.setDate(2, Date.valueOf(endDate));
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt(1);

			LocalDate start = resultSet.getDate(8).toLocalDate();
			System.out.println(start);
		}
	}
		public void employeePayrollByGender() throws SQLException {
		    connection = setUpDatabase();
		    String query = "SELECT gender, SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) FROM employee_payroll GROUP BY gender";
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(query);
		    while (resultSet.next()) {
		        String gender = resultSet.getString(1);
		        double sumSalary = resultSet.getDouble(2);
		        double avgSalary = resultSet.getDouble(3);
		        double minSalary = resultSet.getDouble(4);
		        double maxSalary = resultSet.getDouble(5);
		        int count = resultSet.getInt(6);
		        System.out.println(gender + " " + sumSalary + " " + avgSalary + " " + minSalary + " " + maxSalary + " " + count);
		    }
		}
	}

