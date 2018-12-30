package de.jsfpraxis.advanced.i18n;

import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Locale.class)
public class LocaleConverter implements Converter<Locale>{

	@Override
	public Locale getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("value: " + value);
		System.out.println("locale: " + new Locale(value));
		String[] parts = value.split("_");
		if (parts.length == 1) {
			return new Locale(parts[0]);	
		} else {
			return new Locale(parts[0], parts[1]);	
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Locale value) {
		return value.toString();
	}

}
