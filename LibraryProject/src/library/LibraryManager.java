package library;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUserException;

public class LibraryManager{

	
	ArrayList<User> users=new ArrayList<User>();
	ArrayList<Loan> loans=new ArrayList<Loan>();
	private int userCount=0;
	private int loanCount=0;
	
	public void registerUser(User newUser) throws InvalidUserException, RepeatedUserException{
		Scanner keyboard = new Scanner (System.in);
		System.out.println("Enter the name of the user: ");
		String name=keyboard.nextLine();
		System.out.println("Enter the email of the user: ");
		String email=keyboard.nextLine();
		System.out.println("Enter the member number of the user: ");
		String memberNumber=keyboard.nextLine();
		System.out.println("Enter the registration date: ");
		String date=keyboard.nextLine();
		LocalDate registrationDate=LocalDate.parse(date);
		newUser=new User(name, email, memberNumber, registrationDate);
		users.add(newUser);
		
		if(users.contains(newUser)) {
			throw new RepeatedUserException();
		}else {
			users.add(newUser);
		}
	}
	
	public void registerLoan(Loan newLoan) throws InvalidLoanException{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the book code: ");
		String bookCode=keyboard.nextLine();
		System.out.println("Enter the book title: ");
		String bookTitle=keyboard.nextLine();
		System.out.println("Enter the user: ");
		String user=keyboard.nextLine();
		users.
	}
}

