package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();

	List<Product> zoek(String titel, String schilderNaam,
			String stijl, BigDecimal vanPrijs, BigDecimal totPrijs,
			Integer vanJaartal, Integer totJaartal);
}
