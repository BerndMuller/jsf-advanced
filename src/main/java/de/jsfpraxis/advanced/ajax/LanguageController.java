package de.jsfpraxis.advanced.ajax;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LanguageController {
	
	private String language;
	private List<String> languages;

	public LanguageController() {
	}
	
	@PostConstruct
	public void init() {
		languages = Stream.of(Locale.getAvailableLocales())
				.map(locale -> locale.getDisplayLanguage())
				.filter(s -> !s.isEmpty())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		System.out.println("gefunden: " + languages.size());
	}
	
	// Getter und Setter
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getLanguages() {
		return languages;
	}

}
