package com.jspiders.jdbc.Dynamic.callable;

import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CallableDemo {
	private static Connection connection;
	private static FileReader fileReader;
	private static Properties properties;
	private static CallableStatement callableStatement;
	private static ResultSet resultSet;
	
	private static String query;
	private static String filepath="D:\\WEJA1\\JDBCDynamic\\resources\\db_info.properties";
	
	public static void main(String[] args) {
		try {
            // load The file
			fileReader=new FileReader(filepath);
			properties=new Properties();
			properties.load(fileReader);
			
			//open class
			Class.forName(properties.getProperty("driverpath"));
			 
			//open connection
		    connection=DriverManager.getConnection(properties.getProperty("dburl"), properties);
		    
            //Query
		    query="call proc4()";
		   
		  //Prepare statement
		   callableStatement=connection.prepareCall(query);
		    	   
		    
           resultSet=callableStatement.executeQuery();
		   while(resultSet.next()) {
			   System.out.println(resultSet.getInt(1)+"||"
					   +resultSet.getString(2)+"||"
					   +resultSet.getString(3)+"||"
					   +resultSet.getLong(4));
		   } 
			  
		   
		    
		    
		    
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			
				try {
					if(connection!=null) {
					connection.close();
					}
					if (callableStatement!=null) {
						callableStatement.close();
					}
					
					if (fileReader!=null) {
						fileReader.close();
					}
					if (resultSet!=null) {
						resultSet.close();
					}
					}
					
				
				
				catch (SQLException | IOException e) {
				
					e.printStackTrace();
				}
			}
		}
}
