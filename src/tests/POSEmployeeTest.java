package tests;
import java.sql.SQLException;

import employee_implementations.POSEmployee;

public class POSEmployeeTest {
	public static void main(String[] args) {
		try {
			POSEmployee bob = new POSEmployee("billy");
			System.out.println(bob.getUserLevel());
			}
		catch(SQLException e) {
				e.printStackTrace(); //stuff like config doesn't matter here!
		}
	}
	
}
