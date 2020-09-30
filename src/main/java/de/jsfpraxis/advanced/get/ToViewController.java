package de.jsfpraxis.advanced.get;

import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.annotation.RequestCookieMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;

@Named
@RequestScoped
public class ToViewController {
	
	private static final Logger logger = Logger.getLogger(ToViewController.class.getCanonicalName());
	
	@Inject
	//@ManagedProperty("#{flash.keep.input}")
	@ManagedProperty("#{flash.input}")
	private String inputFromOtherController;
	
	@Inject
	ExternalContext externalContext;
	
	@Inject
	Flash flash; // mit Map-Interface
	
	@Inject
	@RequestCookieMap
	Map<String, Object> cookieMap;
	
	public ToViewController() {
	}

	public String getInputFromOtherController1() {
		return inputFromOtherController;
	}
	
	public String getInputFromOtherController2() {
		//externalContext.getFlash().keep("input");
		return (String) externalContext.getFlash().get("input");
	}
	
	public String getInputFromOtherController3() {
		//flash.keep("input");
		return (String) flash.get("input");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("redirect: " + flash.isRedirect());
		System.out.println("keep messages: " + flash.isKeepMessages());
		flash.entrySet().forEach(e -> System.out.println("flash: " + e.getKey() + " " + e.getValue()));
		cookieMap.entrySet().forEach(e -> System.out.println("cookieMap: " + e.getKey() + " " + e.getValue()));
		printCookie((Cookie) cookieMap.get("csfcfc"));
	}
	
	private void printCookie(Cookie cookie) {
		logger.info(cookie.getName());
	}
}
