package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/klanten")
public class KlantenController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView aanmeldForm() {
		ModelAndView modelAndView = new ModelAndView("klanten/klanten");
		modelAndView.addObject("klant", new NieuweKlantForm());
		
		return modelAndView;
	}
	
	@InitBinder("klant")
	public void initBinderKlant(DataBinder dataBinder) {
		NieuweKlantForm klant = (NieuweKlantForm) dataBinder.getTarget();
		
		if(klant.getAdres() == null) {
			klant.setAdres(new AdresForm());
		} else {
			klant.setAdres(new AdresForm(klant.getAdres()));
		}
	}
}
