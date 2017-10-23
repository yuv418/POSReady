package employee_implementations;
import java.sql.*;

import config.POSDebugConfig;
import config.POSSQLConfig;
import utils.ImportantMethods;
public class POSEmployee {
/****
 * A POSReady user with basic privileges.
 */
	
private String user_level; //table name of user category table in database: pos_users, pos_admin_users, pos_top_admin_users
private String username;
private String firstname; 
private String middlename; 
private String lastname; 
private int id; 
private Connection mariadb_default;
public POSEmployee(String username) throws SQLException, ClassNotFoundException{
/*
 * username POSEmployee constructor
 * 
 * 	
 */
	this.username = username; 
	this.mariadb_default = ImportantMethods.getRegularPOSDBConnection();
	this.user_level = ImportantMethods.getResultString(mariadb_default, "users_mappings", "user_level", "username" , username);
	this.lastname = ImportantMethods.getResultString(mariadb_default, "users_mappings", "lastname", "username", username);
	this.firstname = ImportantMethods.getResultString(mariadb_default, "users_mappings", "firstname", "username", username);
	this.middlename = ImportantMethods.getResultString(mariadb_default, "users_mappings", "middlename", "username", username);
	this.id = ImportantMethods.getResultInt(mariadb_default, "users_mappings", "id", "username", username);
}

public POSEmployee(int user_id) throws SQLException, ClassNotFoundException{
/*
 * id POSEmployee constructor
 */
	
	this.id = user_id; 
	this.mariadb_default = ImportantMethods.getRegularPOSDBConnection();
	this.user_level = ImportantMethods.getResultString(mariadb_default, "users_mappings", "user_level", "id" , id);
	this.lastname = ImportantMethods.getResultString(mariadb_default, "users_mappings", "lastname", "id", id);
	this.firstname = ImportantMethods.getResultString(mariadb_default, "users_mappings", "firstname", "id", id);
	this.middlename = ImportantMethods.getResultString(mariadb_default, "users_mappings", "middlename", "id", id);
	this.id = ImportantMethods.getResultInt(mariadb_default, "users_mappings", "id", "id", id);
	
	
}
public String getUsername() {
	return username;
}

public String getUserLevel() {
	return user_level; 
}


public String getFirstName() {
	return firstname; 
}

public String getMiddleName() {
	return middlename;
}

public String getLastName() {
	return lastname; 
}

public int getId() {
	return id; 
}

}
