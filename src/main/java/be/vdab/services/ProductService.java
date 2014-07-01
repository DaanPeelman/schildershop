package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();

	Iterable<Product> zoek(String titel, String schilderNaam,
			String stijl, BigDecimal vanPrijs, BigDecimal totPrijs,
			Integer vanJaartal, Integer totJaartal);
}
