package be.vdab.services;

import java.math.BigDecimal;
import java.util.*;

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

	@Override
	public Iterable<String> findAllStijlen() {
		Set<String> stijlen = new HashSet<>();
		for (Product product: productDAO.findAll()) {
			stijlen.add(product.getStijl());
		}
		return stijlen;
	}

	@Override
	public Iterable<Product> findByTitel(String titel) {
		return productDAO.findByTitelContaining(titel);
	}

	@Override
	public Iterable<Product> findBySchilderNaam(String schilderNaam) {
		return productDAO.findBySchilderNaamContaining(schilderNaam);
	}

	@Override
	public Iterable<Product> findByStijl(String stijl) {
		return productDAO.findByStijlLike(stijl);
	}

	@Override
	public Iterable<Product> findByPrijsBetween(BigDecimal vanPrijs,
			BigDecimal totPrijs) {
		return productDAO.findByPrijsBetweenOrderByPrijsAsc(vanPrijs, totPrijs);
	}

	@Override
	public Iterable<Product> findByJaartalBetween(Integer vanJaartal,
			Integer totJaartal) {
		return productDAO.findByJaartalBetweenOrderByJaartalAsc(vanJaartal, totJaartal);
	}
}
