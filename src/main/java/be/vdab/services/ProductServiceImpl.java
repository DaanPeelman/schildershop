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
	public Iterator<Product> zoek(String titel, String schilderNaam,
		String stijl, BigDecimal vanPrijs, BigDecimal totPrijs,
		Integer vanJaartal, Integer totJaartal) {
		Iterator<Product> resultaat = productDAO.findAll().iterator();
		if (titel != null) {
			while(resultaat.hasNext()) {
				Product product = resultaat.next();
				if (! product.getTitel().contains(titel)) {
					resultaat.remove();
				}
			}
		}
		if (schilderNaam != null) {
			while(resultaat.hasNext()) {
				Product product = resultaat.next();
				if (! product.getSchilder().getNaam().contains(schilderNaam)) {
					resultaat.remove();
				}
			}
		}
		if (stijl != null) {
			while(resultaat.hasNext()) {
				Product product = resultaat.next();
				if (! product.getStijl().contains(stijl)) {
					resultaat.remove();
				}
			}
		}
		if (vanPrijs != null) {
			while(resultaat.hasNext()) {
				Product product = resultaat.next();
				if (product.getPrijs().compareTo(vanPrijs) < 0) {
					resultaat.remove();
				}
			}
		}
		if (totPrijs != null) {
			while(resultaat.hasNext()) {
				Product product = resultaat.next();
				if (product.getPrijs().compareTo(totPrijs) >= 0) {
					resultaat.remove();
				}
			}
		}
		if (vanJaartal != null) {
			while(resultaat.hasNext()) {
				Product product = resultaat.next();
				if (product.getJaartal() < vanJaartal) {
					resultaat.remove();
				}
			}
		}
		if (totJaartal != null) {
			while(resultaat.hasNext()) {
				Product product = resultaat.next();
				if (product.getJaartal() > totJaartal) {
					resultaat.remove();
				}
			}
		}
		return resultaat;
	}

	@Override
	public Iterable<String> findAllStijlen() {
		Set<String> stijlen = new HashSet<>();
		for (Product product: productDAO.findAll()) {
			stijlen.add(product.getStijl());
		}
		return stijlen;
	}
}
