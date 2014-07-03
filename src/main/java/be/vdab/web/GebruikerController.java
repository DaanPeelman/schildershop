package be.vdab.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Gebruiker;
import be.vdab.exceptions.GebruikerMetDezeEmailBestaatAlException;
import be.vdab.services.GebruikerService;

@Controller
@RequestMapping("/gebruiker")
public class GebruikerController {
	private final GebruikerService gebruikerService;
	
	@Autowired
	public GebruikerController(GebruikerService gebruikerService) {
		this.gebruikerService = gebruikerService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView aanmeldForm() {
		ModelAndView modelAndView = new ModelAndView("gebruiker/login");
		modelAndView.addObject("gebruiker", new Gebruiker());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid Gebruiker gebruiker, BindingResult bindingResult) {
		if(!bindingResult.hasErrors() && !gebruiker.isValid()) {
			bindingResult.rejectValue("bevestigWachtwoord", "wachtwoordenNietGelijk");
		}
		
		if(!bindingResult.hasErrors()) {
			try {
				gebruikerService.add(gebruiker);
				return new ModelAndView("redirect:/");
			} catch (GebruikerMetDezeEmailBestaatAlException e) {
				bindingResult.rejectValue("emailadres", "gebruikerMetDezeEmailBestaatAl");
			}
		}
		
		return new ModelAndView("gebruiker/login", "gebruiker", gebruiker);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toonGegevens(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("gebruiker/gegevens");
		
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
			modelAndView.addObject("gebruiker", gebruiker);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/wijzig", method = RequestMethod.GET)
	public ModelAndView wijzigGegevensForm(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("gebruiker/wijzig");
		
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
			modelAndView.addObject("gebruiker", gebruiker);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView wijzigGegevens(HttpServletRequest request, @Valid Gebruiker gebruiker, BindingResult bindingResult) {		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			if(!bindingResult.hasErrors()) {
				gebruikerService.update(gebruiker);
				return new ModelAndView("gebruiker/gegevens");
			}
		}
		
		return new ModelAndView("gebruiker/wijzig", "gebruiker", gebruiker);
	}
	
	@InitBinder("gebruiker")
	public void initBinderGebruiker(DataBinder dataBinder) {
		Gebruiker gebruiker = (Gebruiker) dataBinder.getTarget();
		
		if(gebruiker.getAdres() == null) {
			gebruiker.setAdres(new AdresForm());
		} else {
			gebruiker.setAdres(new AdresForm(gebruiker.getAdres()));
		}
	}
}
