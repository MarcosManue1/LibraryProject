package exceptions;

public class SanctionedUserException extends Exception{
	
	public SanctionedUserException() {
		
	}

	public SanctionedUserException(String message) {
		
		super(message);
	}
}
