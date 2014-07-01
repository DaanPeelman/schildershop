package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Gebruiker;

public interface KlantDAO extends JpaRepository<Gebruiker, Long>{
	Gebruiker findByEmailadres(String emailadres);
}
