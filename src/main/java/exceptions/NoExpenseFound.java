package exceptions;

public class NoExpenseFound extends Exception {
	
	@Override
	public String getMessage() {
		return "Application has stopped responding. Please try again later.";
	}

}
