package tests;

import java.sql.SQLException;

import items.POSItem;

public class POSItemTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		POSItem i = new POSItem(1); 
		System.out.println(i.getItemDescription()); 
	}
}
