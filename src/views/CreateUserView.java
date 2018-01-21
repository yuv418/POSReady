package views;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import auth.CreateUser;
import auth.EmployeeNotCreatedException;
import config.POSDebugConfig;

public class CreateUserView extends View{
    private JFrame frame;
    private JPanel panel;
    private JTextField firstname_inp;
    private JTextField middlename_inp;
    private JTextField lastname_inp;
    private JTextField username_inp;
    private JComboBox<String> user_level_cbox;
    private JPasswordField passwd_inp;
    private JButton submit_btn;
    public CreateUserView(String env_type){
        super(env_type);
    }
    public void display_graphical(){
        this.username_inp = new JTextField();
        this.firstname_inp= new JTextField();
        this.middlename_inp = new JTextField();
        this.lastname_inp = new JTextField();
        this.passwd_inp = new JPasswordField();

        JPanel username_panel = new JPanel();
        username_panel.setLayout(new BoxLayout(username_panel, BoxLayout.X_AXIS));
        JLabel username_desc = new JLabel("Username: ");
        username_panel.add(username_desc);
        username_panel.add(this.username_inp);

        JPanel firstname_panel = new JPanel();
        firstname_panel.setLayout(new BoxLayout(firstname_panel, BoxLayout.X_AXIS));
        JLabel firstname_desc = new JLabel("Firstname: ");
        firstname_panel.add(firstname_desc);
        firstname_panel.add(this.firstname_inp);

        JPanel middlename_panel = new JPanel();
        middlename_panel.setLayout(new BoxLayout(middlename_panel, BoxLayout.X_AXIS));
        JLabel middlename_desc = new JLabel("Middlename: ");
        middlename_panel.add(middlename_desc);
        middlename_panel.add(this.middlename_inp);

        JPanel lastname_panel = new JPanel();
        lastname_panel.setLayout(new BoxLayout(lastname_panel, BoxLayout.X_AXIS));
        JLabel lastname_desc = new JLabel("Lastname: ");
        lastname_panel.add(lastname_desc);
        lastname_panel.add(this.lastname_inp);

        JPanel password_panel = new JPanel();
        password_panel.setLayout(new BoxLayout(password_panel, BoxLayout.X_AXIS));
        JLabel password_desc = new JLabel("Password: ");
        password_panel.add(password_desc);
        password_panel.add(this.passwd_inp);

        String[] admin_levels = {"pos_users", "pos_admin_users", "pos_top_admin_users"};
        user_level_cbox = new JComboBox<String>(admin_levels);
        this.submit_btn = new JButton("Submit");

        submit_btn.addActionListener(new CreateUserListener());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(user_level_cbox);
        panel.add(username_panel);
        panel.add(firstname_panel);
        panel.add(middlename_panel);
        panel.add(lastname_panel);
        panel.add(password_panel);
        panel.add(submit_btn);

        JFrame frame = new JFrame("Create User");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320,125);
        frame.setVisible(true);

        Thread validator = new Thread(new CreateUserValidator());
        validator.start();
    }

    public class CreateUserValidator implements Runnable {
        public void run(){
            Border default_border = username_inp.getBorder();
            Border red_border = BorderFactory.createLineBorder(Color.RED);
            boolean validating = true;
            while (validating){
                if (username_inp.getText().isEmpty()){  //not an integer
                    username_inp.setBorder(red_border);
                    username_inp.setToolTipText("Please enter a username");
                    submit_btn.setEnabled(false);
                }
                else if (!username_inp.getText().isEmpty()){  //not an integer
                    username_inp.setBorder(default_border);
                    username_inp.setToolTipText("");
                    submit_btn.setEnabled(true);
                }
                if (firstname_inp.getText().isEmpty()){  //not an integer
                    firstname_inp.setBorder(red_border);
                    firstname_inp.setToolTipText("");
                    submit_btn.setEnabled(false);
                }
                else if (!firstname_inp.getText().isEmpty()){  //not an integer
                    firstname_inp.setBorder(default_border);
                    firstname_inp.setToolTipText("");
                    submit_btn.setEnabled(true);
                }
                if (middlename_inp.getText().isEmpty()){  //not an integer
                    middlename_inp.setBorder(red_border);
                    middlename_inp.setToolTipText("");
                    submit_btn.setEnabled(false);
                }
                else if (!middlename_inp.getText().isEmpty()){  //not an integer
                    middlename_inp.setBorder(default_border);
                    middlename_inp.setToolTipText("");
                    submit_btn.setEnabled(true);
                }
                if (lastname_inp.getText().isEmpty()){  //not an integer
                    lastname_inp.setBorder(red_border);
                    lastname_inp.setToolTipText("");
                    submit_btn.setEnabled(false);
                }
                else if (!lastname_inp.getText().isEmpty()){  //not an integer
                    lastname_inp.setBorder(default_border);
                    lastname_inp.setToolTipText("");
                    submit_btn.setEnabled(true);
                }
                if (String.valueOf(passwd_inp.getPassword()).isEmpty()){  //not an integer
                    passwd_inp.setBorder(red_border);
                    passwd_inp.setToolTipText("");
                    submit_btn.setEnabled(false);
                }
                else if (!String.valueOf(passwd_inp.getPassword()).isEmpty()){  //not an integer
                    passwd_inp.setBorder(default_border);
                    passwd_inp.setToolTipText("Please enter a password");
                    submit_btn.setEnabled(true);
                }
                if (((String) user_level_cbox.getSelectedItem()).isEmpty()){  //not an integer
                    user_level_cbox.setBorder(red_border);
                    user_level_cbox.setToolTipText("Please select a user level");
                    submit_btn.setEnabled(false);
                }
                else if (!((String) user_level_cbox.getSelectedItem()).isEmpty()){  //not an integer
                    user_level_cbox.setBorder(default_border);
                    user_level_cbox.setToolTipText("Please select a user level");
                    submit_btn.setEnabled(true);
                }
            }
        }
    }

    public class CreateUserListener implements ActionListener {

        public void actionPerformed(ActionEvent e){
            //get strings
            String username = username_inp.getText();
            String firstname = firstname_inp.getText();
            String middlename = middlename_inp.getText();
            String lastname = lastname_inp.getText();
            String passwd = String.valueOf(passwd_inp.getPassword());
            String adminlevel = (String) user_level_cbox.getSelectedItem();
            try{
                CreateUser reg_cu = new CreateUser(firstname, middlename, lastname, username, passwd, adminlevel);
                reg_cu.writeInfo();
            }
            catch (SQLException se){
                JOptionPane.showMessageDialog(null, "Sorry, but the POSReady database cannot be accessed at this time. Please check your POSReady installation or try again later.");
                if (POSDebugConfig.console_debug()){
                    se.printStackTrace();
                }
            }
            catch (ClassNotFoundException cnfe){
                JOptionPane.showMessageDialog(null, "Sorry, but the POSReady database cannot be accessed due to an installation problem. Please check your POSReady installation and try again. ");
                if (POSDebugConfig.console_debug()){
                    cnfe.printStackTrace();
                }
            }
            catch (EmployeeNotCreatedException ence){
                JOptionPane.showMessageDialog(null, "Duplicate employee! Try again.");
                if (POSDebugConfig.console_debug()){
                    ence.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "User created successfully");
        }
    }
}
