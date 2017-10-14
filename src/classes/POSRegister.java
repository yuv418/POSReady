package classes;
import java.util.Scanner;
import java.util.Map.Entry;
import java.nio.file.*;
import java.text.DecimalFormat;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.print.PrinterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.ArrayList; 
import java.io.*;
import java.math.RoundingMode;
import javax.swing.JTextArea; 
import org.apache.commons.io.*;
import config.POSConfig;
import config.POSConfig;
import java.sql.*;

public class POSRegister  {
	
	private int sales; 
	private double totalPrice; 
	private String storeName; 
	private Map<String, Double> items; 
	private int currentItem; 
	private ArrayList<String> list;
	private double salestax; //set to 1 if there is no sales tax
	private double saletax;
	private double finalPrice; 
	private ArrayList<String> itemslist; 
	private Connection mariadb_default = utils.ImportantMethods.getRegularPOSDBConnection();
	
	
	public POSRegister(String storename, double salestax) throws IOException, FileNotFoundException{
		currentItem = 0; 
		sales = 0; 
		totalPrice = 0; 
		storeName = storename; 
		items = new HashMap<String, Double>(); 
		list = new ArrayList<String>();
		String filePath = new File(new POSConfig().getUserNamePath() + "\\itemnumbers.txt").getAbsolutePath();
		 File file = new File(filePath);
		 list = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
		 this.salestax = salestax; 
		itemslist = new ArrayList<String>(); 
		
	}
	
	public void addItem(String n){
		/*
		 Add Item to total price. 
		  
		  
		  
		 
		 */
		boolean valcheck = false;
		int i = 0; 
		double thisprice = 0; 
		String[] item; 
		for (String element : list.toArray(new String[list.size()])){
			
			item = element.split("=");
			
			
			if (n.equals(item[0])){
				
				thisprice = Double.parseDouble(item[1]);
				
				totalPrice = thisprice + totalPrice; 
				items.put(item[0], thisprice);
				itemslist.add(item[0]);
				valcheck = true;
			}
			
			
		}
	}
	
	public void removeItem(String n){
		double d = getPriceOf(n);
		if (itemslist.contains(n)){
			totalPrice -= d;
			items.remove(n);
			itemslist.remove(n);
		}
		else{
			System.out.println("No such item in sale.");
		}
		
		
	}
	@Deprecated
	public void printSubTotal(){
		System.out.println(totalPrice);
	}
	@Deprecated
	public void printTotal(){
		System.out.println(finalPrice);
	}
	@Deprecated
	public void printStoreName(){
		System.out.println(storeName);
		
	}
	@Deprecated
	public void exportTotal(String outFile) throws FileNotFoundException{
		
			PrintWriter out = new PrintWriter(outFile);	
		
			
			out.println("Total Price: " + totalPrice);
			out.close();
		
	}
	public void calculateTax() {
		this.saletax = salestax * totalPrice;
		
		DecimalFormat rounder = new DecimalFormat("#.##");
		
		rounder.setRoundingMode(RoundingMode.CEILING);
		this.saletax = Double.parseDouble(rounder.format(saletax));
		
	}
	public double getSaleTax(){
		return this.saletax;
	}
	public double calculateTotalPrice(){
		this.finalPrice = saletax + totalPrice; 
		return finalPrice;
	}
	public void exportSaleInfo(String outfile) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(new POSConfig().getPlogWritePath() + "\\" + outfile);
		out.println("\t\t\t" + storeName);
		for (Entry<String, Double> entry : items.entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();
		    out.println(key + "\t\t" + value);
		    
		}
		out.println("\n\nSUBTOTAL\t\t\t" + totalPrice); 
		out.println("TAX\t\t\t"+ saletax);
		out.println("TOTAL\t\t\t"+ finalPrice);
		out.println("TOTAL NUMBER OF ITEMS SOLD = " + items.size());
		out.close();
		
	}
	public void printSale(){
		JTextArea printer = new JTextArea(); 
		String tobeprinted = ""; 
		tobeprinted += "\t" + storeName + "\n"; 
		for (Entry<String, Double> entry : items.entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();
		    tobeprinted += "\n" + key + "\t\t" + value;
		}
		
		    tobeprinted += "\n\nSUBTOTAL\t\t" + totalPrice;
		    tobeprinted += "\nTAX\t\t\t"+ saletax; 
		    tobeprinted += "\nTOTAL NUMBER OF ITEMS SOLD = " + items.size(); 
		    tobeprinted += "\nTOTAL = " + this.finalPrice;
		
		printer.setText(tobeprinted);
		   
		    try {
				printer.print();
			} catch (PrinterException e) {
				
				System.out.println("Print Failed");
			}
	
		
		}
	public Map<String, Double> getItemMap(){
		return items; 
	}
	public double getPriceOf(String n){
		boolean valcheck = false;
		int i = 0; 
		double thisprice = 0; 
		String[] item; 
		for (String element : list.toArray(new String[list.size()])){
			
			item = element.split("=");
			
			
			if (n.equals(item[0])){
			
				thisprice = Double.parseDouble(item[1]);
				
				valcheck = true;
			}
			
			
		}
				
		if (((Double)thisprice).equals(null)) {
			return 0;
		}
		else {
			return (double) thisprice;
		}
		
		
		}
	}
	
	
	
	

