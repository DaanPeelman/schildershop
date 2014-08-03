package be.vdab.web;

import java.math.BigDecimal;

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
import be.vdab.dao.ProductDAO;
import be.vdab.entities.Bestelbon;
import be.vdab.entities.Bestelbonlijn;
import be.vdab.entities.Product;

@Controller
@RequestMapping(value = "/bestellingen")
@SessionAttributes("mandje")
public class BestelbonController {
	private final BestelbonDAO bestelbonDAO;
	private final ProductDAO productDAO;
	private Bestelbon mandje = new Bestelbon();
	
	@Autowired
	public BestelbonController(BestelbonDAO bestelbonDAO, ProductDAO productDAO) {
		this.bestelbonDAO = bestelbonDAO;
		this.productDAO = productDAO;		
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
		
		return new ModelAndView("producten/details");
	}
	
	@RequestMapping(value = "{bestelbonId}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable long bestelbonId) {
		ModelAndView modelAndView = new ModelAndView("bestellingen/bestelling");
		modelAndView.addObject("bestelbon", bestelbonDAO.findOne(bestelbonId));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/mandje")
	public ModelAndView showMandje() {
		System.out.println("mandje: ");
		for(Bestelbonlijn bestelbonlijn:mandje.getBestelbonlijnen()) {
			System.out.println(bestelbonlijn.getProduct().getTitel());
		}
		
		return new ModelAndView("/bestellingen/mandje", "mandje", mandje);
	}
}
