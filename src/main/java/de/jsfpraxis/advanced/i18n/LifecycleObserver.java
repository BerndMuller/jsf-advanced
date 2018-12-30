package de.jsfpraxis.advanced.i18n;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LifecycleObserver implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LifecycleObserver.class.getCanonicalName());
	
	@Inject
	ExternalContext externalContext;
	
	@Inject
	UIViewRoot view;

	@Override
	public void afterPhase(PhaseEvent pe) {
		log.info("after " + pe.getPhaseId());
		log.info("locale: " + view.getLocale());

	}

	@Override
	public void beforePhase(PhaseEvent pe) {
		log.info("request locale: " + externalContext.getRequestLocale());
		log.info("before " + pe.getPhaseId());
		log.info("locale: " + view.getLocale());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
