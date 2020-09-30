package de.jsfpraxis.advanced.get;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FromViewController {

	private String input;
	
	@Inject
	FacesContext facesContext;
	
	@Inject
	ExternalContext externalContext;

	public FromViewController() {
	}

	public String save() {
		saveToDatabase(input); // dummy operation
		externalContext.getFlash().put("input", input);
		return "to-view.xhtml?faces-redirect=true";
	}
	
	public String propagateMessage() {
		facesContext.addMessage(null, new FacesMessage("Eine wichtige FacesMessage"));
		externalContext.getFlash().setKeepMessages(true);
		return "to-view.xhtml?faces-redirect=true";
	}
	
	private void saveToDatabase(String input) {
		// hier ein Fake. Denken Sie z.B. an JPA, siehe Kapitel Classic Models, o.ä.
	}

	// Getter und Setter
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
}
