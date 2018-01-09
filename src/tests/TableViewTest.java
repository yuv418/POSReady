package tests;

import views.TableView;

public class TableViewTest {
	public static void main(String[] args) {
		TableView test_table_view = new TableView("graphical");
		test_table_view.display_graphical("select * from pos_top_admin_users;"); 
	}
}
