package library;

import exceptions.InvalidUserException;
import java.time.LocalDate;

public class User {

	private String name;
	private String email;
	private String memberNumber;
	private LocalDate registrationDate;
	private boolean sanctioned;
	private LocalDate sanctionEndDate;
	
	// Constructor: creates a new user and validates email and member number
	public User(String name, String email, String memberNumber, LocalDate registrationDate) throws InvalidUserException{
		super();
        
        setEmail(email);
        setMemberNumber(memberNumber);
		
		this.name = name;
		this.registrationDate = registrationDate;
		this.sanctioned = false;
	    this.sanctionEndDate = null;
		
	}
	 // Applies a sanction for a number of days
	public void sanction(int days) {
		
		if (days > 0) {
            this.sanctioned = true;
            this.sanctionEndDate = LocalDate.now().plusDays(days);
		}
	}
	// Removes the current sanction
	public void liftSanction() {
		
		this.sanctioned = false;
        this.sanctionEndDate = null;
	}
	// Checks if the user is currently sanctioned
    // If the sanction has expired, it is removed automatically
	public boolean isSanctioned() {
		if (!sanctioned) {
            return false;
        }

        if (LocalDate.now().isAfter(sanctionEndDate)) {
            liftSanction(); 
            return false;
        }

        return true;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", memberNumber=" + memberNumber + ", registrationDate="
				+ registrationDate + ", sancionated=" + sanctioned + ", sanctionEndDate=" + sanctionEndDate + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	// Validates and sets email
	public void setEmail(String email) throws InvalidUserException {
		String reg = "[a-zA-Z].\\w+";
		
		if(email.matches(reg)) {
			this.email = email;
		}else {
			throw new InvalidUserException("Email not valid");
		}
	
	}

	public String getMemberNumber() {
		return memberNumber;
	}
	 // Validates and sets member number (SOC + 5 digits)
	public void setMemberNumber(String memberNumber)throws InvalidUserException{
		String reg="SOC[0-9]{5}";
		if(memberNumber.matches(reg)) {
			this.memberNumber = memberNumber;
		}else {
			throw new InvalidUserException("Member number not valid");
		}
		
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isSancionated() {
		return sanctioned;
	}

	public void setSancionated(boolean sancionated) {
		this.sanctioned = sancionated;
	}

	public LocalDate getSanctionEndDate() {
		return sanctionEndDate;
	}

	public void setSanctionEndDate(LocalDate sanctionEndDate) {
		this.sanctionEndDate = sanctionEndDate;
	}
	
}
