package be.vdab.web;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import be.vdab.entities.Bestelbonlijn;
import be.vdab.entities.Gebruiker;
import be.vdab.entities.Product;
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
		@SuppressWarnings("unchecked")
		Map<Long, Integer> mandje = (ConcurrentHashMap<Long, Integer>)request.getSession().getAttribute("mandje");
		
		Principal principal = request.getUserPrincipal();
		Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
		
		
		Bestelbon bestelbon = new Bestelbon(gebruiker, adresForm);
		
		for(Long productId: mandje.keySet()) {
			Product product = productService.findOne(productId);
			bestelbon.addBestelbonlijn(new Bestelbonlijn(product, mandje.get(productId).intValue(), product.getPrijs()));
		}
		
		if(!bindingResult.hasErrors()) {
			bestelbonService.create(bestelbon);
			
			request.getSession().removeAttribute("mandje");
			
			return new ModelAndView("bestellingen/succes");
		}
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		modelAndView.addObject("mandje", bestelbon);
		modelAndView.addObject("verwijderUitMandjeForm", new VerwijderUitMandjeForm());
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
