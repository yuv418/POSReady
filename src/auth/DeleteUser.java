package auth;
import java.io.*;
import java.sql.*;

import utils.ImportantMethods;

public class DeleteUser {
	//delete a user by deleting their name from users_mappings pos_*_users;
	private Connection mariadb_default;
	private Statement delete_user_exec; 
	private POSUser employee_info; 
	public DeleteUser() throws ClassNotFoundException, SQLException{
		mariadb_default = ImportantMethods.getRegularPOSDBConnection(); 
		delete_user_exec = mariadb_default.createStatement();
	}
	
	public boolean delete(String username) throws ClassNotFoundException, SQLException {
		/* delete a user based on their username;
		 * 
		 * 
		 * LOGIC: 
		 * 
		 * Since CreateUser has verified that the users have no id/username conficts, DeleteUser can just go about deleting without verification. 
		 * 
		 * 
		 * 
		 */
		POSUser tb_del = new POSUser(username);
		
		String stmt_del_umappings = "DELETE FROM users_mappings where username=\"" + username + "\";" ;
		String stmt_del_ulevel = "DELETE FROM " + tb_del.getUserLevel() + " where username=\"" + username + "\";";
		
		delete_user_exec.execute(stmt_del_umappings);
		delete_user_exec.execute(stmt_del_ulevel);
		
		return true; 
	}
	
	public boolean delete(int id) throws SQLException, ClassNotFoundException {
		//same logic from delete(String username) applies here. 
		
		POSUser tb_del = new POSUser(id);
		
		String stmt_del_umappings = "DELETE FROM users_mappings where id=" + id + ";" ;
		String stmt_del_ulevel = "DELETE FROM " + tb_del.getUserLevel() + " where id=" + id + ";";
		
		delete_user_exec.execute(stmt_del_umappings);
		delete_user_exec.execute(stmt_del_ulevel);
		
		//close connections
		delete_user_exec.close();
		
		return true; 
	}
	
	
}
