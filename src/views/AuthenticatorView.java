package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.sql.SQLException;
import java.util.Scanner; 
import org.apache.commons.codec.digest.DigestUtils;
import org.jline.reader.LineReader; 

import auth.Authenticator;
import config.POSDebugConfig;
import javax.swing.*; 

public class AuthenticatorView extends View{

	private JTextField username; 
	private JPasswordField passwd; 
	private boolean isAuthenticated; 
	public AuthenticatorView(String env_type) {
		super(env_type);
	}
	
	public void display_command_line() {
		String username = "", enc_pass = ""; 
		Scanner in = new Scanner(System.in);
		Authenticator reg = null; 
		boolean username_valid = false; 
		while (!username_valid) {
			System.out.print("Username: ");
			username = in.next(); //username does not have spaces, no need for nextline
			
			try {
				reg = new Authenticator(username, enc_pass);
				if (reg.chkUsername()){
					username_valid = true; 
				}
				else {
					System.out.println("The username is invalid. Please try again.");
				}
			}
			catch (SQLException se) {
				 System.out.println("ERROR: Could not connect to POSReady database. Please check your installation and if mariadb is running.");
				 if (POSDebugConfig.console_debug()) {
					 se.printStackTrace(); 
				 }
			}
			catch (ClassNotFoundException ce) {
				System.out.println("ERROR: Cannot start authenticator. Please check if the mariadb connector jar is present.");
				if (POSDebugConfig.console_debug()) {
					ce.printStackTrace();
				}
			}
		}
		
		boolean password_valid = false; 
		int attempts = 3;  // allow three attempts for now
		while (!password_valid) {
			in.nextLine(); //advance scanner so it doesn't get input from before
			Console console = System.console();
	        if (console == null && POSDebugConfig.console_debug()) {
	            System.out.println("DEBUG: You are running POSReady from an IDE. As a result, passwords will be echoed. Consider running POSReady from the command line.");
	            System.out.print("\nPassword: ");
				enc_pass = in.nextLine(); 
	        }
			
	        else {
	        	enc_pass = String.valueOf(console.readPassword());
	        }
			enc_pass = DigestUtils.sha256Hex(enc_pass);
			reg.setEncPass(enc_pass); 
			try {
				if (reg.chkPassword()) {
					password_valid = true; 
				}
				else if (attempts == 0) {
					isAuthenticated = false; 
				}
				else {
					System.out.print("The password is invalid. Please try again. You have " + attempts + " attempts remaining.");
					attempts--; 
				}
			}
			catch (ClassNotFoundException ce) {
				System.out.println("ERROR: Cannot start authenticator. Please check if the mariadb connector jar is present.");
				if (POSDebugConfig.console_debug()) {
					ce.printStackTrace();
				}
			}
			catch (SQLException se) {
				 System.out.println("ERROR: Could not connect to POSReady database. Please check your installation and if mariadb is running.");
				 if (POSDebugConfig.console_debug()) {
					 se.printStackTrace(); 
				 }
			}
		}
		
		isAuthenticated = true; 
			
		
		
		
	}
	
	public boolean isAuthenticated() {
		return isAuthenticated; 
	}
	public void display_graphical() {
		//use swing; 
		JFrame mjf = new JFrame("Authenticator");
		JPanel mjp = new JPanel();
		mjp.setLayout(new BoxLayout(mjp, BoxLayout.Y_AXIS));
		
		username = new JTextField(1); //text field with 1 column
		passwd = new JPasswordField(1); //password field with 1 column
	   
		JButton submit_btn = new JButton("Log in");
		submit_btn.addActionListener(new SubmitListener());
		mjp.add(username);
		mjp.add(passwd);
	   
		mjf.add(mjp);
		mjf.setSize(300, 100);
		mjf.setVisible(true);
	}
	
	public class SubmitListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String username_str = username.getText();
			String encr_pass_str = DigestUtils.sha256Hex(String.valueOf(passwd.getPassword()));
			
			Authenticator a_reg = null;
			try {
				a_reg = new Authenticator(username_str, encr_pass_str);
			} catch (ClassNotFoundException e2) {
				JOptionPane.showMessageDialog(null, "Sorry, POSReady could not load the library to connect to its database. Please check your POSReady installation and try again.");
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "Sorry, POSReady could not connect to the database or there was an error in this build of POSReady. Please report this to the developers.");
			}
			
			//nested try_catch!
			try {
				if (a_reg.chkUsername() && a_reg.chkPassword()) {
					isAuthenticated = true; 
				}
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Sorry, POSReady could not connect to the database or there was an error in this build of POSReady. Please report this to the developers.");
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Sorry, POSReady could not load the library to connect to its database. Please check your POSReady installation and try again.");
			} 

		}
		
	}
}
