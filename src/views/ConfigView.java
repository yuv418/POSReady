package views;

import javax.swing.*; 
import utils.POSXMLReader;

public class ConfigView extends View {

	public ConfigView(String env_type) {
		super(env_type);
		
	}
	
	public void display_graphical() {
		JFrame main_view = new JFrame("Config");
		
		JTabbedPane tbp = new JTabbedPane(); 
		

		
		main_view.setVisible(true);
	}
	
}
