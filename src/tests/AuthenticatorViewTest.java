package tests;

import views.AuthenticatorView;

public class AuthenticatorViewTest {
	public static void main(String[] args) {
		AuthenticatorView av = new AuthenticatorView("command line");
		av.display_command_line();
	}
}
