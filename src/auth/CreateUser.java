package auth;
import java.io.*; 
import java.sql.*;
import utils.ImportantMethods;
import java.nio.*;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.*; 
import config.POSConfig;
public class CreateUser {
private PrintWriter userconfw; 
private Scanner usercheck; 
private String username; 
private String hpw; 
private String adminlevel; //pos_users, pos_admin_users, pos_top_admin_users
private String adminyn; 
private String userconfpath; 
private String firstname; 
private String lastname; 
private String middlename; 

private Connection mariadb_default; 
	public CreateUser(String firstname, String middlename, String lastname, String username, String password, String adminlevel) throws FileNotFoundException, IOException{
		this.hpw = DigestUtils.sha256Hex(password);
		this.username = username.trim(); 
		this.adminlevel = adminlevel; 
		this.firstname = firstname; 
		this.middlename = middlename; 
		this.lastname = lastname; 
		mariadb_default = ImportantMethods.getRegularPOSDBConnection(); 
	}
	
	public void writeInfo(){
	/*	this.userconfw.println(username);
		this.userconfw.println(hashedpassword);
		this.userconfw.println(adminyn);
		this.userconfw.close();
	*/
	Statement insert_info = null;
	try {
		insert_info = mariadb_default.createStatement();
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
		System.out.println("Employee created successfully.");
	} catch (SQLException e) {
		if (config.POSDebugConfig.console_debug()) {
			System.err.println("debug: could not execute query on the posready database");
			e.printStackTrace();
		}
		
		else if(!config.POSDebugConfig.console_debug()) {
			System.err.println("Sorry, we couldn't record the information specified.");
		}
	}
	
	
	
	
	
	
	}
	
	
}
