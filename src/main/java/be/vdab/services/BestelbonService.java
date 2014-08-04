package be.vdab.services;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Gebruiker;

public interface BestelbonService {
	Iterable<Bestelbon> findAll();
	Bestelbon read(long id);
	void create(Bestelbon bestelbon);
}
