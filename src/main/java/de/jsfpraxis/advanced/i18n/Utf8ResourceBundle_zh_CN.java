package de.jsfpraxis.advanced.i18n;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * Chinesisches Resource-Bundle mit UTF-8-codierter Datei.
 * 
 * Basierend auf http://jdevelopment.nl/internationalization-jsf-utf8-encoded-properties-files/
 * 
 * @author Bernd Müller 
 *
 */
public class Utf8ResourceBundle_zh_CN extends ResourceBundle {

    private static final String BUNDLE_NAME = "de.jsfpraxis.advanced.i18n.utf8_zh_CN";

    public Utf8ResourceBundle_zh_CN() {
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, FacesContext.getCurrentInstance().getExternalContext().getRequestLocale(), new Utf8Control()));
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

	@Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }

}

