package com.leantaas.assignment.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;


public class DatabaseConnection {
	public DatabaseConnection(){
		
	}
	private static String DB_DRIVER, DB_URL, DB_NAME, DB_USER, DB_PASSWORD;
	
	
	/*@Autowired
	static DBConfig dbConfig;*/
	public static String getDB_NAME() {
		return DB_NAME;
		//return dbConfig.getDbName();
	}

	public static Connection getConnection(){
		Properties dbProp = new Properties();
		
		Connection dbCon = null;
		
		try {
			dbProp.load(DatabaseConnection.class.getClassLoader()
					.getResourceAsStream(
							"com/leantaas/assignment/database/connection.properties"));
		
			System.out.println("IN DB .....");
			DB_DRIVER = dbProp.getProperty("DB_DRIVER");
			DB_URL = dbProp.getProperty("DB_URL");
			DB_NAME = dbProp.getProperty("DB_NAME");
			DB_USER = dbProp.getProperty("DB_USER");
			DB_PASSWORD = dbProp.getProperty("DB_PASSWORD");

	
			//Class.forName(dbConfig.getDbDriver());// 
			Class.forName(DB_DRIVER);
			
			dbCon = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER,
					DB_PASSWORD);
			
			/*dbCon = DriverManager.getConnection(dbConfig.getDbUrl()+dbConfig.getDbName(),
					dbConfig.getDbUser(),
					dbConfig.getDbPassword());*/
		} catch (ClassNotFoundException e) {
			System.out.println("Error : Class Not Found " + e);
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Error : SQL Error " + e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dbCon;

	}
}
