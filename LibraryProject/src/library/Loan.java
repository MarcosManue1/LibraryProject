package library;

import java.time.LocalDate;
import java.util.Scanner;
import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;

public class Loan extends User{
	
	private String bookCode;
	private String bookTitle;
	private User member;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate actualReturnDate;

	public Loan(String name, String email, String memberNumber, LocalDate registrationDate) throws InvalidLoanException, InvalidUserException {
		super(name, email, memberNumber, registrationDate);
		
		this.bookCode=bookCode;
		this.bookTitle=bookTitle;
		this.member=member;
		this.loanDate=loanDate;
		this.dueDate=dueDate;
		this.actualReturnDate=actualReturnDate;
		
	}
	
	public void registerReturn(LocalDate date) throws InvalidLoanException{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the date when the book was returned: ");
		String returnData=keyboard.nextLine();
		date=LocalDate.parse(returnData);
		if(date==null) {
			throw new InvalidLoanException("Invalid, the date has not been introduced");
		}else if(date.isBefore(loanDate)) {
			throw new InvalidLoanException("Invalid, the date is not correct");
		}else {
			setActualReturnDate(date);
		}
	}
	
	public int calculateDelayDays() {
		int dateDiff;
		if(actualReturnDate==null) {
			dateDiff=dueDate.compareTo(LocalDate.now());
		}else {
			dateDiff=dueDate.compareTo(actualReturnDate);
		}
		return dateDiff;
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
