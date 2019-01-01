package de.jsfpraxis.advanced.i18n;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Einfacher Kunde für anwendungsdefinierte Constraints mit lokalisierten Fehlermeldungen.
 * 
 * @author Bernd Müller
 *
 */
public class CustomerBV {
	
	@NotNull
	//@Size(min=3, max=30, message = "Muss mindestens {min} sein")
	@Size(min=3, max=30)
	private String name;
	
	@NotNull
	@Volljaehrig
	private LocalDate dob;
	
	public CustomerBV() {
	}
	
	// Getter und Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

}
