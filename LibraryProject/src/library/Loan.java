package library;

import java.time.LocalDate;
import java.util.Scanner;

import exceptions.InvalidUserException;

public class Loan extends User{
	
	private String bookCode;
	private String bookTitle;
	private User member;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate actualReturnDate;

	public Loan(String name, String email, String memberNumber, LocalDate registrationDate) throws InvalidUserException {
		super(name, email, memberNumber, registrationDate);
		
	}
	
	public void registerReturn(LocalDate date) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the date when the book was returned: ");
		String returnData=keyboard.nextLine();
	}

	/**
	 * @return the bookCode
	 */
	public String getBookCode() {
		return bookCode;
	}

	/**
	 * @param bookCode the bookCode to set
	 */
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return the member
	 */
	public User getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(User member) {
		this.member = member;
	}

	/**
	 * @return the loanDate
	 */
	public LocalDate getLoanDate() {
		return loanDate;
	}

	/**
	 * @param loanDate the loanDate to set
	 */
	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the actualReturnDate
	 */
	public LocalDate getActualReturnDate() {
		return actualReturnDate;
	}

	/**
	 * @param actualReturnDate the actualReturnDate to set
	 */
	public void setActualReturnDate(LocalDate actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	
}
