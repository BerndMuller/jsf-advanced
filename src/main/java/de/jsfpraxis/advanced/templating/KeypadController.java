package de.jsfpraxis.advanced.templating;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class KeypadController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(KeypadController.class.getCanonicalName());
	
	private static final String KEYPAD_TELEPHONE = "/templating/keypad-telephone.xhtml";  
	private static final String KEYPAD_CALCULATOR = "/templating/keypad-calculator.xhtml";  
	
	private String keypad = KEYPAD_TELEPHONE;
	
	@Inject
	FacesContext facesContext;

	public KeypadController() {
	}

	public void pressed(ActionEvent event) {
		HtmlCommandButton button = (HtmlCommandButton) event.getComponent();
		logger.info("gedrückt: " + button.getValue() + " in " + button.getParent().getId());
	}

	public void toggle() {
		if (keypad.equals(KEYPAD_TELEPHONE)) {
			keypad = KEYPAD_CALCULATOR;
		} else {
			keypad = KEYPAD_TELEPHONE;
		}
	}

	
	// Getter und Setter
	public String getKeypad() {
		return keypad;
	}
	
}
