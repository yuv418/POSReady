package auth;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Console;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter; 

import org.apache.commons.io.*;
public class User implements ActionListener{
private boolean isAdmin; 
private String username; 
private String hashedpwd; 
private boolean authorized; 
private JFrame main;
private JPasswordField auth;  
private JButton submit; 
private boolean auth_frame_is_open; 
private int pwd; 
private JLabel pwdlabel; 
private String userpath; 
private String filePath; 
private JLabel usrlabel; 
private JTextField usr;

	public User(String userpath) throws IOException, FileNotFoundException{
		List usrinfo = new ArrayList<String>();
		//String filePath = new File("src\\utils\\bob.usrconf").getAbsolutePath();
		this.userpath = userpath; 
		String filepath = new File(userpath).getAbsolutePath();
		
		
		 File file = new File(filepath);
		 usrinfo = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
		 username = (String) usrinfo.get(0); 
		 hashedpwd = (String) usrinfo.get(1); 
		 boolean authorized = false; 
		 String admin = (String) usrinfo.get(2); 
		 admin = admin.trim();
		 
		 if (admin.equals("y")){
			 isAdmin = true; 
		 }
		 else {
			 isAdmin = false; 
		 }
		 
	
}
	public void printUsername(){
		
		System.out.println("usr: " + username);
	}
	public void auth() throws IOException{
		
		String filePath = new File("src\\utils\\poslogo.txt").getAbsolutePath();
		 File file = new File(filePath);
		 List logolines = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
		 
		 for (Object line : logolines.toArray()) {
			System.out.println(line);    

		
		
	}
		System.out.println("POSReady Auth Page");  
		String pwd = "";
		Console authconsole = System.console(); 
		if (authconsole == null){
			PythonInterpreter erp = new PythonInterpreter(); 
			erp.exec("import getpass");
			erp.exec("pwd = getpass.getpass()");
			PyObject npwd = erp.get("pwd");
			
			pwd = npwd.toString(); 
		}
		else {
			System.out.print("Password: " );
			char[] passwordChars = authconsole.readPassword();
			pwd = String.valueOf(passwordChars);
			System.out.println();
		}
		
		
		
		if (pwd.hashCode() == Integer.parseInt((hashedpwd))){
			System.out.println("Auth success");
			authorized=true; 
		}
	}
	public boolean isAdmin(){
		return isAdmin;
	}
	public void guiauth() throws IOException{
		String filePath = new File("src\\utils\\poslogo.txt").getAbsolutePath();
		 File file = new File(filePath);
		 List logolines = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");

		for (Object line : logolines.toArray()) {
			System.out.println(line);    

		
		
	}
		System.out.println("POSReady Auth Page");
		main = new JFrame("Authorization Page");
		pwdlabel = new JLabel("Password: ");
		usrlabel = new JLabel("Username: ");
		auth_frame_is_open = true; 
		auth = new JPasswordField();
		usr = new JTextField(); 
		auth.setSize(100, 20);
		usr.setSize(100,20);
		submit = new JButton("Submit");
		submit.setSize(50, 10);
		submit.addActionListener(this); 
		main.getContentPane().add(usrlabel);
		main.getContentPane().add(pwdlabel);
		main.getContentPane().add(usr); 
		main.getContentPane().add(auth);
		main.getContentPane().add(BorderLayout.EAST,submit); 
		
		main.setVisible(true);
		main.setSize(400, 400);
		
		
		if (auth_frame_is_open = false){
			System.out.println("hi");
			
			checkPwd(pwd); 
		}
		
	}
	private void checkPwd(int hashedPwd){
		if (hashedPwd == Integer.parseInt((this.hashedpwd))){
			System.out.println("Auth Success");
			authorized=true; 
		}
		else{
			System.out.println("Auth Fail");
		}
		
	}	
	private void checkPwd(int hashedPwd,String username){
		if (hashedPwd == Integer.parseInt((this.hashedpwd)) && username.equals(this.username)){
			System.out.println("Auth Success");
			authorized=true; 
		}
		else{
			System.out.println("Auth Fail");
		}
		
	}
	public void actionPerformed(ActionEvent e){
		pwd = String.valueOf(auth.getPassword()).hashCode();
		String usrn = usr.getText();
		auth_frame_is_open = false; 
		main.dispose(); 
		checkPwd(pwd,usrn); 
		
		
	}
	public boolean isAuthorized(){
		if (authorized){
			return true; 
		}
		else{
			return false; 
		}
	}
}


