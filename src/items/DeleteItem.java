package items;
import java.sql.*;
import utils.ImportantMethods;

public class DeleteItem {
	
	private POSItem tbd; 
	private Connection mariadb_default;
	private Statement del_exec; 
	
	public DeleteItem() throws ClassNotFoundException, SQLException {
		mariadb_default = ImportantMethods.getRegularPOSDBConnection(); 
		del_exec = mariadb_default.createStatement(); 
	}
	
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		tbd = new POSItem(id);
		String del_query = "DELETE FROM pos_items WHERE id=" + id + ";";
		
		del_exec.execute(del_query);
		return true;
	}
	
	public boolean delete(String item_name) throws ClassNotFoundException, SQLException {
		tbd = new POSItem(item_name); 
		String del_query = "DELETE FROM pos_items WHERE name=\"" + item_name + "\"";

		del_exec.execute(del_query);
		return true; 
	}


}
