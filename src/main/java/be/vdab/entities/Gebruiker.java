package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gebruikers")
public class Gebruiker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long gebruikerNr;
	private String naam;
	private String role;
	private String wachtwoord;
	private String emailadres;
	
	protected Gebruiker() {
	}

	public Gebruiker(String naam, String role, String wachtwoord, String emailadres) {
		this.naam = naam;
		this.role = role;
		this.wachtwoord = wachtwoord;
		this.emailadres = emailadres;
	}

	public long getId() {
		return gebruikerNr;
	}

	public void setId(long gebruikerNr) {
		this.gebruikerNr = gebruikerNr;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	public String getEmailadres() {
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
		Gebruiker other = (Gebruiker) obj;
		if (emailadres == null) {
			if (other.emailadres != null)
				return false;
		} else if (!emailadres.equals(other.emailadres))
			return false;
		return true;
	}
}
