package tests;

import javax.swing.JOptionPane;

import views.TableView;

public class TableViewTest {
	public static void main(String[] args) {
		TableView test_table_view = new TableView("graphical");
		String query = JOptionPane.showInputDialog("Please input query:" );
		test_table_view.display_graphical("select username,id from users_mappings;", "TableView Test"); 
	}
}
