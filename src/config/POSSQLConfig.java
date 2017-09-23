package config;

import utils.POSXMLReader;

public class POSSQLConfig {
	private static POSXMLReader spxr = new POSXMLReader("src/config/files/pos_sql_config.xml");
	public static String getSQLUsername() {
		return spxr.getResultByPath("/sql_credentials/sql_username");
	}
	public static String getSQLPassword() {
		return spxr.getResultByPath("/sql_credentials/sql_password");
	}
}
