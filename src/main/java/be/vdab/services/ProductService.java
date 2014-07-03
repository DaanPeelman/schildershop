package be.vdab.services;

import java.math.BigDecimal;
import be.vdab.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();

	Iterable<String> findAllStijlen();

	Iterable<Product> findByTitel(String titel);

	Iterable<Product> findBySchilderNaam(String schilderNaam);

	Iterable<Product> findByStijl(String stijl);

	Iterable<Product> findByPrijsBetween(BigDecimal vanPrijs,
			BigDecimal totPrijs);

	Iterable<Product> findByJaartalBetween(Integer vanJaartal,
			Integer totJaartal);
}
