package be.vdab.services;

import be.vdab.entities.Gebruiker;

public interface GebruikerService {
	void voegToe(Gebruiker gebruiker);
	Gebruiker findByEmailadres(String emailadres);
	void wijzig(Gebruiker gebruiker);
}
