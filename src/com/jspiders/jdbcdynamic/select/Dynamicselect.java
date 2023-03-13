package com.jspiders.jdbcdynamic.select;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Dynamicselect {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static FileReader fileReader;
	private static Properties properties;
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
		    query=" select * "
		    		+" from student "
		    		+"where id=?";
		    
		  //Prepare statementZA
		   
		   
		    
		    //Execution
		    preparedStatement=connection.prepareStatement(query);
		    preparedStatement.setInt(1,4);
		    resultSet=preparedStatement.executeQuery();
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
