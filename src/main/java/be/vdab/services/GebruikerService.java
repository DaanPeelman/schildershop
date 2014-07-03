package be.vdab.services;

import be.vdab.entities.Gebruiker;

public interface GebruikerService {
	void create(Gebruiker gebruiker);
	Gebruiker findByEmailadres(String emailadres);
	void update(Gebruiker gebruiker);
}
