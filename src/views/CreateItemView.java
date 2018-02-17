package views;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import items.CreateItem;
import items.ItemNotCreatedException;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;


public class CreateItemView extends View {
    private CreateItem item_tbc;
    private JFrame frame;
    private JPanel panel;
    private JTextField item_name;
    private JTextField descr;
    private JTextField price;
    private JButton submit_btn;
    public CreateItemView(String env_type) {
        super(env_type);
    }
    public void display_graphical(){
        frame = new JFrame ("Create User");

        panel = new JPanel ();
        //configure panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //y axis box layout

        item_name = new JTextField(); //one line only of JTextField
        descr = new JTextField(5); //we'll have to limit this to 300 characters
        price = new JTextField();

        submit_btn = new JButton("Submit");
        submit_btn.addActionListener(new CreateItemViewListener());

        panel.add(item_name);
        panel.add(descr);
        panel.add(price);

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Thread validate_thread = new Thread(new CreateItemViewValidator());
        validate_thread.run();
    }

    public class CreateItemViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //fetch information
            String item_name_str = item_name.getText();
            String descr_str = descr.getText();

            double price_double = Double.parseDouble(price.getText());

            try{
                item_tbc = new CreateItem(item_name_str, descr_str, price_double);
                item_tbc.create();
            }
            catch (ClassNotFoundException cnfe){
                JOptionPane.showMessageDialog(panel, "Sorry, but the item cannot be created since POSReady cannot access the database. Please check your installation and try again.");
            }
            catch (SQLException se){
                JOptionPane.showMessageDialog(panel, "Sorry, but the POSReady database cannot be accessed at this time. Please check your POSReady installation, try again later, or make sure you are not using an unstable build.");
            }
            catch (ItemNotCreatedException ince){
                if (ince.getMessage().equals("Item exists")) {
                    JOptionPane.showMessageDialog(panel, "Sorry, but the item cannot be created - check if the item already exists");
                }
            }
        }
    }

    public class CreateItemViewValidator implements Runnable {
        public void run(){

            //get required borders
            Border oldBorder = item_name.getBorder();
            Border redBorder = BorderFactory.createLineBorder(Color.RED);

            while (true){
                if (!price.getText().matches("^(0|([1-9][0-9]*))(\\.[0-9]+)?$")){
                    //not a double
                    price.setBorder(redBorder);
                    item_name.setToolTipText("Please enter a valid item price");
                }
                else if (price.getText().matches("^(0|([1-9][0-9]*))(\\.[0-9]+)?$")){
                    //is a double
                    price.setBorder(oldBorder);
                    item_name.setToolTipText("");
                }




                if (item_name.getText().trim().isEmpty()){
                    item_name.setBorder(redBorder);
                    item_name.setToolTipText("Please enter an item name");
                }
                else if (!item_name.getText().trim().isEmpty()){
                    item_name.setBorder(oldBorder);
                    item_name.setToolTipText("");
                }




                if (descr.getText().length() > 300){
                    descr.setEditable(false);
                    descr.setToolTipText("Your description is too long!");
                }
                else if (descr.getText().length() < 300){
                    descr.setEditable(true);
                    descr.setToolTipText("");
                }


            }
        }
    }
}
