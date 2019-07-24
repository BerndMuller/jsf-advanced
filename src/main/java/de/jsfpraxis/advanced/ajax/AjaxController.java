package de.jsfpraxis.advanced.ajax;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;

@Named
@RequestScoped
public class AjaxController {
	
	private static final Logger logger = Logger.getLogger(AjaxController.class.getCanonicalName());
	
	private static final String[] FAMOUS_COMPUTER_SCIENTISTS = new String[] { "Alan Turing", "John von Neumann", "Edsger W. Dijkstra" };
	
	private Customer customer;
	
	private String input;
	
	private Bezahlart bezahlart;   // fuer Beispiel 'Nicht gerenderte Komponenten'
	private Bezahlinfo bezahlinfo; // fuer Beispiel 'Nicht gerenderte Komponenten'
	
	@Inject
	FacesContext facesContext;
	
	@Inject
	ExternalContext externalContext;

	public AjaxController() {
		customer = new Customer();
		bezahlart = Bezahlart.BANKEINZUG;
	}
	
	public void update() {
		logger.info("update() aufgefufen");
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
	
	public void delay(AjaxBehaviorEvent event) {
		logger.info("eingegeben: " + input);
	}
	
	/**
	 *  Gibt die Eingabe in 'input' in einem JavaScript-Fenster aus.
	 */
	public void javaScriptWindow() {
        if (facesContext.getPartialViewContext().isAjaxRequest()) {
            try {
                externalContext.setResponseContentType("text/xml");
                externalContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer = facesContext.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startEval();
                writer.write("alert('Die Eingabe ist \\'" + input + "\\'');");
                // Achtung! Cross-Site Scripting-Gefahr, z.B. durch Eingabe 'document.URL'
                // writer.write("alert('Die Eingabe ist ' + " + input + ");");
                writer.endEval();
                writer.endDocument();
                writer.flush();
                facesContext.responseComplete();
            } catch (Exception e) {
                logger.warning("Fehler in 'javaScriptWindow()'");
            }
        }
	}
	
	/**
	 * Output all parameter of JavaScript function called by h:commandScript.
	 *  
	 * @return null
	 */
	public void commandScriptAction() {
		logger.info("commandScriptAction() called");
		Map<String, String> params = externalContext.getRequestParameterMap();
		params.forEach((k,v)-> System.out.println("key: " + k + " value: " + v));
	}
	
	public String toTarget() {
		return "navigation-target.jsf";
	}

	public String toTargetWithRedirect() {
		return "navigation-target.jsf?faces-redirect=true";
	}

	
	public String getTestData() {
		return "['eins', 'zwei', 'drei']";
	}

	
	public String[] getFamousComputerScientistsAsStringArray() {
		return FAMOUS_COMPUTER_SCIENTISTS;
	}
	
	public JsonArray getFamousComputerScientistsAsJsonArray() {
		return Json.createArrayBuilder(Arrays.asList(FAMOUS_COMPUTER_SCIENTISTS)).build();
	}
	
	public String getFamousComputerScientistsAsFakeJsonArray() {
		return Stream.of(FAMOUS_COMPUTER_SCIENTISTS)
				.map(s -> "\"" + s + "\"")
				.collect(Collectors.joining(",", "[", "]"));
	}

	
	// Getter und Setter
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	

	public Bezahlart getBezahlart() {
		return bezahlart;
	}
	public void setBezahlart(Bezahlart bezahlart) {
		this.bezahlart = bezahlart;
	}

	public Bezahlinfo getBezahlinfo() {
		return bezahlinfo;
	}
	public void setBezahlinfo(Bezahlinfo bezahlinfo) {
		this.bezahlinfo = bezahlinfo;
	}

}
