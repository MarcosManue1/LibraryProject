package exceptions;

public class InvalidLoanException extends Exception{

	public InvalidLoanException () {
		
	}
		public InvalidLoanException(String message) {
		
			super(message);
	}
}