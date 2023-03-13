package com.jspiders.jdbc.Dynamic.Delete;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DynamicDelete {
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
		    query="delete from emp  where id=?";
		   
		  //Prepare statementZA
		   
		  
		    
		    //Execution
		    preparedStatement=connection.prepareStatement(query);
		    
//		    preparedStatement.setString(1,"suraj@gmail.com");
		    preparedStatement.setInt(1,4);
//		   
		    
result=preparedStatement.executeUpdate();
		   System.out.println("Query "+result);
		   
		    
		    
		    
			
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
