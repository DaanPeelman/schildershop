package be.vdab.services;

import be.vdab.entities.Gebruiker;

public interface GebruikerService {
	void add(Gebruiker gebruiker);
	Gebruiker findByEmailadres(String emailadres);
	void update(Gebruiker gebruiker);
}
