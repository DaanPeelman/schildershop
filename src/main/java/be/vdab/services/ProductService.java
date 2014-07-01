package be.vdab.services;

import be.vdab.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();
}
