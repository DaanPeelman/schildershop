package be.vdab.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView aanmeldForm() {
		ModelAndView modelAndView = new ModelAndView("klanten/klanten");
		modelAndView.addObject("gebruiker", new Gebruiker());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid Gebruiker gebruiker, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			klantService.voegToe(gebruiker);
			return new ModelAndView("redirect:/");
		}
		
		List<ObjectError> errors = bindingResult.getAllErrors();
		
		for(ObjectError error:errors) {
			System.out.println(error.toString());
		}
		
		return new ModelAndView("klanten/klanten", "gebruiker", gebruiker);
	}
	
	@InitBinder("gebruiker")
	public void initBinderKlant(DataBinder dataBinder) {
		Gebruiker gebruiker = (Gebruiker) dataBinder.getTarget();
		
		if(gebruiker.getAdres() == null) {
			System.out.println("KLANT ADRES OP ADRESFORM GEZET!!");
			gebruiker.setAdres(new AdresForm());
		} else {
			System.out.println("KLANT ADRES OP AL INGESTELD ADRESFORM GEZET!!");
			gebruiker.setAdres(new AdresForm(gebruiker.getAdres()));
		}
	}
}
