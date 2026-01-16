 package com.comcast.crm.generic.DatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {

	Connection con;
	
	public void getdbConnection(String URL, String UserName, String Password) {
		 try {
			 Driver driverRef = new Driver();
			 DriverManager.registerDriver(driverRef);
			 con= DriverManager.getConnection(URL, UserName, Password);
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void closeDBConnection() {
	try {
		con.close();
	}catch (Exception e) {
		
	}
}
	//Create SQL statement and obtain result
	public ResultSet executeSelectQuery(String Query) {
		
		ResultSet result= null;
		 try {
			 Statement stat = con.createStatement();
			 result= stat.executeQuery(Query);
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
		 return result;
		
	}
	public void getdbConnection() {
		try {
			Driver driverRef= new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection("jdbc:mysql:http://49.249.28.218:3307","root","root");
		}
		catch (Exception e) {
			
		}
	}
	
	
}