package views;
import auth.DeleteUser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import java.sql.SQLException;

public class DeleteUserView extends View{
	private JComboBox choose_method_deletion; 
	private JTextField get_response; 
	private JButton submit; 
	public DeleteUserView(String env_type) {
		super(env_type);
	}
	public void display_graphical() {
		JFrame jf = new JFrame("Delete User");
		JPanel jp = new JPanel(); 
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		
		String[] methods_deletion = {"Id", "Username"};
		choose_method_deletion = new JComboBox(methods_deletion); 
		
		get_response = new JTextField(); 
		
		submit = new JButton("Submit");
		submit.addActionListener(new DeleteUserSubmitListener());
		jp.add(choose_method_deletion);
		jp.add(get_response);
		jp.add(submit);
		
		jf.add(jp);
		jf.setSize(300, 125);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	
		Thread validator = new Thread (new DeleteUserValidator());
		validator.run(); 
	}
	
	public class DeleteUserSubmitListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0){
			DeleteUser du = null;
			try {
				du = new DeleteUser();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
				return;
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
				return;
			}
			String method_of_deletion = (String) choose_method_deletion.getSelectedItem();
			if (method_of_deletion.equals("Id")) {
				int id_del = 0; 
				id_del = Integer.parseInt(get_response.getText());
				
				
				try {
					du.delete(id_del);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
					return;
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
					return;
				} 
			}
			else if (method_of_deletion.equals("Username")) {
				String username_del = get_response.getText(); 
				if (username_del.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Sorry, but your input was invalid. \nREASON: Your input contained spaces");
				}
				try {
					du.delete(username_del);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
					return;
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
					return; 
				}
			}
			
			JOptionPane.showMessageDialog(null, "The user was deleted successfully.");
			
		}

	
		

	}
	
	public class DeleteUserValidator implements Runnable{
		public void run() {
			Border default_border = get_response.getBorder(); 
			Border red_border = BorderFactory.createLineBorder(Color.RED);
			while(true) {
				String response = get_response.getText();
				if (choose_method_deletion.getSelectedItem().toString().equals("Id")) {
					if (response.matches("^\\d+$")) { //is an integer 
						get_response.setBorder(default_border);
						get_response.setToolTipText("");
						submit.setEnabled(true);
					}
					else {
						get_response.setBorder(red_border);
						get_response.setToolTipText("The Id you entered was not an integer!");
						submit.setEnabled(false);
					}
				}
				
				else if (choose_method_deletion.getSelectedItem().toString().equals("Username")){
					if (!response.contains(" ")) {
						submit.setEnabled(true);
						get_response.setBorder(default_border);
						get_response.setToolTipText("");
					}
					else {
						get_response.setBorder(red_border);
						get_response.setToolTipText("The Id you entered was not an integer!");
						submit.setEnabled(false);
					}
				}
				
			}
		}
	}
	
	public class DeleteUserFormValidator impelements Runnable{
		public void run(){
			String response = get_response.getText();
			String method_deletion = get_method_deletetion.getSelectedItem().toString(); 
			if (method_deletion.equals("Id") && response.matches("^-?\\d+$")){
					
				
			}
				
		}
		
		
	}
	
	
	
}
