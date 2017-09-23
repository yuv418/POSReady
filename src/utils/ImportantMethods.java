package utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import config.POSDebugConfig;
import config.POSSQLConfig;

public class ImportantMethods {
public static void logoDisplay() throws IOException{
	String filePath = new File("src\\utils\\poslogo.txt").getAbsolutePath();
	 File file = new File(filePath);
	 List logolines = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
	 
	 for (Object line : logolines.toArray()) {
		System.out.println(line);    

	
	
}
	 

	//System.out.println("POSReady Auth Page");  
}


public Connection getRegularPOSDBConnection() {
Connection mariadb_default = null;
	try {
		mariadb_default = DriverManager.getConnection("jdbc:mariadb://localhost:3306/posready", POSSQLConfig.getSQLUsername(), POSSQLConfig.getSQLPassword());
	  } 
	  catch (SQLException e) {
		if (POSDebugConfig.console_debug()) {
			System.err.println("debug: fatal, cannot connect to posready default database"); // TODO Switch to automated printer  
			e.printStackTrace();
			System.exit(-1);
		}
		else if(!POSDebugConfig.console_debug()) {
			System.err.println("Sorry, we couldn't connect to the POSReady Database at this time."); //the posready database is a glorified name for mariadb. 
			System.exit(-1);
		}
	  }
return mariadb_default;
}

}
