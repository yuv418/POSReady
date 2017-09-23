package employee_implementations;
import java.sql.*;

import config.POSDebugConfig;
import config.POSSQLConfig;

public class POSEmployee {
/****
 * A POSReady user with basic privileges.
 */
	
private Connection mariadb_default; 
private String user_type; //table name of user category table in database: pos_users, pos_admin_users, pos_top_admin_users
private String username;
public POSEmployee(String utype, String u_name){
/*
 * Default POSEmployee constructor
 * 
 * 	
 */
	user_type = utype;
	u_name = username; 
  try {
	mariadb_default = DriverManager.getConnection("jdbc:mariadb://localhost:3306/posready", POSSQLConfig.getSQLUsername(), POSSQLConfig.getSQLPassword());
  } 
  catch (SQLException e) {
	if (POSDebugConfig.console_debug()) {
		System.err.println("debug: fatal, cannot connect to posready default database"); // TODO Switch to automated printer  
		e.printStackTrace();
		System.exit(-1);
	}
	else if(!POSDebugConfig.console_debug()) {
		System.err.println("Sorry, we couldn't connect to the POSReady Database at this time."); //the posready database is a glorified name for mariadb. 
		System.exit(-1);
	}
  }
}

public String getUsername() {
	return username;
}






}
