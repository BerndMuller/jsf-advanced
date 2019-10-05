package de.jsfpraxis.advanced.servletconfig;

import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.InitParameterMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ServletConfigController {

	@Inject
	@InitParameterMap
	Map<String, String> initParams;
	
	@Inject
	ExternalContext externalContext;
	
	@Inject
	FacesContext facesContext;
	
	public String getProjectStage() {
		return externalContext.getInitParameter("javax.faces.PROJECT_STAGE");
	}

	public String getProjectStage2() {
		return facesContext.getApplication().getProjectStage().toString();
	}

	public boolean isSavingStateInClient() {
		return facesContext.getApplication().getStateManager().isSavingStateInClient(facesContext);
	}

	public Map<String, String> getInitParams() {
		return initParams;
	}

	public Map<String, String> getJsfInitParams() {
		return initParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("javax.faces"))
                .collect(Collectors.toMap(e -> e.getKey(), p -> p.getValue()));
	}
	
	public Map<Object, Object> getAttributes() {
		return facesContext.getAttributes();
				
		
	}

//	public Map<Object, Object> getAttributes() {
//		System.out.println("jetzt attributes");
//		facesContext.getAttributes().entrySet().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
//		return null;
//	}
}
