package de.jsfpraxis.advanced.ajax;

/**
 * Einfacher Kunde.
 * 
 * @author Bernd Müller
 *
 */
public class Customer {
	
	private String firstName;
	private String lastName;
	
	public Customer() {
	}
	
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	// Getter und Setter
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
