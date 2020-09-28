package de.jsfpraxis.advanced.get;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class FromViewController {

	private String input;

	public FromViewController() {
	}

	public String save() {
		return "to-view.xhtml?faces-redirect=true";
	}
	
	// Getter und Setter
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
}
