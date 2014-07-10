package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.dao.BestelbonDAO;

@Controller
@RequestMapping(value = "/bestellingen")
public class BestelbonController {
	private final BestelbonDAO bestelbonDAO;
	
	@Autowired
	public BestelbonController(BestelbonDAO bestelbonDAO) {
		this.bestelbonDAO = bestelbonDAO;
	}
	
	@RequestMapping(value = "{bestelbonId}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable long bestelbonId) {
		ModelAndView modelAndView = new ModelAndView("bestellingen/bestelling");
		modelAndView.addObject("bestelbon", bestelbonDAO.findOne(bestelbonId));
		
		return modelAndView;
	}
}
