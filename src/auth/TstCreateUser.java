package auth;
import java.io.FileNotFoundException;
import java.io.IOException;

import auth.CreateUser; 
public class TstCreateUser {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		CreateUser v = new CreateUser("billy", "honk", true); 
		v.write();

	}

}
