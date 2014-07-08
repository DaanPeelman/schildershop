package be.vdab.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.ProductDAO;
import be.vdab.dao.SchilderDAO;
import be.vdab.entities.Product;
import be.vdab.entities.Schilder;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
	private final ProductDAO productDAO;
	private final SchilderDAO schilderDAO;
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO, SchilderDAO schilderDAO) {
		this.productDAO = productDAO;
		this.schilderDAO = schilderDAO;
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

	@Override
	@Transactional(readOnly = false)
	public void create(Product product) {
		Schilder schilder = schilderDAO.findByNaamLike(product.getSchilder().getNaam()).iterator().next();
		product.setSchilder(schilder);
		product.setProductId(productDAO.save(product).getProductId());
	}
}
