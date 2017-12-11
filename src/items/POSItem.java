package items;

import java.sql.*;
import utils.ImportantMethods;

public class POSItem {
	private String item_name;
	private String item_descr; 
	private int item_id;
	private double item_price;
	private Connection mariadb_default; 
	public POSItem(String item_name) throws ClassNotFoundException, SQLException {
		
		mariadb_default = ImportantMethods.getRegularPOSDBConnection();

		this.item_name = item_name;
		this.item_descr = ImportantMethods.getResultString(mariadb_default, "pos_items", "descr", "name" ,item_name);
		this.item_id = ImportantMethods.getResultInt(mariadb_default, "pos_items", "id", "name", item_name);
		this.item_price = ImportantMethods.getResultDouble(mariadb_default, "pos_items", "price", "name", item_name);
		mariadb_default.close();
	}
	public POSItem(int item_id) throws SQLException, ClassNotFoundException { 
		mariadb_default = ImportantMethods.getRegularPOSDBConnection(); 
		
		this.item_id = item_id;
		this.item_descr = ImportantMethods.getResultString(mariadb_default, "pos_items", "descr", "id" ,item_id);
		this.item_name = ImportantMethods.getResultString(mariadb_default, "pos_items", "name", "id", item_id);
		this.item_price = ImportantMethods.getResultDouble(mariadb_default, "pos_items", "price", "name", item_id);
		
		mariadb_default.close(); 
	}
	
	public String getItemName() {
		return this.item_name;
	}
	
	public String getItemDescription() {
		return this.item_descr;
	}
	
	public double getItemPrice() {
		return this.item_price;
	}
	
	public int getItemId() {
		return this.item_id;
	}
	
	public boolean changeItemPrice(double new_price) throws SQLException { //must be 2 decimal places, will add validation later.
		String chItemPriceQuery = "UPDATE pos_items SET price= " + new_price + "WHERE id=" + this.item_id + ";";
		Statement updateprice = mariadb_default.createStatement();
		updateprice.executeQuery(chItemPriceQuery);
		this.item_price = ImportantMethods.getResultDouble(mariadb_default, "pos_items", "price", "name", item_name);
		if (this.item_price == new_price) {
			return true; 
		}
		return false; 
	}
}
