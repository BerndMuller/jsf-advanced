package de.jsfpraxis.advanced.ajax;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/languages")
@ApplicationScoped
public class LanguagesResource {
	
	private static final Logger logger = Logger.getLogger(LanguagesResource.class.getCanonicalName());
	
	private List<String> languages;

	@Context
	UriInfo uriInfo;

	/**
	 * Schnittstelle für Suggestions.
	 * 
	 * @param input bisherige Eingabe
	 * @return passende Sprachen
	 */
	@GET
	@Path("/suggestions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponse(@QueryParam("input") String input) {
		logger.info("input: " + input);
		if (input == null || input.length() < 2) {
			return Response.ok(new String[0]).build();
		}
		String[] suggestions = languages.stream().filter(str -> str.startsWith(input)).sorted().toArray(String[]::new);
		for (int i = 0; i < suggestions.length; i++) {
			logger.info("geliefert: " + suggestions[i]);
		}
		return Response.ok(suggestions).build();
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
}
