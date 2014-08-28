package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();

	Iterable<String> findAllStijlen();

	void create(Product product);

	Product findOne(long productId);

	Iterable<Product> findByZoekterm(String zoekterm);

	Iterable<Product> findByZoektermen(String zoekterm, BigDecimal vanPrijs,
			BigDecimal totPrijs, Integer vanJaartal, Integer totJaartal);
	
	Iterable<Product> findNieuwsteProducten();

	Integer findMinPrijs();

	Integer findMaxPrijs();

	Integer findMinJaartal();

	Integer findMaxJaartal();
	
	void createTest();
}
