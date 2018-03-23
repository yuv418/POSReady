package views;
import java.sql.*;
import java.awt.*; 
import javax.swing.*;
import utils.ImportantMethods;
import java.util.ArrayList;

public class TableView extends View{
	private Connection mariadb_default; 
	protected JFrame frame; 
	protected JPanel panel; 
	protected JScrollPane scrollPane; 
	protected JTable query_table; 
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
	
	public void display_graphical(String table_query, String title) {
		set_table(table_query); 
		frame = new JFrame(title);
		panel = new JPanel();
		panel.add(scrollPane);
		frame.add(panel);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	protected void set_table(String table_query) {
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
			//System.out.println("rows "+rows + " columns " + columns);
			row_data = new String[rows][columns];
			table_result.first(); //move the resultset to first row to solve index errors.
			/*while (table_result.next()) {

			}*/
			for (int i = 0 ; i < rows; i++){
				for (int j = 0; j < columns; j++) {
					row_data[i][j] = table_result.getString(column_names[j]);
					//System.out.println(column_names[j]);
				}
				
				table_result.next(); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Sorry, but the POSReady database cannot be queried. Please try again later.");
			if (config.POSDebugConfig.console_debug()) {
				e.printStackTrace();
			}
			return;
		}
		query_table = new JTable((Object[][])row_data, (Object[])column_names); 
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(query_table);
	}
	
	public Object[][] get_query_arr (String table_query) {
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
			System.out.println("rows "+rows + " columns " + columns);
			row_data = new String[rows][columns];
			table_result.first(); //move the resultset to first row to solve index errors.
			/*while (table_result.next()) {
				
			}*/
			for (int i = 0 ; i < rows; i++){
				for (int j = 0; j < columns; j++) {
					row_data[i][j] = table_result.getString(column_names[j]);
					//System.out.println(column_names[j]);
				}
				
				table_result.next(); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Sorry, but the POSReady database cannot be queried. Please try again later.");
			if (config.POSDebugConfig.console_debug()) {
				e.printStackTrace();
			}
			return null;
		}
		return (Object[][]) row_data;
	}
	
}
