package classes;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import classes.POSRegister;
public class POSReadyTest {

	public static void main(String[] args) throws IOException{
		POSRegister v = null; 
		try {
			v = new POSRegister("Test Store", 0.07);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//v.addItem("All-Purpose_Flour");
		v.addItem("Big Aged Cheese");
		//v.printStoreName();
		//v.printSubTotal();
		v.exportTotal("hello.plog");
		v.calculateTax();
		v.calculateTotalPrice(); 
		v.exportSaleInfo("testsale.plog");
	
		
		
	}
}
