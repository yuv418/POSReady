package utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.*;

import org.apache.commons.io.FileUtils;
public class Mainpage {
public static void main(String[] args) throws IOException{
	
	List logolines = new ArrayList<String>();
	String filePath = new File("src\\utils\\poslogo.txt").getAbsolutePath();
	 File file = new File(filePath);
	 logolines = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
	 
	 for (Object line :logolines.toArray()) {
		System.out.println(line);    

	
	
}
	System.out.println("Welcome to POSReady!");  
}
}