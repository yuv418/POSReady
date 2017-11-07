package auth;

public class EmployeeNotDeletedException extends Exception{
	public EmployeeNotDeletedException(String message) {
		super(message);
	}
}
