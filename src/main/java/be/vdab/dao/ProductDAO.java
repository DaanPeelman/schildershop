package be.vdab.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Product;

public interface ProductDAO extends JpaRepository<Product, Long> {

	Iterable<Product> findByTitelContaining(String titel);

	Iterable<Product> findBySchilderNaamContaining(String schilderNaam);

	Iterable<Product> findByStijlLike(String stijl);

	Iterable<Product> findByPrijsBetweenOrderByPrijsAsc(BigDecimal vanPrijs,
			BigDecimal totPrijs);

	Iterable<Product> findByJaartalBetweenOrderByJaartalAsc(Integer vanJaartal,
			Integer totJaartal);
}
