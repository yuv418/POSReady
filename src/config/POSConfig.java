package config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils; 
public class POSConfig {
private String storename;
private double tax; 
private String usernamepath;
private String plogwritepath;
private boolean canaccess; 

public POSConfig(String storename, double tax, String usernamepath, String plogwritepath){
	this.storename = storename; 
	this.tax = tax; 
	this.usernamepath = usernamepath; 
	this.plogwritepath = plogwritepath;
	this.canaccess = true; 
}
public POSConfig(){
	//do nothing. seriously.
	this.canaccess = false; //not seriously :D
}



public void exportInfo(){
	PrintWriter f = null; 
	try {
		f = new PrintWriter("src\\config\\posreadyconfig.fconf");
	} catch (FileNotFoundException e) {
		System.out.println("Cannot set preferences. Try again later.");
	} 
	f.println(this.storename);
	f.println(this.tax);
	f.println(this.usernamepath);
	f.println(this.plogwritepath);
	f.close();
}

public double getTax(){
	String filePath = new File("src\\config\\posreadyconfig.fconf").getAbsolutePath();
	 File file = new File(filePath);
	 ArrayList<String> configinfo = null; 
	 try {
		 configinfo = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
	} catch (IOException e) {
		System.out.println("Cannot read config file.");
	}
	 double returntax = 0;
	try{
		returntax = Double.parseDouble(configinfo.get(1));
		return returntax;
	}
	catch (NumberFormatException e){
		System.out.println("The config file is corrupted or has been modified from an outside source.");
	}
	return returntax; 
	
}
public String getUserNamePath(){
	String filePath = new File("src\\config\\posreadyconfig.fconf").getAbsolutePath();
	 File file = new File(filePath);
	 ArrayList<String> configinfo = null; 
	 try {
		 configinfo = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
	} catch (IOException e) {
		System.out.println("Cannot read config file.");
	}
	 
	String path = configinfo.get(2);
	return path;
}

public String getPlogWritePath(){
	String filePath = new File("src\\config\\posreadyconfig.fconf").getAbsolutePath();
	 File file = new File(filePath);
	 ArrayList<String> configinfo = null; 
	 try {
		 configinfo = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
	} catch (IOException e) {
		System.out.println("Cannot read config file.");
	}
	 
	String path = configinfo.get(3);
	return path;
}
public String getStoreName(){
	String filePath = new File("src\\config\\posreadyconfig.fconf").getAbsolutePath();
	 File file = new File(filePath);
	 ArrayList<String> configinfo = null; 
	 try {
		 configinfo = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
	} catch (IOException e) {
		System.out.println("Cannot read config file.");
	}
	 
	String storename = configinfo.get(0);
	return storename;
}

}
