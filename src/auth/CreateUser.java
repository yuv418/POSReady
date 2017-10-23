package auth;
import java.io.*; 
import java.sql.*;
import utils.ImportantMethods;

import org.apache.commons.codec.digest.DigestUtils;

public class CreateUser {


private String username; 
private String hpw; 
private String adminlevel; //pos_users, pos_admin_users, pos_top_admin_users
private String firstname; 
private String lastname; 
private String middlename; 

private Connection mariadb_default; 
	public CreateUser(String firstname, String middlename, String lastname, String username, String password, String adminlevel) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
		this.hpw = DigestUtils.sha256Hex(password);
		this.username = username.trim(); 
		this.adminlevel = adminlevel; 
		this.firstname = firstname; 
		this.middlename = middlename; 
		this.lastname = lastname;  
		mariadb_default = ImportantMethods.getRegularPOSDBConnection(); 
	}
	
	public boolean writeInfo() throws EmployeeNotCreatedException, SQLException{
	/*	this.userconfw.println(username);
		this.userconfw.println(hashedpassword);
		this.userconfw.println(adminyn);
		this.userconfw.close();
	*/
	Statement insert_info = null;

		insert_info = mariadb_default.createStatement();
		String check_username_exists = ImportantMethods.getResultString(mariadb_default, "users_mappings", "username", "username", this.username);
		if(check_username_exists.equals(this.username)) {
			//username exists, pick a new username; 
			throw new EmployeeNotCreatedException("username_exists"); //controller will deduce message "Sorry, the username exists already" and send it to the view. Controller here is exception
		}
		String querylastid = "SELECT id FROM " + adminlevel + " ORDER BY id DESC LIMIT 1"; //get last id
		ResultSet result_lastid = null;
		result_lastid = insert_info.executeQuery(querylastid);
		int id = 0; 
		while (result_lastid.next()) {
			id = result_lastid.getInt("id");
		}
		id += 1; 
		String insert_employee = "INSERT INTO " + this.adminlevel + " VALUES (\"" + this.username + "\"," + id + ",\"" + this.hpw + "\");";
		insert_info.execute(insert_employee);
		String map_employee = "INSERT INTO users_mappings VALUES(\"" + this.firstname + "\",\"" + this.middlename + "\",\"" + this.lastname + "\",\"" + this.username + "\"," + id + ",\"" + this.adminlevel + "\");";
		insert_info.execute(map_employee);
		insert_info.close(); 
		//System.out.println("Employee created successfully."); //no no no! this is for debug purposes ONLY!!!!
		return true; 
	}
	
	
	
	
	
	
	}
	
	

