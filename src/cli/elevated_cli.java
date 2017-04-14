package cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import auth.User;
import config.POSConfig;
import auth.CreateUser;

public class elevated_cli {
	public static void initAdminPrompt(String[] args, User f){
		Scanner in = new Scanner(System.in);
		boolean page = true; 
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("newsale");
		commands.add("getusernames");
		commands.add("createusernames");
		commands.add("adduser");
		commands.add("exportitem");
		commands.add("configurestore");
		commands.add("removeuser");
		commands.add("exit");
		String command = "";
		if (f.isAdmin()){
			System.out.println("Starting Administrator Shell...");
		}
		else{
			regcli.main(args);
		}
		
		while (page){
			System.out.print("input>> ");
			command = in.next(); 
			command = command.toLowerCase();
			for (Object element:commands.toArray()){
				element = (String) element;
				if (element.equals(command)){
					if (element.equals("newsale")){
						regcli.newsale();
						
					}
					if (element.equals("getusernames")){
						 File file = new File("src\\auth\\users\\");  
						 File[] files = file.listFiles();  
						 for (File temp:files)  
						 {  
						     System.out.println(temp.getName().substring(0, temp.getName().lastIndexOf(".")));  
						     
						 }  
						 
					}
					else if (element.equals("adduser")){
						adduser();
					}
					else if (element.equals("exportitem")){
						exportitem();
					}
					else if (element.equals("configurestore")){
						configurestore(); 
					}
					else if (element.equals("removeuser")){
						removeuser();
					}
					else if (element.equals("exit")){
						System.exit(0); 
					}
					else {
						System.out.println("Command '" + command + "' is invalid.");
					
					}
					
					
					
				}
				
			}
		}
		
	}
	
	private static void removeuser() {
		Scanner in = new Scanner(System.in);
		System.out.print("User Name: ");
		String username = in.nextLine(); 
		File target = new File("src\\auth\\users\\" + username + ".usrconf");
		target.setWritable(true);
		File reg = new File(target.getAbsolutePath());
		
		reg.delete(); 
	
		
		
	}

	private static void configurestore() {
		Scanner in = new Scanner(System.in);
		System.out.print("Store Name: ");
		String storename = in.nextLine(); 
		System.out.print("\nTax: ");
		boolean taxisvalid = false; 
		double tax = 0; 
		while(!taxisvalid){
			try {
				tax = in.nextDouble();
				taxisvalid = true; 
			}
			catch (InputMismatchException e){
				System.out.println("You did not input a valid number");
			}
		}
		
		POSConfig write = new POSConfig(storename, tax);
		write.exportInfo();
		
		
		
		
	}

	public static void adduser(){
		Scanner in = new Scanner(System.in);
		System.out.print("Username: ");
		String username = in.nextLine();
		System.out.print("Password (No Spaces): ");
		String password = in.next();
		System.out.print("\nIsAdmin (y or n): ");
		String isAdmin = in.next(); 
		
		boolean isadmin = false; 
		if (isAdmin.equals("y")){
			isadmin = true; 
		}
		
		CreateUser f= null; 
		try {
			f = new CreateUser(username, password, isadmin);
		} catch (FileNotFoundException e) {
			System.err.println("Fatal User Creation Error.");
		} catch (IOException e) {
			System.err.println("Cannot Write to User Folder.");
		}
		f.write();
		System.out.println("User created successfully.");
	}
	
	public static void exportitem(){
		Scanner in = new Scanner(System.in);
		System.out.print("Item Name: ");
		String itemname = in.nextLine(); 
		System.out.print("\nPrice: ");
		double itemprice = in.nextDouble(); 
		String toappend = itemname + "=" + itemprice;
		String filename= "src\\classes\\itemnumbers.txt";
	    FileWriter fw = null; 
		try {
			fw = new FileWriter(filename,true);
		} catch (IOException e) {
			System.out.println("The item files are missing.");
		}
	    try {
			fw.write("\n" + toappend);
		} catch (IOException e) {
			System.out.println("Cannot write to the item file. Check its permissions (For Example - read only)?"); 
		}
	    try {
			fw.close();
		} catch (IOException e) {
			System.out.println("Cannot finish item close. Items file may be corrupted.");
		}
	    
	    
	    
	}
}
