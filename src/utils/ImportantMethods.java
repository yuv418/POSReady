package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

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

}
