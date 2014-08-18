package be.vdab.web;


import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Product;
import be.vdab.services.ProductService;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	private final ServletContext servletContext;
	
	private final ProductService productService;
	
	@Autowired
	public IndexController(ProductService productService, ServletContext servletContext) {
		this.productService = productService;
		this.servletContext = servletContext;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		Map<Product, Boolean> mapProducten = new ConcurrentHashMap<>();
		Iterable<Product> producten = productService.findNieuwsteProducten();
		
		for(Product product:producten) {
			String productFotoPad = servletContext.getRealPath("/img") + "\\" + product.getProductId() + ".jpg";
			File file = new File(productFotoPad);
			mapProducten.put(product, file.exists());
		}
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("laatsteProducten", mapProducten);
		
		return modelAndView;
	}
}
