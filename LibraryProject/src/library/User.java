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
	
	public User(String name, String email, String memberNumber, LocalDate registrationDate) throws InvalidUserException{
		super();
		
		if (name == null || name.isBlank()) {
            throw new InvalidUserException("Name cannot be null or empty");
        }

        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new InvalidUserException("Invalid email format");
        }

        if (memberNumber == null || !memberNumber.matches("SOC\\d{5}")) {
            throw new InvalidUserException("Member number must follow format SOC + 5 digits");
        }

        if (registrationDate == null) {
            throw new InvalidUserException("Registration date cannot be null");
        }
        
        setEmail(email);
        setMemberNumber(memberNumber);
		
		this.name = name;
		this.email = email;
		this.memberNumber = memberNumber;
		this.registrationDate = registrationDate;
		this.sanctioned = false;
	    this.sanctionEndDate = null;
		
	}
	
	public void Sanction(int days) {
		
		if (days > 0) {
            this.sanctioned = true;
            this.sanctionEndDate = LocalDate.now().plusDays(days);
		}
	}
	public void liftSanction() {
		
		this.sanctioned = false;
        this.sanctionEndDate = null;
	}

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
