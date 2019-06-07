package de.jsfpraxis.advanced.ajax;

public class Bezahlinfo {

	private String kontoinhaber;
	private String kontonummer; 
	private String blz;
	private String kreditkarte;
	private String gueltigBis;
	private String nummer;
	private String name;
	private String strasse;
	private String plzOrt;
	
	public Bezahlinfo() {
	}
	
	public String getKontoinhaber() {
		return kontoinhaber;
	}
	public void setKontoinhaber(String kontoinhaber) {
		this.kontoinhaber = kontoinhaber;
	}
	public String getKontonummer() {
		return kontonummer;
	}
	public void setKontonummer(String kontonummer) {
		this.kontonummer = kontonummer;
	}
	public String getBlz() {
		return blz;
	}
	public void setBlz(String blz) {
		this.blz = blz;
	}
	public String getKreditkarte() {
		return kreditkarte;
	}
	public void setKreditkarte(String kreditkarte) {
		this.kreditkarte = kreditkarte;
	}
	public String getGueltigBis() {
		return gueltigBis;
	}
	public void setGueltigBis(String gueltigBis) {
		this.gueltigBis = gueltigBis;
	}
	public String getNummer() {
		return nummer;
	}
	public void setNummer(String nummer) {
		this.nummer = nummer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getPlzOrt() {
		return plzOrt;
	}
	public void setPlzOrt(String plzOrt) {
		this.plzOrt = plzOrt;
	}

	
}
