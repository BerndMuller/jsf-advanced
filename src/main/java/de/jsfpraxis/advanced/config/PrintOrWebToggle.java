package de.jsfpraxis.advanced.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Toggle für die CSS-Dateien section-print.css und section-web.css.
 * 
 * Wird verwendet, um Inhalt für Screenshots des Buches zu formatieren. 
 * 
 * @author Bernd Müller
 *
 */
@Named
@ApplicationScoped
public class PrintOrWebToggle {
	
	private static final String PRINT = "section-print.css";
	private static final String WEB = "section-web.css";
	
	private boolean toggle = true;

	public String getValue() {
		return toggle ? WEB : PRINT;
	}
	
	public void toggle() {
		toggle = !toggle;
	}

	
}
