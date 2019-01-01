package de.jsfpraxis.advanced.i18n;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CustomerBVController {
	
	private static final Logger logger = Logger.getLogger(CustomerBVController.class.getCanonicalName());

	private CustomerBV customer;

	public CustomerBVController() {
		customer = new CustomerBV();
	}
	
	public void save() {
		logger.info("saved: " + customer);
	}

	// Getter und Setter
	public CustomerBV getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBV customer) {
		this.customer = customer;
	}

}
