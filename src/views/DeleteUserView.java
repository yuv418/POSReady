package views;
import auth.DeleteUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 
import java.sql.SQLException;

public class DeleteUserView extends View{
	private JComboBox choose_method_deletion; 
	private JTextField get_response; 
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
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new DeleteUserSubmitListener());
		jp.add(choose_method_deletion);
		jp.add(get_response);
		jp.add(submit);
		
		jf.add(jp);
		jf.setSize(300, 300);
		jf.setVisible(true);
	}
	
	public class DeleteUserSubmitListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0){
			DeleteUser du = null;
			try {
				du = new DeleteUser();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
			}
			String method_of_deletion = (String) choose_method_deletion.getSelectedItem();
			if (method_of_deletion.equals("Id")) {
				int id_del = 0; 
				try {
					id_del = Integer.parseInt(get_response.getText());
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Sorry, but your input was invalid. \n REASON: The id specified was not a valid integer.");
				}
				
				try {
					du.delete(id_del);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
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
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
				}
			}
			
			JOptionPane.showMessageDialog(null, "The user was deleted successfully.");
			
		}
	
	}
}
