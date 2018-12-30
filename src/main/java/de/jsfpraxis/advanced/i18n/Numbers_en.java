package de.jsfpraxis.advanced.i18n;

import java.util.ListResourceBundle;

public class Numbers_en extends ListResourceBundle {

	private static final Object[][] contents = {
		{ "_1", "one"}, { "_2", "two"}, { "_3", "three"}, { "_4", "four"}, { "_5", "five"}, 
		{ "_6", "six"}, { "_7", "seven"}, { "_8", "eight"}, { "_9", "nine"}
	};
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
