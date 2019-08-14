package de.jsfpraxis.advanced.components;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.inject.Named;

@Named
@RequestScoped
public class ComponentController {
	
	private static final Logger logger = Logger.getLogger(ComponentController.class.getCanonicalName());
	
	
    public List<String> getUiComponents() {
		List<String> components = new LinkedList<>();
		String fileName = jsfImplementationJar();
		if (fileName == null) {
			components.add("Application Server not supported");
		}
		logger.info("Reading Components from " + fileName);
		try (JarFile jarFile = new JarFile(fileName)) {
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				String entry = entries.nextElement().getName();
				if (entry.endsWith(".class") && entry.startsWith("javax/faces/component/") && !entry.contains("$")) {
					String name = entry.substring("javax/faces/component/".length());
					if (name.startsWith("UI")) {
						components.add(name.split(".class")[0]);		
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		components.sort(null);
		return components;
    }

    public List<String> getHtmlComponents() {
		List<String> components = new LinkedList<>();
		String fileName = jsfImplementationJar();
		if (fileName == null) {
			components.add("Application Server not supported");
		}
		logger.info("Reading Components from " + fileName);
		try (JarFile jarFile = new JarFile(fileName)) {
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				String entry = entries.nextElement().getName();
				if (entry.endsWith(".class") && entry.startsWith("javax/faces/component/html/") && !entry.contains("$")) {
					String name = entry.substring("javax/faces/component/html/".length());
					components.add(name.split(".class")[0]);		
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		components.sort(null);
		return components;
    }

    /**
     * Returns the jar file name of the JSF implementation.
     * 
     * <p>
     * Because application server use their own class loader the file name has to be canonized.
     * The URLs returned are:
     * <ul>
     * 	 <li>file:/java/server/wildfly-17.0.0.Final/modules/system/layers/base/javax/faces/api/main/jboss-jsf-api_2.3_spec-2.3.9.SP02.jar!/</li>
     *   <li>/home/java/server/payara-5.192/glassfish/modules/jakarta.faces.jar]]</li>
     *   <li>/home/java/server/apache-tomee-plus-8.0.0-M3/lib/myfaces-api-2.3.3.jar</li>
     * </ul>
     * '/java' is a symbolic link to '/home/java' at my system.
     * 
     * @return the file name or null, if application server is not supported
     */
	private String jsfImplementationJar() {
		URL url = UIComponent.class.getProtectionDomain().getCodeSource().getLocation();
		String jarPath = url.getPath();
		String fileName = null;
		if (jarPath.contains("payara")) {
			fileName = jarPath.split("]]")[0];
		} else if (jarPath.contains("wildfly")) {
			fileName = jarPath.substring("file:".length()).split("!/")[0];
		} else if (jarPath.contains("tomee")) {
			fileName = jarPath;
		} else {
			// not supported
		}
		return fileName;
	}
}
