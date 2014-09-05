package be.vdab.services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.ProductDAO;
import be.vdab.dao.SchilderDAO;
import be.vdab.entities.Product;
import be.vdab.entities.Schilder;
import be.vdab.exceptions.ProductBestaatAlException;

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
		for (Product product : productDAO.findAll()) {
			stijlen.add(product.getStijl());
		}
		return stijlen;
	}

	@Override
	@Transactional(readOnly = false)
	public void create(Product product) {
		Schilder schilder = schilderDAO
				.findByNaamLike(product.getSchilder().getNaam()).iterator()
				.next();
		product.setSchilder(schilder);
		Iterable<Product> productenMetDezelfdeTitel = productDAO
				.findByTitelContaining(product.getTitel());
		Boolean isUniek = true;
		while (isUniek && productenMetDezelfdeTitel.iterator().hasNext()) {
			Product product2 = productenMetDezelfdeTitel.iterator().next();
			if (product.equals(product2)) {
				isUniek = false;
			}
		}
		if (isUniek) {
			productDAO.save(product).getProductId();
		} else {
			throw new ProductBestaatAlException();
		}
	}

	@Override
	public Product findOne(long productId) {
		return productDAO.findOne(productId);
	}

	@Override
	public Iterable<Product> findByZoekterm(String zoekterm) {
		Set<Product> resultaat = new HashSet<>();
		for (Product product : productDAO.findByTitelContaining(zoekterm)) {
			resultaat.add(product);
		}
		for (Product product : productDAO
				.findBySchilderNaamContaining(zoekterm)) {
			resultaat.add(product);
		}
		for (Product product : productDAO.findByStijlContaining(zoekterm)) {
			resultaat.add(product);
		}
		return resultaat;
	}

	@Override
	public Iterable<Product> findNieuwsteProducten() {
		Pageable pageable = new PageRequest(0, 3, Direction.DESC, "productId");
		Page<Product> pageProducten = productDAO.findAll(pageable);

		List<Product> producten = pageProducten.getContent();

		return producten;
	}

	@Override
	public Iterable<Product> findByZoektermen(String zoekterm,
			BigDecimal vanPrijs, BigDecimal totPrijs, Integer vanJaartal,
			Integer totJaartal) {
		Set<Product> resultaat = new HashSet<>();
		for (Product ztRes : findByZoekterm(zoekterm)) {
			for (Product prijsRes : productDAO
					.findByPrijsBetweenOrderByPrijsAsc(vanPrijs, totPrijs)) {
				if (ztRes.equals(prijsRes)) {
					for (Product jtRes : productDAO
							.findByJaartalBetweenOrderByJaartalAsc(vanJaartal,
									totJaartal)) {
						if (ztRes.equals(jtRes)) {
							resultaat.add(ztRes);
						}
					}
				}
			}
		}
		return resultaat;
	}

	@Override
	public Integer findMaxPrijs() {
		return (int) (Math.ceil((productDAO.findMaxPrijs() + 10) / 10) * 10);
	}

	@Override
	public Integer findMinJaartal() {
		return productDAO.findMinJaartal();
	}

	@Override
	public Integer findMaxJaartal() {
		return productDAO.findMaxJaartal();
	}

	// @PostConstruct
	// protected void deleteAlleNieuweProducten() {
	// Iterable<Product> productenTeVerwijderen =
	// productDAO.findByProductIdGreaterThan(12);
	// List<Bestelbon> bestelbonnen = bestelbonDAO.findAll();
	//
	// for(Bestelbon bestelbon:bestelbonnen) {
	// Set<Bestelbonlijn> bestelbonlijnen = bestelbon.getBestelbonlijnen();
	//
	// for(Bestelbonlijn bestelbonlijn:bestelbonlijnen) {
	// }
	// }
	// //productDAO.deleteInBatch(productenTeVerwijderen);
	// }
}
