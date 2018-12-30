package de.jsfpraxis.advanced.i18n;

import java.util.ListResourceBundle;

public class Numbers extends ListResourceBundle {

	private static final Object[][] contents = {
		{ "_1", "eins"}, { "_2", "zwei"}, { "_3", "drei"}, { "_4", "vier"}, { "_5", "fünf"}, 
		{ "_6", "sechs"}, { "_7", "sieben"}, { "_8", "acht"}, { "_9", "neun"}
	};
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
