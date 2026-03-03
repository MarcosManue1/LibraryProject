package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;

public class Loan{
	
	 String bookCode;
	    private String bookTitle;
	    private User member;
	    private LocalDate loanDate;
	    private LocalDate dueDate;
	    private LocalDate actualReturnDate; 

	    // Constructor: receives bookCode, user, title, and loan date
	    
	    public Loan(String bookCode, User member, String bookTitle, LocalDate loanDate)
	            throws InvalidLoanException {

	        setBookCode(bookCode);
	        setBookTitle(bookTitle);
	        this.member = member;
	        setLoanDate(loanDate);
	        setDueDate(dueDate); // due date = loanDate + 14 days
	        this.actualReturnDate = null;
	    }

	    // Registers the return of the book
	    public void registerReturn(LocalDate date) throws InvalidLoanException {
	        if (date == null) {
	            throw new InvalidLoanException("Return date cannot be null.");
	        }
	        if (date.isBefore(loanDate)) {
	            throw new InvalidLoanException("Return date cannot be before loan date.");
	        }
	        this.actualReturnDate = date;
	    }

	    // If the book has already been returned, we use the return date.
	    // If it has not been returned, we use today's date.
	    public int calculateDelayDays() {

	        LocalDate referenceDate;

	        if (actualReturnDate != null) {
	            referenceDate = actualReturnDate;
	        } else {
	            referenceDate = LocalDate.now();
	        }

	        // If the reference date is not after dueDate, there is no delay
	        if (!referenceDate.isAfter(dueDate)) {
	            return 0;
	        }

	        // Return the number of delayed days
	        return (int) dueDate.until(referenceDate).getDays();
	    }

	    // Returns true if the due date has passed (the book is overdue)
	    public boolean isOverdue() {
	        return LocalDate.now().isAfter(dueDate);
	    }

	    @Override
	    public String toString() {
	        return "Loan [bookCode=" + bookCode + ", bookTitle=" + bookTitle
	                + ", member=" + member.getName() + ", loanDate=" + loanDate
	                + ", dueDate=" + dueDate + ", actualReturnDate=" + actualReturnDate + "]";
	    }

	    public String getBookCode() {
	        return bookCode;
	    }

	    // Validates bookCode format: 3 uppercase letters + 4 digits 
	    
	    public void setBookCode(String bookCode) throws InvalidLoanException {

	        if (bookCode == null || !bookCode.matches("[A-Z]{3}[0-9]{4}")) {
	            throw new InvalidLoanException("Invalid book code format. Must be 3 uppercase letters + 4 digits (e.g., LIB0001).");
	        } else {
	            this.bookCode = bookCode;
	        }
	    }

	    public String getBookTitle() {
	        return bookTitle;
	    }

	    // Validates title (cannot be null or blank)
	    public void setBookTitle(String bookTitle) throws InvalidLoanException {
	        if (bookTitle == null || bookTitle.isBlank()) {
	            throw new InvalidLoanException("Book title cannot be empty.");
	        } else {
	            this.bookTitle = bookTitle;
	        }
	    }

	    public User getMember() {
	        return member;
	    }

	    public void setMember(User member) {
	        this.member = member;
	    }

	    public LocalDate getLoanDate() {
	        return loanDate;
	    }

	    // Validates loan date: cannot be null or in the future
	    public void setLoanDate(LocalDate loanDate) throws InvalidLoanException {

	        if (loanDate == null) {
	            throw new InvalidLoanException("Loan date cannot be null.");
	        } else if (loanDate.isAfter(LocalDate.now())) {
	            throw new InvalidLoanException("Loan date cannot be in the future.");
	        } else {
	            this.loanDate = loanDate;
	        }
	    }

	    public LocalDate getDueDate() {
	        return dueDate;
	    }

	    // Sets due date as 14 days after loan date
	    public void setDueDate(LocalDate newDate) {
	        this.dueDate = newDate.plusDays(14);
	    }

	    public LocalDate getActualReturnDate() {
	        return actualReturnDate;
	    }

	    public void setActualReturnDate(LocalDate actualReturnDate) {
	        this.actualReturnDate = actualReturnDate;
	    }
}
