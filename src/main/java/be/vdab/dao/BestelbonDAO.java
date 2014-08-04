package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Bestelbon;

public interface BestelbonDAO extends JpaRepository<Bestelbon, Long> {
}
