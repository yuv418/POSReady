package config;

// *1 use POSConfig to read debugging and print user-friendly messages or debug messages based on xml prefs


//java libraries imports
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.io.IOException;

//other imports
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	
	
	public String getResultById(String xmlpath) {
		/*
		  Returns a string based on custom path specification:  
		  
		  / is base of document
		  /debug/boolean where xml is <debug><boolean>RETURNS WHAT IS HERE</boolean></debug> returns RETURNS WHAT IS HERE
		  /a/b/c will return e where xml is <a><b><c>e</c></b></a>
		 */
		String[] path_id = xmlpath.split(":");
		String path = path_id[0];
		String id = path_id[1];
		String[] paths = path.split("/");
		
		NodeList nl = parsed.getElementsByTagName(paths[0]);
		Node e = nl.item(0);
		NodeList childNodes = e.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node cn = childNodes.item(i);
			if 
		}
		
		
	}
	
}
