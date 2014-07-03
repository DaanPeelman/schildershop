package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Schilder;

public interface SchilderDAO extends JpaRepository<Schilder, Long> {

	Iterable<Schilder> findByNaamLike(String naam);

}
