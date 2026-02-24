package library;

import java.time.LocalDate;

public class User {

	private String name;
	private String email;
	private String memberNumber;
	private LocalDate registrationDate;
	private boolean sancionated;
	private LocalDate sanctionEndDate;
	
	public User(String name, String email, String memberNumber, LocalDate registrationDate) {
		super();
		
		this.name = name;
		this.email = email;
		this.memberNumber = memberNumber;
		this.registrationDate = registrationDate;
		
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

	public void setEmail(String email) {
		String reg = "(a-zA-Z),(a-zA-Z)0(a-zA-Z),(a-zA-Z)";
		
		if(email.equals(reg)) {
			this.email = email;
		}
		
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
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
