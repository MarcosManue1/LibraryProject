package library;

import exceptions.InvalidUserException;
import java.time.LocalDate;

public class User {

	private static final Exception InvalidUserException = null;
	private String name;
	private String email;
	private String memberNumber;
	private LocalDate registrationDate;
	private boolean sancionated;
	private LocalDate sanctionEndDate;
	
	public User(String name, String email, String memberNumber, LocalDate registrationDate) throws InvalidUserException{
		super();
		
		this.name = name;
		this.email = email;
		this.memberNumber = memberNumber;
		this.registrationDate = registrationDate;
		
	}
	
	public void Sanction(int days) {
		
		setSancionated(false);
	}
	
	public static void liftSanction() {
		
	}

	public static boolean isSanctioned() {
		return true;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", memberNumber=" + memberNumber + ", registrationDate="
				+ registrationDate + ", sancionated=" + sancionated + ", sanctionEndDate=" + sanctionEndDate + "]";
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
		String reg = "(a-zA-Z),(a-zA-Z)0(a-zA-Z),(a-zA-Z)";	
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
		return sancionated;
	}

	public void setSancionated(boolean sancionated) {
		this.sancionated = sancionated;
	}

	public LocalDate getSanctionEndDate() {
		return sanctionEndDate;
	}

	public void setSanctionEndDate(LocalDate sanctionEndDate) {
		this.sanctionEndDate = sanctionEndDate;
	}
	
	
	
}
