package tests;

		import java.sql.SQLException;

		import auth.Authenticator;
		import views.AuthenticatorView;

public class AuthenticatorViewTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AuthenticatorView av = new AuthenticatorView("cli");
		av.display_command_line();
	}
}
