package tests;
import config.POSSQLConfig;
public class SQLConfigTest {
	public static void main(String[] args) {
		System.out.println(POSSQLConfig.getSQLUsername());
		System.out.println(POSSQLConfig.getSQLPassword());
	}
}
