package views;
import java.sql.*;
import java.awt.*; 
import javax.swing.*;
import utils.ImportantMethods;
import utils.ImportantMethods; 
public class TableView extends View{
	private Connection mariadb_default; 
	public TableView(String env_type) {
		super(env_type);
		try {
			mariadb_default = ImportantMethods.getRegularPOSDBConnection(); //frontend so we must try catch
		}
		catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Libraries to connect to the POSReady database cannot be found. Please check your POSReady installation and try again.");
		} 
		
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Sorry, but the POSReady database is down at this time. Please try again later.");
		}
		
	}
	
	public void display_graphical(String table_query) {
		ResultSet table_result = null;
		ResultSetMetaData table_result_metadata = null;
		int rows = 0;
		int columns = 0; 
		String[] column_names = null;
		String[][] row_data = null;
		try {
			Statement get_table = mariadb_default.createStatement();
			table_result = get_table.executeQuery(table_query);
			table_result_metadata = table_result.getMetaData();
			columns = table_result_metadata.getColumnCount(); 
			column_names = new String[columns];
			while (table_result.next()) {
				rows++; 
			}
			for (int i = 0; i < columns; i++) {
				column_names[i] = table_result_metadata.getColumnLabel(i+1);
			}
			row_data = new String[rows][columns];
			table_result.first(); //move the resultset to first row to solve index errors.
			for (int i = 0 ; i < rows; i++){
				for (int j = 0; j < columns; j++) {
					row_data[i][j] = table_result.getString(column_names[j]);
				}
				i++; 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Sorry, but the POSReady database cannot be queried. Please try again later.");
			if (config.POSDebugConfig.console_debug()) {
				e.printStackTrace();
			}
			return;
		}
		JFrame frame = new JFrame("TableView(internal)");
		JPanel panel = new JPanel();
		JTable query_table = new JTable((Object[][])row_data, (Object[])column_names); 
		JScrollPane scrollPane = new JScrollPane(); 
		scrollPane.setViewportView(query_table);
		panel.add(scrollPane);
		frame.add(panel);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	
	
}
