package be.vdab.web;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Klant;
import be.vdab.valueobjects.Adres;

class NieuweKlantForm extends Klant {
	private static final long serialVersionUID = 1L;
	
	private String bevestigingWachtwoord;
	
	NieuweKlantForm() {
	}
	
	NieuweKlantForm(String naam, String familienaam, Adres adres, String wachtwoord, String bevestigingWachtwoord, String emailadres) {
		setNaam(naam);
		setFamilienaam(familienaam);
		setAdres(adres);
		setWachtwoord(wachtwoord);
		this.bevestigingWachtwoord = bevestigingWachtwoord;
		setEmailadres(emailadres);
		setBestellingen(new HashSet<Bestelbon>());
	}

	public String getBevestigingWachtwoord() {
		return bevestigingWachtwoord;
	}

	public void setBevestigingWachtwoord(String bevestigingWachtwoord) {
		this.bevestigingWachtwoord = bevestigingWachtwoord;
	}
}
