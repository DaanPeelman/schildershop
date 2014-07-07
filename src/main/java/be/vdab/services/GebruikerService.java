package be.vdab.services;

import be.vdab.entities.Gebruiker;
import be.vdab.valueobjects.Adres;

public interface GebruikerService {
	void create(Gebruiker gebruiker);
	Gebruiker findByEmailadres(String emailadres);
	void update(Gebruiker gebruiker);
	void updateGegevens(long id, Adres adres);
	void updateWachtwoord(long id, String wachtwoord);
	Gebruiker find(long id);
}
