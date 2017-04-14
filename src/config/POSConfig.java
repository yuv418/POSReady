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
public String storename;
public double tax; 
public POSConfig(String storename, double tax){
	this.storename = storename; 
	this.tax = tax; 
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
		System.out.println("The config file is corrupted.");
	}
	return returntax; 
	
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
