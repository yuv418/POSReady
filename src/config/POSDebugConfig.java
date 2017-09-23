package config;

public class POSDebugConfig {
	private static POSXMLReader dpxr = new POSXMLReader("src/config/files/pos_debugging_config.xml");
	public static boolean console_debug() {
		String result = dpxr.getResultByPath("/enable_console_debug");
		boolean result_b = Boolean.parseBoolean(result);
		return result_b; 
	}
}
