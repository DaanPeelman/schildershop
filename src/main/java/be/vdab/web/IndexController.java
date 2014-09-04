package be.vdab.web;


import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Product;
import be.vdab.services.ProductService;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	private final ServletContext servletContext;
	
	private final ProductService productService;
	
	private Mandje mandje;
	
	@Autowired
	public IndexController(ProductService productService, ServletContext servletContext, Mandje mandje) {
		this.productService = productService;
		this.servletContext = servletContext;
		this.mandje = mandje;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		Map<Product, Boolean> mapProducten = new ConcurrentHashMap<>();
		Iterable<Product> producten = productService.findNieuwsteProducten();
		
		for(Product product:producten) {
			String productFotoPad = servletContext.getRealPath(File.separator + "img") + File.separator + product.getProductId() + ".jpg";
			File file = new File(productFotoPad);
			mapProducten.put(product, file.exists());
		}
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("laatsteProducten", mapProducten);
		modelAndView.addObject("bestelProductForm", new BestelProductForm());
		
		return modelAndView;
	}
}
