package library;

import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryManager extends User{

	public LibraryManager(String name, String email, String memberNumber, LocalDate registrationDate) {
		super(name, email, memberNumber, registrationDate);
	
	}
	ArrayList<User> users=new ArrayList<User>();
	ArrayList<User> loans=new ArrayList<User>();
	private int count=0;
}

