package exceptions;

public class BookNotAvailableException extends Exception{
	
	public BookNotAvailableException() {
		
	}
	
	public BookNotAvailableException(String message) {
		
		super(message);
	}
}
