package com.jspiders.jdbcdynamic.insert;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DynamicInsert {
	
	
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static FileReader fileReader;
	private static Properties properties;
	private static int result;
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
		    query=" insert into "
		    		+" student values "
		    		+" (?,?,?,?) ";
		    //Prepare statement
		    preparedStatement=connection.prepareStatement(query);
		    
		    preparedStatement.setInt(1,4);
		    preparedStatement.setString(2,"babita");
		    preparedStatement.setString(3,"babita@gmmial.com");
		    preparedStatement.setLong(4, 9876543210L);
		    
		    //Execution
		    result=preparedStatement.executeUpdate();
		    
		    System.out.println("Query ok "+result+" rows affected");
		    
		    
		    
		    
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			
				try {
					if(connection!=null) {
					connection.close();
					}
					if (preparedStatement!=null) {
						preparedStatement.close();
					}
					
					if (fileReader!=null) {
						fileReader.close();
					}
					
				}
				
				catch (SQLException | IOException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		
	}
	


