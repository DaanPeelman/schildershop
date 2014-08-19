package be.vdab.web;


import java.io.File;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.*;
import be.vdab.services.ProductService;

@Controller
@RequestMapping(value = "/mandje")
public class MandjeController {
	private final ProductService productService;
	private Mandje mandje;
	private ServletContext servletContext;
	
	@Autowired
	public MandjeController(ProductService productService, Mandje mandje, ServletContext servletContext) {
		this.productService = productService;
		this.mandje = mandje;
		this.servletContext = servletContext;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showMandje() {
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		
		Bestelbon productenInMandje = new Bestelbon();
		
		for(Long productId:mandje.getProducten().keySet()) {
			Product product = productService.findOne(productId);
			productenInMandje.addBestelbonlijn(new Bestelbonlijn(product, mandje.getAantal((productId).intValue()), product.getPrijs()));
		}
		
		modelAndView.addObject("mandje", productenInMandje);
		modelAndView.addObject("adresForm", new AdresForm());
		modelAndView.addObject("verwijderUitMandjeForm", new VerwijderUitMandjeForm());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView addBestellijn(@Valid BestelProductForm bestelProductForm, BindingResult bindingResult) {
		Long productId = bestelProductForm.getProductId();
		if(!bindingResult.hasErrors()) {			
			mandje.addProduct(productId, bestelProductForm.getAantal());

			return new ModelAndView("redirect:/mandje");
		}
		ModelAndView modelAndView = new ModelAndView("producten/details");
		Product product = productService.findOne(productId);
		
		String productFotoPad = servletContext.getRealPath("/img") + "\\" + product.getProductId() + ".jpg";
		File file = new File(productFotoPad);
		modelAndView.addObject("heeftFoto", file.exists());
		
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView deleteBestellijn(@ModelAttribute VerwijderUitMandjeForm verwijderUitMandjeForm) {
		mandje.removeProduct(verwijderUitMandjeForm.getProductId());
		
		return new ModelAndView("redirect:/mandje");
	}
}
