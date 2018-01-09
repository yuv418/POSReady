package items;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import utils.ImportantMethods;
public class ItemModifier {
		//modifies an item to to have a rebate 
private Connection mariadb_default;  
private POSItem tb_mod; 
private double price_less; 

	public ItemModifier(String item_name, double price_less) throws ClassNotFoundException, SQLException {
		//name constructor
		this.mariadb_default = ImportantMethods.getRegularPOSDBConnection(); 
		this.tb_mod = new POSItem (item_name);
		this.price_less = price_less; 
	}
	
	public ItemModifier (int item_id, double price_less) throws ClassNotFoundException, SQLException {
		//id constructor
		this.mariadb_default = ImportantMethods.getRegularPOSDBConnection(); 
		this.tb_mod = new POSItem(item_id);
		this.price_less = price_less; 
	}
	
	public void modify_item_rebates() throws SQLException {
		String query = "INSERT INTO pos_rebates VALUES(\"" + tb_mod.getItemName() + "\", \"" + tb_mod.getItemId() + "\", " + this.price_less + ")";
		Statement exec_mod = mariadb_default.createStatement(); 
		exec_mod.executeQuery(query);
	}
	
	public void modify_item_clearance() throws SQLException{
	//the difference between rebates and clearance is that rebates is "coupon" but clearance is a set of items on clearance 
		// they will still be in the items but they cannot have a rebate!
		String query = "INSERT INTO pos_clearance VALUES(\"" + tb_mod.getItemName() + "\",\"" + tb_mod.getItemDescription() + "\"," + tb_mod.getItemId() + "," + this.price_less + ")";
		Statement exec_mod = mariadb_default.createStatement();
		exec_mod.executeQuery(query);
	}
	
	public void remove_item_rebates() throws SQLException{
		String query = "DELETE FROM pos_rebates WHERE id=" + tb_mod.getItemId() + ";"; //the reason that we query using id is because the java vm will be able to generate the query string faster 
		//thus leading to *slightly* faster performance 
		Statement exec_mod = mariadb_default.createStatement(); 
		exec_mod.executeQuery(query); 
		
	}
	
	public void remove_item_clearance() throws SQLException{
		String query = "DELETE FROM pos_clearance WHERE id=" + tb_mod.getItemId() + ";"; //same reason we query using id: performance
		Statement exec_mod = mariadb_default.createStatement(); 
		exec_mod.executeQuery(query);
	}
	
	
}

