package tests;

import java.sql.SQLException;

import items.CreateItem;
import items.ItemNotCreatedException;

public class CreateItemTest {
	public static void main(String[] args) throws SQLException, ItemNotCreatedException, ClassNotFoundException {
		CreateItem critem = new CreateItem("papaya", "an orange, foul-tasting fruit", 1.11);
		critem.create(); 
	}
}
