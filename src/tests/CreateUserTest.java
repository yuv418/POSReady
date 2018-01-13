package tests;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import auth.CreateUser;
import auth.EmployeeNotCreatedException; 
public class CreateUserTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, EmployeeNotCreatedException, SQLException, ClassNotFoundException {

		CreateUser v1 = new CreateUser("via", "folem", "expendercen", "vfe", "onepass", "pos_users");
		v1.writeInfo();

		CreateUser v = new CreateUser("billy", "bob", "joe", "billy", "honk", "pos_top_admin_users");
		v.writeInfo();

		CreateUser v3 = new CreateUser("entermak", "sandia", "foo", "esf", "threepass", "pos_admin_users");
		v3.writeInfo();
	}

}
