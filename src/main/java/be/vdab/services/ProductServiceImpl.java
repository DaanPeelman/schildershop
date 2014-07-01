package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import be.vdab.dao.ProductDAO;
import be.vdab.entities.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductDAO productDAO;
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public Iterable<Product> findAll() {
		return productDAO.findAll(new Sort("titel", "jaartal"));
	}
}
