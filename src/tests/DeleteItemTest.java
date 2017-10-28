package tests;

import java.sql.SQLException;

import items.DeleteItem;

public class DeleteItemTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DeleteItem i = new DeleteItem();
		i.delete("papaya");
		
	}
	
}
