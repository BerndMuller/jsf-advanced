package de.jsfpraxis.advanced.ids;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Managed Bean für Beispiele mit Komponenten- und Client-Ids.
 * 
 * @author Bernd Müller
 *
 */
@Named
@ViewScoped
public class IdController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Eingabe erforderlich";

	private String input;
	
	@Inject
	Logger logger;
	
	@Inject
	UIViewRoot view;
	
	public IdController() {
	}

	
	/**
	 * Sucht das (einzige) Formular in der View und gibt die Kompomenten mit Ids aus.
	 */
	public void showIds() {
		// vereinfacht für benanntes Formular:
		// HtmlForm form = (HtmlForm) view.findComponent("form");
		// printComponents(form, 0);
		for (UIComponent component : view.getChildren()) {
			if (component instanceof HtmlForm) {
				printComponents(component, 0);
			}
		}
	}

	
	/**
	 * Ausgabe mit Einrückung entsprechend Baumtiefe
	 */
	void printComponents(UIComponent comp, int indent) {
		String spaces = "  ".repeat(indent);
		logger.info("Komponente: " + spaces + comp.getId() + " " + comp);
		if (comp.getChildCount() != 0) {
			for (UIComponent component : comp.getChildren()) {
				printComponents(component, indent++);
			}
		}
	}
	
	/**
	 * Action-Methode die über die Id eine Eingabe prüft und eine evtuelle Nichteingabe anmahnt.
	 */
	public void save() {
		HtmlInputText inputText = (HtmlInputText) view.findComponent("form:input");
		logger.info("Farbe: " + inputText.getStyle());
		inputText.setStyle("color: black;");
		logger.info("client-id: " + inputText.getClientId());
		logger.info("input: " + input);
		logger.info("inputText.getId(): " + inputText.getId());
		logger.info("inputText.getValue(): " +  inputText.getValue());
		if (inputText.getValue() == null || inputText.getValue().equals("")) {
			inputText.setStyle("color: red;");
			inputText.setValue("Eingabe erforderlich");
			//inputText.setValue(MESSAGE);
		}
	}
	
	
	public void listener(ActionEvent ae) {
		logger.info("Checkbox wurde gechecked");
		logger.info("Aufgerufenen Komponente (Komponenten-Id): " + ae.getComponent().getId());
		logger.info("Aufgerufenen Komponente (Client-Id): " + ae.getComponent().getClientId());
	}
	
	
	public List<Comedian> getComedians() {
		return Comedian.sample();
	}
	
	// Getter und Setter
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}

	public String getMessage() {
		return MESSAGE;
	}
	
}
