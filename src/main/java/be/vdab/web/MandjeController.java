package be.vdab.web;


import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.dao.GebruikerDAO;
import be.vdab.entities.*;
import be.vdab.services.GebruikerService;
import be.vdab.services.ProductService;

@Controller
@RequestMapping(value = "/mandje")
public class MandjeController {
	private final ProductService productService;
	private final GebruikerService gebruikerService;
	private Mandje mandje;
	private ServletContext servletContext;
	
	@Autowired
	public MandjeController(ProductService productService, GebruikerService gebruikerService, Mandje mandje, ServletContext servletContext) {
		this.productService = productService;
		this.gebruikerService = gebruikerService;
		this.mandje = mandje;
		this.servletContext = servletContext;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showMandje(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		Bestelbon productenInMandje = new Bestelbon();
		
		for(Long productId:mandje.getProducten().keySet()) {
			Product product = productService.findOne(productId);
			productenInMandje.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(productId), product.getPrijs()));
		}
		
		modelAndView.addObject("productenInMandje", productenInMandje);
		
		MandjeForm mandjeForm;
		
		if(mandje.getAdres() == null) {
			mandjeForm = new MandjeForm(new AdresForm());
		} else {
			mandjeForm = new MandjeForm(new AdresForm(mandje.getAdres()));
		}
		modelAndView.addObject("mandjeForm", mandjeForm);
		modelAndView.addObject("verwijderUitMandjeForm", new VerwijderUitMandjeForm());
		
		if(request.getUserPrincipal() != null) {
			Gebruiker gebruiker = gebruikerService.findByEmailadres(request.getUserPrincipal().getName());
			
			modelAndView.addObject("adresGebruiker", gebruiker.getAdres());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addBestellijn(@Valid BestelProductForm bestelProductForm, BindingResult bindingResult) {
		Long productId = bestelProductForm.getProductId();
		if(!bindingResult.hasErrors()) {			
			mandje.addProduct(productId, bestelProductForm.getAantal());

			return new ModelAndView("redirect:/mandje");
		}
		ModelAndView modelAndView = new ModelAndView("producten/details");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		Product product = productService.findOne(productId);
		
		String productFotoPad = servletContext.getRealPath("/img") + "\\" + product.getProductId() + ".jpg";
		File file = new File(productFotoPad);
		modelAndView.addObject("heeftFoto", file.exists());
		
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView wijzigMandje(@Valid MandjeForm mandjeForm, BindingResult bindingResult, HttpServletRequest request) {
		if(!bindingResult.hasErrors()) {
			for(MandjeFormLijn lijn:mandjeForm.getLijnen()) {
				mandje.addProduct(lijn.getId(), lijn.getAantal());
			}
			
			mandje.setAdres(mandjeForm.getAdres());
			
			return new ModelAndView("redirect:/mandje/overzicht");
		}
		
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		Bestelbon productenInMandje = new Bestelbon();
		
		for(Long productId:mandje.getProducten().keySet()) {
			Product product = productService.findOne(productId);
			productenInMandje.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(productId), product.getPrijs()));
		}
		
		modelAndView.addObject("productenInMandje", productenInMandje);
		
		modelAndView.addObject("verwijderUitMandjeForm", new VerwijderUitMandjeForm());
		
		if(request.getUserPrincipal() != null) {
			Gebruiker gebruiker = gebruikerService.findByEmailadres(request.getUserPrincipal().getName());
			
			modelAndView.addObject("adresGebruiker", gebruiker.getAdres());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "{id}/verwijder", method = RequestMethod.GET)
	public ModelAndView deleteBestellijn(@PathVariable long id) {
		mandje.removeProduct(id);
		
		return new ModelAndView("redirect:/mandje");
	}
	
	@RequestMapping(value = "overzicht", method = RequestMethod.GET)
	ModelAndView showOverzicht() {
		ModelAndView modelAndView = new ModelAndView("mandje/overzicht");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		Bestelbon productenInMandje = new Bestelbon();
		
		for(long id:mandje.getProducten().keySet()) {
			Product product = productService.findOne(id);
			productenInMandje.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(id), product.getPrijs()));
		}
		productenInMandje.setLeverAdres(mandje.getAdres());
		
		modelAndView.addObject("productenInMandje", productenInMandje);
		
		return modelAndView;
	}
}
