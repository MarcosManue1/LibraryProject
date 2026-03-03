package exceptions;

public class RepeatedUserException extends Exception{

	public RepeatedUserException() {
		
	}
	
	public RepeatedUserException(String message) {
		
		super(message);
	}
}
