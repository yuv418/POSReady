package utils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import config.POSDebugConfig;
import config.POSSQLConfig;

public class ImportantMethods {
public static void logoDisplay() throws IOException{
	String filePath = new File("src\\utils\\poslogo.txt").getAbsolutePath();
	 File file = new File(filePath);
	 List logolines = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
	 
	 for (Object line : logolines.toArray()) {
		System.out.println(line);    

	
	
}
	 

	//System.out.println("POSReady Auth Page");  
}


public static Connection getRegularPOSDBConnection() throws ClassNotFoundException, SQLException{

Class.forName("org.mariadb.jdbc.Driver");

Connection mariadb_default = DriverManager.getConnection("jdbc:mariadb://localhost:3306/posready", POSSQLConfig.getSQLUsername(), POSSQLConfig.getSQLPassword());
return mariadb_default;
}


public static String getResultString(Connection mariadb_default, String table_name, String column, String column_check, String column_equal) throws SQLException {
	String query = "SELECT " + column + " FROM " + table_name + " WHERE " + column_check + "=\"" + column_equal + "\";";
	Statement do_query = mariadb_default.createStatement();
	ResultSet query_result = do_query.executeQuery(query);
	String result_string = "";
	while (query_result.next()) {
		result_string = query_result.getString(column);
	}
	return result_string; 
}




public static String getResultString(Connection mariadb_default, String table_name, String column, String column_check, int column_equal) throws SQLException {
	String query = "SELECT " + column + " FROM " + table_name + " WHERE " + column_check + "=" + column_equal + ";";
	Statement do_query = mariadb_default.createStatement();
	ResultSet query_result = do_query.executeQuery(query);
	String result_string = "";
	while (query_result.next()) {
		result_string = query_result.getString(column);
	}
	return result_string; 
}

public static int getResultInt(Connection mariadb_default, String table_name, String column, String column_check, String column_equal) throws SQLException {
	String query = "SELECT " + column + " FROM " + table_name + " WHERE " + column_check + "=\"" + column_equal + "\";";
	Statement do_query = mariadb_default.createStatement();
	ResultSet query_result = do_query.executeQuery(query);
	int result_int = 0;
	while (query_result.next()) {
		result_int = query_result.getInt(column);
	}
	return result_int; 
}

public static int getResultInt(Connection mariadb_default, String table_name, String column, String column_check, int column_equal) throws SQLException {
	String query = "SELECT " + column + " FROM " + table_name + " WHERE " + column_check + "=" + column_equal + ";";
	Statement do_query = mariadb_default.createStatement();
	ResultSet query_result = do_query.executeQuery(query);
	int result_int = 0;
	while (query_result.next()) {
		result_int = query_result.getInt(column);
	}
	return result_int; 
}


public static double getResultDouble(Connection mariadb_default, String table_name, String column, String column_check, String column_equal) throws SQLException {
	String query = "SELECT " + column + " FROM " + table_name + " WHERE " + column_check + "=\"" + column_equal + "\";";
	Statement do_query = mariadb_default.createStatement();
	ResultSet query_result = do_query.executeQuery(query);
	double result_double = 0;
	while (query_result.next()) {
		result_double = query_result.getDouble(column);
	}
	return result_double; 
}

public static double getResultDouble(Connection mariadb_default, String table_name, String column, String column_check, int column_equal) throws SQLException {
	String query = "SELECT " + column + " FROM " + table_name + " WHERE " + column_check + "=" + column_equal + ";";
	Statement do_query = mariadb_default.createStatement();
	ResultSet query_result = do_query.executeQuery(query);
	double result_double = 0;
	while (query_result.next()) {
		result_double = query_result.getDouble(column);
	}
	return result_double; 
}
}
