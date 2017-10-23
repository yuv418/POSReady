package tests;
import java.sql.SQLException;

import employee_implementations.POSEmployee;

public class POSEmployeeTest {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			POSEmployee bob = new POSEmployee(1);
			System.out.println(bob.getUserLevel());
			}
		catch(SQLException e) {
				e.printStackTrace(); //stuff like config doesn't matter here!
		}
	}
	
}
