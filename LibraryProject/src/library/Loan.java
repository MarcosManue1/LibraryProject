package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import exceptions.InvalidLoanException;
import exceptions.InvalidUserException;

public class Loan{
	
	 private String bookCode;
	    private String bookTitle;
	    private User member;
	    private LocalDate loanDate;
	    private LocalDate dueDate;
	    private LocalDate actualReturnDate; // null si el libro no ha sido devuelto aún

	    // Constructor: recibe bookCode, usuario, título y fecha de préstamo
	    public Loan(String bookCode, User member, String bookTitle, LocalDate loanDate)
	            throws InvalidLoanException {

	        setBookCode(bookCode);
	        setBookTitle(bookTitle);
	        this.member = member;
	        setLoanDate(loanDate);
	        setDueDate(dueDate);
	        this.actualReturnDate = null;
	    }

	    // Registra la devolución del libro
	    public void registerReturn(LocalDate date) throws InvalidLoanException {
	        if (date == null) {
	            throw new InvalidLoanException("La fecha de devolución no puede ser nula.");
	        }
	        if (date.isBefore(loanDate)) {
	            throw new InvalidLoanException("La fecha de devolución no puede ser anterior a la fecha de préstamo.");
	        }
	        this.actualReturnDate = date;
	    }

	    // Si el libro ya fue devuelto usamos la fecha de devolución.
	    // Si no ha sido devuelto, usamos la fecha actual.
	    public int calculateDelayDays() {
	    	
	    	LocalDate referenceDate;

	        if (actualReturnDate != null) {
	            referenceDate = actualReturnDate;
	        } else {
	            referenceDate = LocalDate.now();
	        }

	        // Convertimos ambas fechas a número de días desde 1970
	        // y calculamos la diferencia.
	        
	        int delay = (int) (referenceDate.toEpochDay() - dueDate.toEpochDay());

	        // Si el resultado es negativo significa que no hay retraso,
	        // por lo tanto devolvemos 0.
	        
	        if (delay < 0) {
	            return 0;
	        }

	        return delay;
	    }

	    // Devuelve true si la fecha de vencimiento ya ha pasado (el libro está vencido)
	    public boolean isOverdue() {
	        return LocalDate.now().isAfter(dueDate);
	    }

	    @Override
	    public String toString() {
	        return "Loan [bookCode=" + bookCode + ", bookTitle=" + bookTitle
	                + ", member=" + member.getName() + ", loanDate=" + loanDate
	                + ", dueDate=" + dueDate + ", actualReturnDate=" + actualReturnDate + "]";
	    }

		/**
		 * @return the bookCode
		 */
		public String getBookCode() {
			return bookCode;
		}

		/**
		 * @param bookCode the bookCode to set
		 * @throws InvalidLoanException 
		 */
		public void setBookCode(String bookCode) throws InvalidLoanException {
			// Validar formato bookCode: 3 letras mayúsculas + 4 dígitos (ej: LIB0001)
			if (bookCode == null || !bookCode.matches("[A-Z]{3}[0-9]{4}")) {
	            throw new InvalidLoanException("El código de libro no es válido. Formato: 3 letras mayúsculas + 4 dígitos (ej: LIB0001).");
			}else {
				this.bookCode=bookCode;
			}
			
		}
			

		/**
		 * @return the bookTitle
		 */
		public String getBookTitle() {
			return bookTitle;
		}

		/**
		 * @param bookTitle the bookTitle to set
		 * @throws InvalidLoanException 
		 */
		public void setBookTitle(String bookTitle) throws InvalidLoanException {
			 // Validar título
	        if (bookTitle == null || bookTitle.isBlank()) {
	            throw new InvalidLoanException("El título del libro no puede estar vacío.");
	        }else {
	        	this.bookTitle=bookTitle;
	        }
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
		 * @throws InvalidLoanException 
		 */
		public void setLoanDate(LocalDate loanDate) throws InvalidLoanException {
			// Validar fecha: no puede ser nula ni estar en el futuro
	        if (loanDate == null) {
	            throw new InvalidLoanException("La fecha de préstamo no puede ser nula.");
	        }else if(loanDate.isAfter(LocalDate.now())) {
	        
	            throw new InvalidLoanException("La fecha de préstamo no puede ser una fecha futura.");
	        }else {
	        	this.loanDate=loanDate;
	        }
		}

		/**
		 * @return the dueDate
		 */
		public LocalDate getDueDate() {
			return dueDate;
		}

		/**
		 * @param newDate the dueDate to set
		 */
		public void setDueDate(LocalDate newDate) {
			this.dueDate = newDate.plusDays(14);
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
