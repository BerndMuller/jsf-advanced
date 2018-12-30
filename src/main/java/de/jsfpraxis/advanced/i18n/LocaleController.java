package de.jsfpraxis.advanced.i18n;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleController implements Serializable {

	private static final Logger log = Logger.getLogger(LocaleController.class.getCanonicalName());
	private static final long serialVersionUID = 1L;

	private Locale locale;    // application locale
    private List<Locale> supportedLocales;
    private Double value;
    
    @Inject
    FacesContext facesContext;
    
    @Inject
    ExternalContext externalContext; 

    @Inject
    UIViewRoot uiViewRoot;
    	

    public LocaleController() {
	}


    public void ajaxListener(AjaxBehaviorEvent event) {
    	// optional, da ganze View gerendert wird
    	//uiViewRoot.setLocale(locale);
	}

    
    public void changeLocale() {
    	log.info("locale in changeLocaleTo() gesetzt auf: " + locale);
    }

    
    public void save() {
    	log.info("gespeicherter Double-Wert: " + value);
    }

    
    public String startWorkflow() {
    	return "workflow-" + uiViewRoot.getLocale().toString();
    }
    
    
	@PostConstruct
    public void init() {
		supportedLocales = new ArrayList<Locale>();
        locale = externalContext.getRequestLocale();
        log.info("locale in init() gesetzt auf: " + locale);
        log.info("ViewHander.calculateLocale: " + facesContext.getApplication().getViewHandler().calculateLocale(facesContext));
        facesContext.getApplication().getSupportedLocales().forEachRemaining(supportedLocales::add);
        log.info("supported Locales: " + supportedLocales);
    }


	// Getter und Setter
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<Locale> getSupportedLocales() {
		return supportedLocales;
	}

	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	public List<Locale> getSupportedUtf8Locales() {
		return List.of(new Locale("zh", "CN"), new Locale("ar", "TN"));
	}
	
	public List<Locale> getSupportedNumbersLocales() {
		return List.of(new Locale("de"), new Locale("en"));
	}
	
	public Map<Character, List<String>> getJreSupportedLocales() {
		Locale[] availableLocales = Locale.getAvailableLocales();
		log.info("Number of available locales in JRE: " + availableLocales.length);
		// gibt ein 'leeres' Locale zurück, toString() liefert leeren String, muss gefiltert werden
		return Stream.of(availableLocales)
				.map(l -> l.toString())
				.sorted()
				.filter(s -> !s.isEmpty())
				.collect(Collectors.groupingBy(str -> str.charAt(0)));
	}
	
	public String getTitle() {
		return facesContext.getApplication().getResourceBundle(facesContext, "msg").getString("title");
	}
}
