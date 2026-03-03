package library;

import java.time.LocalDate;
import java.util.ArrayList;
import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUserException;
import exceptions.SanctionedUserException;
import exceptions.BookNotAvailableException;

public class LibraryManager{
	
	// Lists to store users and loans
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Loan> loans = new ArrayList<Loan>();
    
    // Counters 
    private int userCount = 0;
    private int loanCount = 0;

    // Adds a new user to the users list
    // Before adding, loops through the list to check if a user with the same member number already exists. 
   
    public void registerUser(User newUser) throws RepeatedUserException {
        for (User user : users) {
            if (user.getMemberNumber().equals(newUser.getMemberNumber())) { // Before adding, loops through the list to check if a user with the same member number already exists.
                throw new RepeatedUserException("User already exists: " + newUser.getMemberNumber()); //If it does, throws RepeatedUserException.
            }
        }
        users.add(newUser);  // If not, adds the user and increments the counter.
        userCount++;
    }

    // Creates and adds a new loan to the loans list
    // If the book is available and the user is not sanctioned, creates the loan and adds it
    public void registerLoan(String bookCode, User member, String bookTitle, LocalDate loanDate)
            throws InvalidLoanException, SanctionedUserException, BookNotAvailableException {

        if (member.isSanctioned()) { // First checks if the user is sanctioned, if so, throws SanctionedUserException
            throw new SanctionedUserException("User is sanctioned and cannot borrow books");
        }
        for (Loan loan : loans) {  // Then loops through the loans list to check if the book is already on loan
            if (loan.getBookCode().equals(bookCode) && loan.getActualReturnDate() == null) {
                throw new BookNotAvailableException("Book is already on loan: " + bookCode);
            }
        }
        Loan newLoan = new Loan(bookCode, member, bookTitle, loanDate);
        loans.add(newLoan);
        loanCount++;
    }

    // Searches for a user in the list by their member number
    
    public User findUser(String memberNumber) {
        for (User user : users) {  // Loops through the users list and compares member numbers
            if (user.getMemberNumber().equals(memberNumber)) {
                return user; // Returns the User object if found, or null if no user has that member number
            }
        }
        return null;
    }

    // Finds the active loan for the given book code and registers the return
    // A loan is active if its actualReturnDate is null (book not returned yet)
    // After registering the return, checks if there was a delay:
    
    public boolean returnBook(String bookCode, LocalDate returnDate) throws InvalidLoanException {
        for (Loan loan : loans) {
            if (loan.getBookCode().equals(bookCode) && loan.getActualReturnDate() == null) { //If calculateDelayDays() returns more than 0, the user gets sanctioned
                loan.registerReturn(returnDate);	// with as many days as the delay (1 day of sanction per day of delay)
                int delayDays = loan.calculateDelayDays();
                if (delayDays > 0) {
                    loan.getMember().sanction(delayDays);
                }
                return true; // Returns true if the book was found and returned, false if no active loan was found
            }
        }
        return false;
    }

    // Returns the full list of loans
    public ArrayList<Loan> getLoans() { return loans; }
    
    // Returns the full list of users
    public ArrayList<User> getUsers() { return users; }

    // Returns a summary string with the total number of users and loans registered
    @Override
    public String toString() {
        return "LibraryManager [userCount=" + userCount + ", loanCount=" + loanCount + "]";
    }
	
}

