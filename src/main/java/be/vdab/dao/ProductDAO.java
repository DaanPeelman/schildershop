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

	Iterable<Product> findByStijlContaining(String stijl);

	Integer findMinPrijs();
	
	Integer findMaxPrijs();
	
	Integer findMinJaartal();
	
	Integer findMaxJaartal();
	
	Iterable<Product> findByProductIdGreaterThan(long id);
	
	Iterable<Product> findByTitelContainingAndPrijsBetweenAndJaartalBetweenOrStijlContainingAndPrijsBetweenAndJaartalBetweenOrSchilderNaamContainingAndPrijsBetweenAndJaartalBetweenOrderByTitelAsc(String titelTerm, BigDecimal vanPrijsTitel, BigDecimal totPrijsTitel, int startJaarTitel, int eindJaarTitel, String stijlTerm, BigDecimal vanPrijsStijl, BigDecimal totPrijsStijl, int startJaarStijl, int eindJaarStijl, String Schilderterm, BigDecimal vanPrijsSchilder, BigDecimal totPrijsSchilder, int startJaarSchilder, int eindJaarSchilder);
}
