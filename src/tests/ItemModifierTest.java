package tests;

import java.sql.SQLException;

import items.ItemModifier;

public class ItemModifierTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ItemModifier itmod_test = new ItemModifier(1, 0.11);
		itmod_test.modify_item_clearance();
		
	}
}
