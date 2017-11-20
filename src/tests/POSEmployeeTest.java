package tests;
import java.sql.SQLException;

import auth.POSUser;

public class POSEmployeeTest {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			POSUser bob = new POSUser(1);
			System.out.println(bob.getUserLevel());
			}
		catch(SQLException e) {
				e.printStackTrace(); //stuff like config doesn't matter here!
		}
	}
	
}
