package de.jsfpraxis.advanced.ajax;

import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AjaxController {
	
	private Customer customer;

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
	
	// Getter und Setter
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
