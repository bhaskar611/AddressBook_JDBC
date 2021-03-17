package com.address.AddressBookJDBC;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;




public class AddressBook_DB {
	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println("	" + driverClass.getClass().getName());
		}
	}
	public List<AddressBookData> readData()
	{
		String sql = "select * from addressBook;";
		List<AddressBookData> addressBookList = new ArrayList<>();
		try(Connection connection = this.getConnection();)
		
		{
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			addressBookList.add(new AddressBookData(resultSet.getString("firstName"), resultSet.getString("lastName"),
					resultSet.getString("address"),resultSet.getString("city"),resultSet.getString("state"),resultSet.getInt("zip"),
					resultSet.getInt("phoneNumber"),resultSet.getString("email") ));
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return addressBookList;
	}
	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
		String userName = "root";
		Connection connection;
		System.out.println("Connecting to database : "+jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, userName, "1@Github");
		System.out.println("Connection is successful !! " + connection);
		return connection;
	}
	

   	
}
