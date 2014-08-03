package be.vdab.web;

import java.math.BigDecimal;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.dao.BestelbonDAO;
import be.vdab.dao.GebruikerDAO;
import be.vdab.dao.ProductDAO;
import be.vdab.entities.Bestelbon;
import be.vdab.entities.Bestelbonlijn;
import be.vdab.entities.Gebruiker;
import be.vdab.entities.Product;
import be.vdab.services.BestelbonService;

@Controller
@RequestMapping(value = "/bestellingen")
@SessionAttributes("mandje")
public class BestelbonController {
	private final BestelbonService bestelbonService;
	private final ProductDAO productDAO;
	private final GebruikerDAO gebruikerDAO;
	private Bestelbon mandje = new Bestelbon();
	
	@Autowired
	public BestelbonController(BestelbonService bestelbonService, ProductDAO productDAO, GebruikerDAO gebruikerDAO) {
		this.bestelbonService = bestelbonService;
		this.productDAO = productDAO;
		this.gebruikerDAO = gebruikerDAO;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView addBestellijn(@Valid MandjeForm mandjeForm, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			System.out.println("IN ADDBESTELLIJN");
			
			Product product = productDAO.findOne(mandjeForm.getProductId());
			int aantal = mandjeForm.getAantal();
			BigDecimal prijs = product.getPrijs();
			
			mandje.addBestelbonlijn(new Bestelbonlijn(product, aantal, prijs));
			
			System.out.println("mandje na toevoegen: ");
			for(Bestelbonlijn bestelbonlijn:mandje.getBestelbonlijnen()) {
				System.out.println(bestelbonlijn.getProduct().getTitel());
			}
			return new ModelAndView("redirect:/bestellingen/mandje");
		}
		String view = String.format("producten/%d", mandjeForm.getProductId());
		return new ModelAndView(view);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid AdresForm adresForm, BindingResult bindingResult, HttpServletRequest request) {
		if(!bindingResult.hasErrors()) {
			Principal principal = request.getUserPrincipal();
			Gebruiker gebruiker = gebruikerDAO.findByEmailadres(principal.getName());
			
			mandje.setLeverAdres(adresForm);
			mandje.setGebruiker(gebruiker);
			
			bestelbonService.create(mandje);
			
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("bestellingen/mandje");
	}
	
	@RequestMapping(value = "{bestelbonId}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable long bestelbonId) {
		ModelAndView modelAndView = new ModelAndView("bestellingen/bestelling");
		modelAndView.addObject("bestelbon", bestelbonService.read(bestelbonId));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/mandje", method = RequestMethod.GET)
	public ModelAndView showMandje() {
		ModelAndView modelAndView = new ModelAndView("bestellingen/mandje");
		
		modelAndView.addObject("mandje", mandje);
		modelAndView.addObject("adresForm", new AdresForm());
		
		return modelAndView;
	}
}
