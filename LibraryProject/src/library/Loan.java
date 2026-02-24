package library;

import java.time.LocalDate;

public class Loan extends User{

	public Loan(String name, String email, String memberNumber, LocalDate registrationDate) {
		super(name, email, memberNumber, registrationDate);
		
	}
	private String bookCode;
	private String bookTitle;
	private User member;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate actualReturnDate;
}
