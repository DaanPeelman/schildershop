package be.vdab.web;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.*;
import be.vdab.services.*;

@Controller
@RequestMapping(value = "/bestellingen")
public class BestelbonController {
	private final BestelbonService bestelbonService;
	private final ProductService productService;
	private final GebruikerService gebruikerService;
	
	private Mandje mandje;
	
	@Autowired
	public BestelbonController(BestelbonService bestelbonService, ProductService productService, GebruikerService gebruikerService, Mandje mandje) {
		this.bestelbonService = bestelbonService;
		this.productService = productService;
		this.gebruikerService = gebruikerService;
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView readAll(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		Iterable<Bestelbon> bestellingen = gebruikerService.findByEmailadres(principal.getName()).getBestellingen();
		
		ModelAndView modelAndView = new ModelAndView("bestellingen/bestellingen");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("bestellingen", bestellingen);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
		
		
		Bestelbon bestelbon = new Bestelbon(gebruiker, mandje.getAdres());
		
		for(Long productId: mandje.getProducten().keySet()) {
			Product product = productService.findOne(productId);
			bestelbon.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(productId), product.getPrijs()));
		}
		
		bestelbonService.create(bestelbon);
			
		mandje.leegMandje();
		ModelAndView modelAndView = new ModelAndView("bestellingen/succes");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "{bestelbonId}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable long bestelbonId, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
		
		Bestelbon bestelbon = bestelbonService.read(bestelbonId);
		
		if(gebruiker == bestelbon.getGebruiker()) {
			ModelAndView modelAndView = new ModelAndView("bestellingen/bestelling");
			modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
			modelAndView.addObject("bestelbon", bestelbon);
			
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("forbidden");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		return modelAndView;
	}
}
