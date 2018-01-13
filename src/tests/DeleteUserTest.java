package tests;
import java.sql.SQLException;

import auth.DeleteUser;

public class DeleteUserTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DeleteUser a = new DeleteUser();
		a.delete(2);
	}
}
