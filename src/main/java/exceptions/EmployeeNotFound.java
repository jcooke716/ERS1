package exceptions;

public class EmployeeNotFound extends Exception{
	
	@Override
	public String getMessage() {
		return "Employee not found.";
	}

}
