package be.vdab.web;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Bestelbonlijn;
import be.vdab.entities.Product;
import be.vdab.services.ProductService;

@Controller
@RequestMapping(value = "/mandje")
public class MandjeController {
	private final ProductService productService;
	
	@Autowired
	public MandjeController(ProductService productService) {
		this.productService = productService;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showMandje(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		
		Bestelbon mandje = (Bestelbon)request.getSession(true).getAttribute("mandje");
		
		if(mandje == null) {
			mandje = new Bestelbon();
			request.getSession().setAttribute("mandje", mandje);
		}
		
		modelAndView.addObject("mandje", mandje);
		modelAndView.addObject("adresForm", new AdresForm());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView addBestellijn(@Valid ProductAankoopForm productAankoopForm, BindingResult bindingResult, HttpServletRequest request) {
		if(!bindingResult.hasErrors()) {
			System.out.println("IN ADDBESTELLIJN");
			
			Product product = productService.findOne(productAankoopForm.getProductId());
			int aantal = productAankoopForm.getAantal();
			BigDecimal prijs = product.getPrijs();
			
			Bestelbonlijn bestellijn = new Bestelbonlijn(product, aantal, prijs);
			
			Bestelbon mandje = (Bestelbon)request.getSession(true).getAttribute("mandje");
			
			if(mandje == null) {
				mandje = new Bestelbon();
			}
			
			mandje.addBestelbonlijn(bestellijn);
			
			System.out.println("mandje na toevoegen: ");
			for(Bestelbonlijn bestelbonlijn:mandje.getBestelbonlijnen()) {
				System.out.println("is " + bestelbonlijn.getProduct().toString() + " hetzelde als " + product.toString() + "? " + bestelbonlijn.getProduct().equals(product));
			}
			return new ModelAndView("redirect:/mandje");
		}
		String view = String.format("producten/%d", productAankoopForm.getProductId());
		return new ModelAndView(view);
	}
	
	/*
	@RequestMapping(method = RequestMethod.DELETE, params = {"productId"})
	public ModelAndView deleteBestellijn(@Param long productId) {
		System.out.println("in deleteBestellijn");
		
		for(Bestelbonlijn bestelbonlijn:mandje.getBestelbonlijnen()) {
			if(bestelbonlijn.getProduct().getProductId() == productId) {
				mandje.verwijderBestelbonlijn(bestelbonlijn);
			}
		}
		return new ModelAndView("redirect:/mandje");
	}
	
	void removeMandje(){
		mandje = new Bestelbon();
	}
	*/
}
