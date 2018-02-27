package views;
import auth.DeleteUser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import items.DeleteItem;
import javax.swing.*;
import javax.swing.border.Border;

import java.sql.SQLException;

public class DeleteItemView extends TableView {
    private JComboBox choose_method_deletion;
    private JTextField get_response;
    private JButton submit;

    public DeleteItemView(String env_type) {
        super(env_type);
    }

    public void display_graphical() {
        set_table("select name,descr,id,price from pos_items;");
        frame = new JFrame("Delete Item");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String[] methods_deletion = {"Name", "Description", "Id", "Price"};
        choose_method_deletion = new JComboBox(methods_deletion);

        get_response = new JTextField();
        submit = new JButton("Submit");
        submit.addActionListener(new DeleteUserSubmitListener());
        panel.add(scrollPane);
        panel.add(submit);
        frame.add(panel);
        frame.setSize(300, 125);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		/*Thread t = new Thread(new DeleteUserDebugger());
		t.run();*/

    }

    private class DeleteUserSubmitListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            DeleteItem di = null;
            try {
                di = new DeleteItem();
            } catch (ClassNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
                return;
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
                return;
            }
            int id_del = Integer.parseInt(query_table.getValueAt(query_table.getSelectedRow(), 2).toString());
            System.out.println(id_del);
            try {
                di.delete(id_del); //compensate for index always starting with 0 by adding 1
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
                if (config.POSDebugConfig.console_debug()) {
                    e.printStackTrace();
                }
                return;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Sorry, an internal error occurred. POSReady will now exit this component.");
                if (config.POSDebugConfig.console_debug()) {
                    e.printStackTrace();
                }
                return;
            }

            JOptionPane.showMessageDialog(null, "The item was deleted successfully.");
            set_table("select name,descr,id,price from pos_items");
            panel.add(query_table);
        }


    }

    public class DeleteUserDebugger implements Runnable {
        public void run() {
            try{
                Thread.sleep(3000);

            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
            boolean do_forever = true;
            while (do_forever) {
                System.out.println("column_selected " + query_table.getSelectedRow());
                System.out.println("id: " + query_table.getValueAt(query_table.getSelectedRow(), 1));

            }
        }
    }
}





