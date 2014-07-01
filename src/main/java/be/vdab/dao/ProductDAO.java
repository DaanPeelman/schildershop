package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Product;

public interface ProductDAO extends JpaRepository<Product, Long> {

}
