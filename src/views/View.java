package views;

public abstract class View {
//all view classes extend view. 
private String env_type; 
	public View(String env_type) {
		this.env_type = env_type;
	}
	
	public void display_graphical() {
		//logic can go into other classes
	}
	public void display_command_line() {
		//logic can go into other classes
	}
	
	public String getEnvironmentType() {
		return this.env_type; 
	}
	
	
}
