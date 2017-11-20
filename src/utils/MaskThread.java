package utils;

//code adopted from https://stackoverflow.com/questions/10819469/hide-input-on-command-line
public class MaskThread implements Runnable {
	public boolean is_masking = true;
	public MaskThread(String msg) {
		//prints msg before mask starts
		System.out.println(msg);
	}
	
	public void run(){
		//mask console
		while(is_masking) {
			System.out.print("\010*");
			try {
				Thread.currentThread().sleep(1);
			}
			catch (InterruptedException e) {
				System.out.println("ERROR: Cannot mask password!");
				if (config.POSDebugConfig.console_debug()) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void end_masking() {
		this.is_masking = false; 
	}
}
