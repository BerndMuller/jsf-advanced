package de.jsfpraxis.advanced.get;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Demonstrator GET vs POST.
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Named
@RequestScoped
public class GetController implements Serializable {
	
	private static final Logger logger = Logger.getLogger(GetController.class.getCanonicalName());

	//@Size(min = 1, max = 10)
	private String message;

	public GetController() {
	}
	

	public String action() {
		logger.info("Action-Methode mit message: " + message);
		return "target.xhtml";
	}

	public String actionWithRedirect() {
		logger.info("Action-Methode mit message: " + message);
		return "target.xhtml?faces-redirect=true";
	}

	
	// Getter und Setter
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@PostConstruct
	public void init() {
		logger.info("GetController-Bean erzeugt");
	}

	@PreDestroy
	public void cleanUp() {
		logger.info("GetController-Bean gelöscht");
	}
}
