package be.vdab.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.ProductService;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	private final ProductService productService;
	
	@Autowired
	public IndexController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("laatsteProducten", productService.findNieuwsteVijfProducten());
		
		return modelAndView;
	}
}
