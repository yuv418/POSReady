package tests;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import auth.CreateUser;
import auth.EmployeeNotCreatedException; 
public class CreateUserTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, EmployeeNotCreatedException, SQLException, ClassNotFoundException {
		CreateUser v = new CreateUser("billy", "bob", "joe", "billy", "honk", "pos_top_admin_users"); 
		v.writeInfo();

	}

}
