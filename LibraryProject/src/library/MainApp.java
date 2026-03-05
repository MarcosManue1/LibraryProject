package library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import exceptions.BookNotAvailableException;
import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUserException;
import exceptions.SanctionedUserException;

public class MainApp {
	
	private static Scanner keyboard = new Scanner(System.in);
    private static LibraryManager manager = new LibraryManager();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


	public static void main(String[] args) {
		
		int option = 0;
		
		 // Keep showing the menu until the user chooses to exit (option 8)
		do {
			
			printMenu();
			option=readInt();
			
			switch(option) {
			case 1:
				registerUser();
				break;
			case 2:
				issueLoan();
				break;
			case 3:
				returnBook();
				break;
			case 4:
				checkUserStatus();
				break;
			case 5:
				showActiveLoans();
				break;
			case 6:
				showSanctionedUsers();
				break;
			case 7:
				updateSanctions();
				break;
			case 8:
				System.out.println("BYEEEEE!");
				break;
			default:
				System.out.println("Invalid option. Please enter a number between 1 and 8.");
					
			}
			
		}while(option!=8);
		
		keyboard.close();
	}
	
	private static void printMenu() {
		System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Register new user");
        System.out.println("2. Issue book loan");
        System.out.println("3. Return book");
        System.out.println("4. Check user status");
        System.out.println("5. Show active loans");
        System.out.println("6. Show sanctioned users");
        System.out.println("7. Update sanctions");
        System.out.println("8. Exit");
        System.out.print("Write your option: ");
	}
	
	// Option 1: asks for user data, creates the User and registers it in the manager
	
	private static void registerUser() {
		
		try {
			System.out.println("Give me the name: ");
			String name=keyboard.nextLine();
			
			System.out.println("Give me the email: ");
			String email=keyboard.nextLine();
			
			System.out.println("Give me the member number (e.g. SOC00001): ");
			String memberNumber=keyboard.nextLine();
			
			System.out.println("Registration date: dd/MM/yyyy");
			LocalDate registrationDate = readDate();

            // User constructor validates email and memberNumber via setters
            User newUser = new User(name, email, memberNumber, registrationDate);

            // registerUser checks for duplicates
            manager.registerUser(newUser);
            
		}catch (InvalidUserException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RepeatedUserException e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
	
	// Option 2: asks for loan data, finds the user and registers the loan
	
    private static void issueLoan() {
    	
        try {
            System.out.print("Member number of the user: ");
            String memberNumber = keyboard.nextLine();

            // Search the user by member number

            User member = manager.findUser(memberNumber);
            if (member == null) {
                System.out.println("No user found with member number: " + memberNumber);
                return;
            }

            System.out.print("Book code (e.g. LIB0001): ");
            String bookCode = keyboard.nextLine();

            System.out.print("Book title: ");
            String bookTitle = keyboard.nextLine();

            System.out.print("Loan date (dd/MM/yyyy): ");
            LocalDate loanDate = readDate();

            manager.registerLoan(bookCode, member, bookTitle, loanDate);

            System.out.println("Loan issued successfully.");

        } catch (SanctionedUserException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
 // Option 3: asks for a book code and return date, registers the return
    // If returned late, the user gets sanctioned automatically inside returnBook()
    
    private static void returnBook() {
    	
    	try {
            System.out.print("Book code: ");
            String bookCode = keyboard.nextLine();

            System.out.print("Return date (dd/MM/yyyy): ");
            LocalDate returnDate = readDate();

            boolean returned = manager.returnBook(bookCode, returnDate);

            if (returned) {
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("No active loan found for book code: " + bookCode);
            }

        } catch (InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Option 4: asks for a member number and shows that user's full status
    
    private static void checkUserStatus() {
    	
        System.out.print("Member number: ");
        String memberNumber = keyboard.nextLine();

        User user = manager.findUser(memberNumber);

        if (user == null) {
            System.out.println("No user found with member number: " + memberNumber);
            return;
        }

        System.out.println("\n--- User Status ---");
        System.out.println(user.toString());

        if (user.isSanctioned()) {
            System.out.println("Status: SANCTIONED until " + user.getSanctionEndDate());
        } else {
            System.out.println("Status: Active (no sanctions)");
        }
    }
    
    //Ahora cada vez que le doy a la opcion return book cuando pongo la fecha de regreso y esta fuera de plazo me pone que libro regresado con exito cuando no deberia. Mira en mi clase main o loan donde pueda estar el error
    
    // Option 5: shows all loans where the book has not been returned yet
    
    private static void showActiveLoans() {
    	
        System.out.println("\n--- Active Loans ---");

        boolean found = false;
        for (Loan loan : manager.getLoans()) {
            if (loan.getActualReturnDate() == null) {
                System.out.println(loan.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no active loans.");
        }
    }
    
 // Option 6: shows all users who are currently sanctioned
    private static void showSanctionedUsers() {
        System.out.println("\n--- Sanctioned Users ---");

        boolean found = false;
        for (User user : manager.getUsers()) {
            if (user.isSanctioned()) {
                System.out.println(user.toString());
                System.out.println("Sanctioned until: " + user.getSanctionEndDate());
                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no sanctioned users.");
        }
    }
    
    // Option 7: lifts sanctions that have already expired
    private static void updateSanctions() {
        System.out.println("\n--- Updating sanctions ---");

        int count = 0;
        for (User user : manager.getUsers()) {
            // If the user has a sanctionEndDate but isSanctioned() returns false,
            // it means the sanction just expired and was lifted automatically
            if (!user.isSanctioned() && user.getSanctionEndDate() != null) {
                System.out.println("Sanction lifted for: " + user.getName());
                // Clear the sanctionEndDate now that it has been processed
                user.setSanctionEndDate(null);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No sanctions needed to be updated.");
        } else {
            System.out.println(count + " sanction(s) updated.");
        }
    }
 
 // Method that reads a date in dd/MM/yyyy format, keeps asking if the format is wrong
    
    private static LocalDate readDate() {
    	
        while (true) {
            try {
                String input = keyboard.nextLine();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.print("Invalid format. Please use dd/MM/yyyy: ");
            }
        }
    }
 // Method that reads any integer number   
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
