package de.jsfpraxis.advanced.ajax;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AjaxController {
	
	private static final Logger logger = Logger.getLogger(AjaxController.class.getCanonicalName());
	
	private Customer customer;
	
	@Inject
	FacesContext facesContext;
	
	@Inject
	ExternalContext externalContext;

	public AjaxController() {
		customer = new Customer();
	}
	
	/**
	 * Whole name of customer. 
	 * 
	 * @return whole name of customer, i.e. 'firstname blank lastname' 
	 */
	public String wholeCustomerName() {
		return (Objects.isNull(customer.getFirstName()) ? "" : customer.getFirstName())
				+ " "
				+ (Objects.isNull(customer.getLastName()) ? "" : customer.getLastName());
	}
	
	/**
	 * Event listener for navigation.
	 * 
	 * @param event 
	 * @throws IOException if navigation fails
	 */
	public void redirect(AjaxBehaviorEvent event) throws IOException {
		String url = facesContext.getApplication().getViewHandler().getActionURL(facesContext, "/ajax/navigation-target.xhtml");
		String urlEncoded = externalContext.encodeActionURL(url);
		externalContext.redirect(urlEncoded);
	}
	
	public String toTarget() {
		return "navigation-target.jsf";
	}

	public String toTargetWithRedirect() {
		return "navigation-target.jsf?faces-redirect=true";
	}

	// Getter und Setter
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
