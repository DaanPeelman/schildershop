package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccesDeniedController {
	private Mandje mandje;
	
	@Autowired
	public AccesDeniedController(Mandje mandje) {
		this.mandje = mandje;
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	ModelAndView accesssDenied() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		modelAndView.setViewName("forbidden");
		return modelAndView;
	}
}
