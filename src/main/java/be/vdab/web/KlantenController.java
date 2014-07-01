package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Gebruiker;
import be.vdab.services.KlantService;

@Controller
@RequestMapping("/klanten")
public class KlantenController {
	private final KlantService klantService;
	
	@Autowired
	public KlantenController(KlantService klantService) {
		this.klantService = klantService;
	}
	
	@RequestMapping(value = "/registreer", method = RequestMethod.GET)
	public ModelAndView aanmeldForm() {
		ModelAndView modelAndView = new ModelAndView("klanten/registreer");
		modelAndView.addObject("gebruiker", new Gebruiker());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid Gebruiker gebruiker, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			klantService.voegToe(gebruiker);
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("klanten/registreer", "gebruiker", gebruiker);
	}
	
	@InitBinder("gebruiker")
	public void initBinderKlant(DataBinder dataBinder) {
		Gebruiker gebruiker = (Gebruiker) dataBinder.getTarget();
		
		if(gebruiker.getAdres() == null) {
			gebruiker.setAdres(new AdresForm());
		} else {
			gebruiker.setAdres(new AdresForm(gebruiker.getAdres()));
		}
	}
}
