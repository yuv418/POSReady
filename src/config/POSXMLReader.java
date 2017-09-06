package config;

// *1 use POSConfig to read debugging and print user-friendly messages or debug messages based on xml prefs


//java libraries imports
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

//other imports
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class POSXMLReader {

private File specified; 	
private DocumentBuilderFactory builderf; 
private DocumentBuilder builder; 
private Document parsed; 

	public POSXMLReader(String file_path) {
	
		try {
			specified = new File(file_path);
		}
		catch (Exception ie) {
			ie.printStackTrace(); //TODO *1 
		}
	
		builderf = DocumentBuilderFactory.newInstance();
	
		try {
			builder = builderf.newDocumentBuilder();
			parsed = builder.parse(specified);
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace(); //TODO *1
		}
		catch (SAXException se) {
			se.printStackTrace(); //TODO *1
		}
		catch (IOException ie) {
			ie.printStackTrace(); //TODO *1
		}
	}
	
	
}
