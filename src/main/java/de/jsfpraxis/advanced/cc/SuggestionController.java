package de.jsfpraxis.advanced.cc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * geht mit Chrome, macht Probleme mit Firefox
 * 
 * @author Bernd Müller
 *
 */
@Named
@ViewScoped
public class SuggestionController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SuggestionController.class.getCanonicalName());
	
	private String language; // Input
	private List<String> languages;
	private List<String> suggestions;

	public SuggestionController() {
	}

	public void typed(AjaxBehaviorEvent event) {
		System.out.println("Event: " + event);
		logger.info("typed 'language': " + language);
		if (language == null) {
			suggestions = new ArrayList<>();
		} else {
			suggestions = languages.stream().filter(str -> str.startsWith(language)).sorted().collect(Collectors.toList());
		}
		logger.info("zurückgeschickt: " + suggestions);
	}
	
	
	public void save() {
		logger.info("Formular abgeschickt mit 'language': " + language);
	}
	
	@PostConstruct
	public void init() {
		languages = Stream.of(Locale.getAvailableLocales())
				.map(locale -> locale.getDisplayLanguage())
				.filter(s -> !s.isEmpty())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		logger.info(languages.size() + " Sprachen für Suggestion gefunden");
	}
	
	
	// Getter und Setter
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getSuggestions() {
		return suggestions;
	}
	

}
