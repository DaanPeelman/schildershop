package be.vdab.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import be.vdab.constraints.*;
import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "gebruikers")
public class Gebruiker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long gebruikerId;
	@NotEmpty
	@Size(max = 50)
	private String naam;
	@NotEmpty
	@Size(max = 50)
	private String familienaam;
	@Valid
	@Embedded
	private Adres adres;
	@OneToMany(mappedBy = "gebruiker")
	@OrderBy("datum DESC")
	private Set<Bestelbon> bestellingen;
	@Wachtwoord
	private String wachtwoord;
	@Transient
	private String bevestigWachtwoord;
	@Emailadres
	private String emailadres;
	private boolean actief;
	@ManyToMany(mappedBy = "gebruikers")
	private Set<Rol> rollen;
	
	
	public Gebruiker() {
		this.actief = true;
		rollen = new HashSet<>();
		bestellingen = new HashSet<>();
	}

	public Gebruiker(String naam, String familienaam, Adres adres, String wachtwoord, String emailadres) {
		this.naam = naam;
		this.familienaam = familienaam;
		this.adres = adres;
		bestellingen = new HashSet<>();
		this.wachtwoord = wachtwoord;
		this.emailadres = emailadres;
		this.actief = true;
		rollen = new HashSet<>();
	}
	
	public boolean isValid() {
		if(bevestigWachtwoord == null) {
			return false;
		}
		
		return wachtwoord.equals(bevestigWachtwoord);
	}

	public long getGebruikerId() {
		return gebruikerId;
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
	
	public String getBevestigWachtwoord() {
		return bevestigWachtwoord;
	}
	
	public void setBevestigWachtwoord(String bevestigWachtwoord) {
		this.bevestigWachtwoord = bevestigWachtwoord;
	}
	
	public String getEmailadres() {
		return emailadres;
	}
	
	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}
	
	public void setActief(boolean actief) {
		this.actief = actief;
	}
	
	public boolean getActief() {
		return actief;
	}
	
	public Set<Rol> getRollen() {
		return Collections.unmodifiableSet(rollen);
	}
	
	public void addRol(Rol rol) {
		rollen.add(rol);
		
		if(!rol.getGebruikers().contains(this)) {
			rol.addGebruiker(this);
		}
	}
	
	public void removeRol(Rol rol) {
		rollen.remove(rol);
		
		if(rol.getGebruikers().contains(this)) {
			rol.removeGebruiker(this);
		}
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
		Gebruiker other = (Gebruiker) obj;
		if (emailadres == null) {
			if (other.emailadres != null)
				return false;
		} else if (!emailadres.equals(other.emailadres))
			return false;
		return true;
	}
}
