package auth;
import java.io.*; 
import java.nio.*;
import java.util.Scanner; 
import org.apache.commons.io.*; 

public class CreateUser {
private PrintWriter userconfw; 
private Scanner usercheck; 
private String username; 
private int hashedpassword; 
private boolean isAdmin; 
private String adminyn; 
private String userconfpath; 
	public CreateUser(String username, String password, boolean isAdmin) throws FileNotFoundException, IOException{
		this.hashedpassword = password.trim().hashCode(); 
		this.username = username.trim(); 
		this.isAdmin = isAdmin;
		adminyn = ""; 
		if (isAdmin){
			adminyn = "y"; 
		}
		if (!isAdmin){
			adminyn = "n"; 
		}
		this.userconfpath = new File("src\\auth\\users\\"  + this.username + ".usrconf").getAbsolutePath(); 
		
		this.userconfw = new PrintWriter(userconfpath); 
		
	}
	
	public void write(){
		this.userconfw.println(username);
		this.userconfw.println(hashedpassword);
		this.userconfw.println(adminyn);
		this.userconfw.close();
	}
}
