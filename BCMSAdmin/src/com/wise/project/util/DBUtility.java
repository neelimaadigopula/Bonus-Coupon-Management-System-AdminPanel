package com.wise.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.el.parser.ParseException;

public class DBUtility {
	static Connection connection = null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bcms?useSSL = true","root","root");
		}catch(SQLException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}	
		return connection;
	}
	public static void close(Object ...args) {
		for(Object arg : args) {
			try {
				if(arg instanceof ResultSet) {
					((ResultSet)arg).close();
				} 
				if(arg instanceof Statement) {
					((Statement)arg).close();
				}
				if(arg instanceof PreparedStatement) {
					((PreparedStatement)arg).close();
				}
				if(arg instanceof Connection) {
					((Connection)arg).close();
				}
			
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			
		}
	}
	public static Date stringToUtilDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date convertedDate = null;
			try {
				convertedDate = dateFormat.parse(date);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return convertedDate;
	}
	public static java.util.Date SQLDateToUtilDate(java.sql.Date sqlDate) {
		java.util.Date javaDate = new Date(sqlDate.getTime());
		return javaDate;
	}
	public static java.sql.Date UtilDateToSQLDate(java.util.Date javaDate) { 
		java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
		return sqlDate;
	}
}
