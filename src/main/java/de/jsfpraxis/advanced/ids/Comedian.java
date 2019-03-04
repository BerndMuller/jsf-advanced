package de.jsfpraxis.advanced.ids;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comedian {

	private String firstname;
	private String lastname;
	private LocalDate dob;
	
	
	public Comedian() {
	}
	
	public Comedian(String firstname, String lastname, LocalDate dob) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
	}

	
	@SuppressWarnings("serial")
	public static List<Comedian> sample() {
		return new ArrayList<Comedian>() {{
			   add(new Comedian("Mario", "Barth", LocalDate.of(1972, 10, 1)));
			   add(new Comedian("Atze", "Schröder", LocalDate.of(1965, 8, 27)));
			   add(new Comedian("Dieter", "Nuhr", LocalDate.of(1960, 9, 29)));
			   add(new Comedian("Anke", "Engelke", LocalDate.of(1965, 11, 21)));
			   add(new Comedian("Kaya", "Yanar", LocalDate.of(1973, 4, 20)));
			}};
	}

	@Override
	public String toString() {
		return "Comedian [firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob + "]";
	}

	// Getter und Setter
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


}
