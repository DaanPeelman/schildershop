package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import be.vdab.valueobjects.Adres;

public class Klant implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String naam;
	private String familienaam;
	private Adres adres;
	private Set<Bestelbon> bestellingen;
	private String wachtwoord;
	private String emailadres;
	
	protected Klant() {
	}

	public Klant(String naam, String familienaam, Adres adres, String wachtwoord, String emailadres) {
		this.naam = naam;
		this.familienaam = familienaam;
		this.adres = adres;
		bestellingen = new HashSet<>();
		this.wachtwoord = wachtwoord;
		this.emailadres = emailadres;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public Set<Bestelbon> getBestellingen() {
		return Collections.unmodifiableSet(bestellingen);
	}

	public void setBestellingen(Set<Bestelbon> bestellingen) {
		this.bestellingen = bestellingen;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	public String emailadres() {
		return emailadres;
	}
	
	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailadres == null) ? 0 : emailadres.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Klant other = (Klant) obj;
		if (emailadres == null) {
			if (other.emailadres != null)
				return false;
		} else if (!emailadres.equals(other.emailadres))
			return false;
		return true;
	}
}
