package items;
import java.sql.*;

import utils.ImportantMethods;
public class CreateItem {
	//this class manages items.
	//we'll take some of the logic from CreateUser.java's implementation.
	
private Connection mariadb_default = utils.ImportantMethods.getRegularPOSDBConnection();
private String item_name; 
private String item_description;
private int item_id;
private double item_price; 
private Statement do_queries = mariadb_default.createStatement();
	public CreateItem(String item_name, String item_description, double item_price) throws SQLException{
		this.item_name = item_name.trim();
		this.item_description = item_description; 
		this.item_price = item_price; 
		
		String previous_id_query = "SELECT id FROM pos_items ORDER BY id DESC LIMIT 1";
		ResultSet last_id_result = do_queries.executeQuery(previous_id_query);
		int last_id = 0;
		while (last_id_result.next()) {
			last_id = last_id_result.getInt("id");
		}
		
		this.item_id = last_id + 1;
		
		
	}
	
	public boolean create() throws SQLException, ItemNotCreatedException{
		//let's try the StringBuilder!
		String check_item_exists = ImportantMethods.getResultString(mariadb_default, "pos_items", "name", "name", item_name);
		
		if (check_item_exists.equals(item_name)) {
			
		}
		StringBuilder build_insert_query = new StringBuilder();
		build_insert_query.append("INSERT INTO pos_items VALUES(");
		build_insert_query.append("\"");
		build_insert_query.append(item_name);
		build_insert_query.append("\",\"");
		build_insert_query.append(item_description);
		build_insert_query.append("\",");
		build_insert_query.append(item_id);
		build_insert_query.append(",");
		build_insert_query.append(item_price);
		build_insert_query.append(");");
		//yeah, that was fun...... I think I'll build a class to do this for me.
		
		String insert_query = build_insert_query.toString();
		do_queries.executeQuery(insert_query);
		do_queries.close(); 
		
		return true; 
		
	}
	
	
}
