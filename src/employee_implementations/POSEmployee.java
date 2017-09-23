package employee_implementations;
import java.sql.*;

import config.POSDebugConfig;
import config.POSSQLConfig;

public class POSEmployee {
/****
 * A POSReady user with basic privileges.
 */
	
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
	
}

public String getUsername() {
	return username;
}






}
