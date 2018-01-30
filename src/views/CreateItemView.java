package views;
import java.awt.event.ActionListener;
import java.sql.*;
import items.POSItem;

import javax.swing.*;
import java.awt.event.*;

public class CreateItemView extends View {
    private POSItem item_tbc;
    private JFrame frame;
    private JPanel panel;
    private JTextField descr;
    private JTextField id;
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

        descr = new JTextField(5); //we'll have to limit this to 300 characters
        id = new JTextField();
        price = new JTextField();

        submit_btn = new JButton("Submit");
        submit_btn.addActionListener(new CreateItemViewListener());
    }

    public class CreateItemViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }
    }
}
