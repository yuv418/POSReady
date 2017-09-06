package employee_implementations;
import java.sql.*;

public class POSEmployee {
/****
 * A POSReady user with basic privileges.
 */
	
private Connection mariadb_default; 
	
public POSEmployee(){
/*
 * Default POSEmployee constructor
 * 
 * 	
 */

  try {
	mariadb_default = DriverManager.getConnection("jdbc:mariadb://localhost:3306/posready", "default_u", "letmeinmysql");
  } 
  catch (SQLException e) {
	System.err.println("posready: fatal, cannot connect to posready default database"); // TODO Switch to automated printer  
  }

}




}
