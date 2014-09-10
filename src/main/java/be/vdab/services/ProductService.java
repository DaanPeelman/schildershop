package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.entities.Product;

public interface ProductService {
	Iterable<Product> findAll(int page);

	Iterable<String> findAllStijlen();

	void create(Product product);

	Product findOne(long productId);

	Iterable<Product> findByZoektermen(String zoekterm, BigDecimal vanPrijs,
			BigDecimal totPrijs, Integer vanJaartal, Integer totJaartal, int page);
	
	Iterable<Product> findNieuwsteProducten();

	Integer findMaxPrijs();

	Integer findMinJaartal();

	Integer findMaxJaartal();
	
	long findAantalProducten();
	long findAantalProductenMetZoekterm(String zoekterm, BigDecimal vanPrijs,
			BigDecimal totPrijs, Integer vanJaartal, Integer totJaartal);
	
	void update(Product product, long id);
}
