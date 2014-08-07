package be.vdab.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Gebruiker;
import be.vdab.services.BestelbonService;
import be.vdab.services.GebruikerService;
import be.vdab.services.ProductService;

@Controller
@RequestMapping(value = "/bestellingen")
public class BestelbonController {
	private final BestelbonService bestelbonService;
	private final ProductService productService;
	private final GebruikerService gebruikerService;
	
	@Autowired
	public BestelbonController(BestelbonService bestelbonService, ProductService productService, GebruikerService gebruikerService, MandjeController mandjeController) {
		this.bestelbonService = bestelbonService;
		this.productService = productService;
		this.gebruikerService = gebruikerService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView readAll(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		Iterable<Bestelbon> bestellingen = gebruikerService.findByEmailadres(principal.getName()).getBestellingen();
		
		return new ModelAndView("bestellingen/bestellingen", "bestellingen", bestellingen);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid AdresForm adresForm, BindingResult bindingResult, HttpServletRequest request) {
		Bestelbon mandje = (Bestelbon)request.getSession().getAttribute("mandje");
		if(!bindingResult.hasErrors()) {
			Principal principal = request.getUserPrincipal();
			Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
			
			
			mandje.setLeverAdres(adresForm);
			mandje.setGebruiker(gebruiker);
			
			bestelbonService.create(mandje);
			
			request.getSession().removeAttribute("mandje");
			
			return new ModelAndView("bestellingen/succes");
		}
		ModelAndView modelAndView = new ModelAndView("/mandje");
		modelAndView.addObject("mandje", mandje);
		return modelAndView;
	}
	
	@RequestMapping(value = "{bestelbonId}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable long bestelbonId, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
		
		Bestelbon bestelbon = bestelbonService.read(bestelbonId);
		
		if(gebruiker == bestelbon.getGebruiker()) {
			return new ModelAndView("bestellingen/bestelling", "bestelbon", bestelbon);
		}
		
		return new ModelAndView("forbidden");
	}
}
