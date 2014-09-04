package be.vdab.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Gebruiker;
import be.vdab.exceptions.GebruikerMetDezeEmailBestaatAlException;
import be.vdab.services.GebruikerService;

@Controller
@RequestMapping("/gebruiker")
public class GebruikerController {
	private final GebruikerService gebruikerService;
	
	private Mandje mandje;
	
	@Autowired
	public GebruikerController(GebruikerService gebruikerService, Mandje mandje) {
		this.gebruikerService = gebruikerService;
		this.mandje = mandje;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView aanmeldForm() {
		ModelAndView modelAndView = new ModelAndView("gebruiker/login");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("gebruiker", new Gebruiker());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid Gebruiker gebruiker, BindingResult bindingResult) {
		System.out.println("in post methode");
		if(!bindingResult.hasErrors() && !gebruiker.isValid()) {
			bindingResult.rejectValue("bevestigWachtwoord", "wachtwoordenNietGelijk");
		}
		
		if(!bindingResult.hasErrors()) {
			try {
				gebruikerService.create(gebruiker);
				
				ModelAndView modelAndView = new ModelAndView("gebruiker/succes");
				modelAndView.addObject("gebruikersnaam", gebruiker.getEmailadres());
				modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
				
				return modelAndView;
			} catch (GebruikerMetDezeEmailBestaatAlException e) {
				bindingResult.rejectValue("emailadres", "gebruikerMetDezeEmailBestaatAl");
			}
		}
		
		ModelAndView modelAndView = new ModelAndView("gebruiker/login");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toonGegevens(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
			
			ModelAndView modelAndView = new ModelAndView("gebruiker/gegevens");
			modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
			modelAndView.addObject("gebruiker", gebruiker);
			
			return modelAndView;
		}

		return new ModelAndView("redirect:/gebruiker/login");
	}
	
	@RequestMapping(value = "/wijzig", method = RequestMethod.GET)
	public ModelAndView wijzigGegevensForm(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("gebruiker/wijzig");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
			modelAndView.addObject("adresForm", new AdresForm(gebruiker.getAdres()));
			modelAndView.addObject("wijzigWachtwoordForm", new WijzigWachtwoordForm());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView wijzigGegevens(HttpServletRequest request, @Valid AdresForm adresForm, BindingResult bindingResult) {		
		Principal principal = request.getUserPrincipal();
		long gebruikerId = (gebruikerService.findByEmailadres(request.getUserPrincipal().getName())).getGebruikerId();
		
		if(principal != null) {
			if(!bindingResult.hasErrors()) {
				gebruikerService.updateGegevens(gebruikerId, adresForm);
				ModelAndView modelAndView = new ModelAndView("gebruiker/gegevensSucces");
				modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
				
				return modelAndView;
			}
		}
		ModelAndView modelAndView = new ModelAndView("gebruiker/wijzig");
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("wijzigWachtwoordForm", new WijzigWachtwoordForm());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/wachtwoord", method = RequestMethod.PUT)
	public ModelAndView wijzigWachtwoord(HttpServletRequest request, @Valid WijzigWachtwoordForm wijzigWachtwoordForm, BindingResult bindingResult) {
		Principal principal = request.getUserPrincipal();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Gebruiker gebruiker = gebruikerService.findByEmailadres(principal.getName());
		
		if(!encoder.matches(wijzigWachtwoordForm.getOudWachtwoord(), gebruiker.getWachtwoord())){
			bindingResult.rejectValue("oudWachtwoord", "oudWachtwoordNietGelijk");
		}
		if(!wijzigWachtwoordForm.isValid()) {
			bindingResult.rejectValue("bevestigWachtwoord", "wachtwoordenNietGelijk");
		}
		if(!bindingResult.hasErrors()) {
			gebruikerService.updateWachtwoord(gebruiker.getGebruikerId(), wijzigWachtwoordForm.getNieuwWachtwoord());
			return new ModelAndView("redirect:/");
		}
		
		ModelAndView modelAndView = new ModelAndView("gebruiker/wijzig");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("adresForm", new AdresForm(gebruiker.getAdres()));
		
		return modelAndView;
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
