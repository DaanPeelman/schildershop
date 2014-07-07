package be.vdab.services;

import be.vdab.entities.Schilder;

public interface SchilderService {
	void create(Schilder schilder);

	Iterable<Schilder> findAll();
}
