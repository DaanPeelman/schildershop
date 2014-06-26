package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Klant;

public interface KlantDAO extends JpaRepository<Klant, Long>{

}
