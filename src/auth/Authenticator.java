package auth;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import utils.ImportantMethods;

public class Authenticator {
	private Connection mariadb_default; 
	private String username; 
	private String enc_pass; 
	
	public Authenticator(String username, String enc_pass) throws ClassNotFoundException, SQLException {
		this.username = username; 
		this.enc_pass = enc_pass; 
		mariadb_default = ImportantMethods.getRegularPOSDBConnection();
	}
	
	public boolean chkUsername() throws SQLException {
		String chk_username = ImportantMethods.getResultString(mariadb_default, "users_mappings", "username", "username", this.username);
		if (chk_username.equals(this.username)) {
			return true; 
		}
		return false; 
	}
	
	public boolean chkPassword() throws SQLException, ClassNotFoundException {
		POSUser mapper = new POSUser(username);
		String ulevel = mapper.getUserLevel(); 
		String db_enc_pass = ImportantMethods.getResultString(mariadb_default, ulevel, "enc_password", "username", this.username);
		if (db_enc_pass.equals(enc_pass)) {
			db_enc_pass = ""; //wipe sensitive data 
			enc_pass = "";
			return true; 
		}
		db_enc_pass = ""; //wipe sensitive data 
		return false; 
	}
	
	public void setEncPass(String enc_pass) {
		this.enc_pass = enc_pass; 
	}
	
	public void setUsername(String username) {
		this.username = username; 
	}
}
