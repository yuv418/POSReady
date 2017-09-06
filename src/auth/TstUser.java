package auth;

import java.io.FileNotFoundException;
import java.io.IOException;

import auth.User; 
public class TstUser {
public static void main(String[] args) throws FileNotFoundException, IOException{
	User v = new User("src\\auth\\users\\testadmin.usrconf");
			
			v.guiauth();
}
}
