package be.vdab.services;

import java.math.BigDecimal;
import java.util.Iterator;

import be.vdab.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();

	Iterator<Product> zoek(String titel, String schilderNaam,
			String stijl, BigDecimal vanPrijs, BigDecimal totPrijs,
			Integer vanJaartal, Integer totJaartal);

	Iterable<String> findAllStijlen();

}
