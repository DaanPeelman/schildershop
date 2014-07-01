package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

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
	public List<Product> zoek(String titel, String schilderNaam,
		String stijl, BigDecimal vanPrijs, BigDecimal totPrijs,
		Integer vanJaartal, Integer totJaartal) {
		List<Product> resultaat = productDAO.findAll();
		if (titel != null) {
			for (Product product: resultaat) {
				if (! product.getTitel().contains(titel)) {
					resultaat.remove(product);
				}
			}
		}
		if (schilderNaam != null) {
			for (Product product: resultaat) {
				if (! product.getSchilder().getNaam().contains(schilderNaam)) {
					resultaat.remove(product);
				}
			}
		}
		if (stijl != null) {
			for (Product product: resultaat) {
				if (! product.getStijl().equals(stijl)) {
					resultaat.remove(product);
				}
			}
		}
		if (vanPrijs != null) {
			for (Product product: resultaat) {
				if (product.getPrijs().compareTo(vanPrijs) < 0) {
					resultaat.remove(product);
				}
			}
		}
		if (totPrijs != null) {
			for (Product product: resultaat) {
				if (product.getPrijs().compareTo(totPrijs) >= 0) {
					resultaat.remove(product);
				}
			}
		}
		if (vanJaartal != null) {
			for (Product product: resultaat) {
				if (product.getJaartal() < vanJaartal) {
					resultaat.remove(product);
				}
			}
		}
		if (totJaartal != null) {
			for (Product product: resultaat) {
				if (product.getJaartal() > totJaartal) {
					resultaat.remove(product);
				}
			}
		}
		return resultaat;
	}
}
