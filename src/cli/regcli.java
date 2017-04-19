package cli;
import auth.User; 
import utils.ImportantMethods;
import classes.POSRegister;
import config.POSConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner; 
import java.util.ArrayList; 
public class regcli {

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		boolean page = true; 
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("newsale");
		commands.add("exit");
		String command = "";
		
		User f = null;
		boolean isauthorized = false;
		System.out.print("Username: ");
		String username = in.next(); 
		while (!isauthorized){
		try {
			f = new User( username + ".usrconf");
		} catch (FileNotFoundException e) {
			System.err.println("An error occurred. The user does not exist.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("An error occurred. The user could not be accessed.");
			System.exit(1);
		}
		try {
			f.auth();
			if (f.isAuthorized()){
				if (f.isAdmin()){
					
					elevated_cli.initAdminPrompt(args, f);
					isauthorized = true;
				}
				isauthorized = true;
			}
			
		} catch (IOException e) {
			System.err.println("The user specified does not exist.");
		}}
		while (page){
			System.out.print("input>> ");
			command = in.next();
			command = command.toLowerCase();
			
			for (Object element:commands.toArray()){
				element = (String) element;
				if (element.equals(command)){
					if (element.equals("newsale")){
						newsale();
					}
					else if (element.equals("exit")){
						System.exit(0);
					}
				}
				else {
					System.out.println("Command '" + command + "' is invalid.");
				}
			}
		}
				
		
	}
	
	public static void newsale(){
		ArrayList<String> items= new ArrayList<String>(); 
		System.out.println("NewSale Initiated");
		Scanner in = new Scanner(System.in);
		POSConfig c = new POSConfig("i", 2,"e", "e"); 
		String storename = c.getStoreName(); 
		POSRegister reg = null;
		try {
			reg = new POSRegister(storename,c.getTax());
		} catch (FileNotFoundException e) {
			System.out.println("Utils folder is damaged. Please fix your POSReady Installation.");
		} catch (IOException e) {
			System.out.println("Cannot read standard files.");
		}
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Method method : POSRegister.class.getDeclaredMethods()) {
		    commands.add(method.getName());
		}
		String command = "";
		boolean page = true; 
		while (page){
			System.out.print("posregister>> ");
			command = in.nextLine();
			
			
			
			for (String element: commands.toArray(new String[commands.size()])){
				element = (String) element.toLowerCase();
				
				String[] e= command.split(" ");
				e[0] = e[0].toLowerCase();
				
					if (e[0].equals("additem")){
						
						String f = ""; 
						int iter = 0;
						for (String elem : e ){
							if (iter != 0){
								f = f+ elem + " ";
							}
							iter ++;
							
						}
						f = f.trim();
						
						
						if (reg.getPriceOf(f) != 0 ){
							
							reg.addItem(f);
							items.add(f);	
						}
						else {
							System.out.println("Item '" + f + "' is invalid.");
						}
						
						
						if (reg.getPriceOf(f) != 0){
							System.out.println(f + "\t\t" + reg.getPriceOf(f));
						}
						break;
					}
				
					else if (e[0].equals("printstorename")){
					reg.printStoreName();
					break;
				}
				
					else if(e[0].equals("finishsale")){
						reg.calculateTax();
						reg.calculateTotalPrice();
						break;
					}
					
					else if (e[0].equals("recieptprint")){
						reg.calculateTax();
						reg.calculateTotalPrice();
						reg.printSale();
						
						break; 
					}
					else if (e[0].equals("printtotal")){
						reg.printTotal();
						break;
					}
					else if(e[0].equals("printsubtotal")){
						reg.printSubTotal();
						break;
					}
					else if(e[0].equals("exportsaleinfo")){
						reg.calculateTax();
						reg.calculateTotalPrice();
						try{
							if (!e[1].equals("")){
								try {
									reg.exportSaleInfo(e[1]);
								} catch (FileNotFoundException e1) {
									
									System.out.println("POSReady could not handle the request.");;
								}
							}
							else{
								System.out.println("No file argument given");
							}
						}
						catch (ArrayIndexOutOfBoundsException notgiven){
							System.out.println("No file argument given");
						}
						break;
					}
					else if (e[0].equals("removeitem")){
						String f = ""; 
						int iter = 0;
						for (String elem : e ){
							if (iter != 0){
								f = f+ elem + " ";
							}
							iter ++;
							
						}
						f = f.trim();
						reg.removeItem(f);
						break;
					}
					else if (e[0].equals("return")){
						return; 
					}
					
					
					else {
						System.out.println("Command '" + e[0]  + "' is invalid.");
						break;
					}
					
					
			}
		
				
			}
		}
		
	}

	


