package be.vdab.web;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		Map<Long, Integer> mandje = (ConcurrentHashMap<Long, Integer>)request.getSession(true).getAttribute("mandje");
		
		if(mandje == null) {
			mandje = new ConcurrentHashMap<>();
			request.getSession().setAttribute("mandje", mandje);
		}
		
		Bestelbon productenInMandje = new Bestelbon();
		
		for(Long productId:mandje.keySet()) {
			Product product = productService.findOne(productId);
			productenInMandje.addBestelbonlijn(new Bestelbonlijn(product, mandje.get(productId).intValue(), product.getPrijs()));
		}
		
		modelAndView.addObject("mandje", productenInMandje);
		modelAndView.addObject("adresForm", new AdresForm());
		modelAndView.addObject("verwijderUitMandjeForm", new VerwijderUitMandjeForm());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ModelAndView addBestellijn(@Valid ProductAankoopForm productAankoopForm, BindingResult bindingResult, HttpServletRequest request) {
		if(!bindingResult.hasErrors()) {
			System.out.println("IN ADDBESTELLIJN");
			
			Map<Long, Integer> mandje = (ConcurrentHashMap<Long, Integer>)request.getSession(true).getAttribute("mandje");
			
			System.out.println("productId = " + productAankoopForm.getProductId());
			Long productId = productAankoopForm.getProductId();
			int aantal = productAankoopForm.getAantal();
			
			if(mandje == null) {
				mandje = new ConcurrentHashMap<>();
			}
			
			mandje.put(productId, aantal);

			request.getSession(true).setAttribute("mandje", mandje);
			return new ModelAndView("redirect:/mandje");
		}
		return new ModelAndView("producten/details");
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView deleteBestellijn(@ModelAttribute VerwijderUitMandjeForm verwijderUitMandjeForm, HttpServletRequest request) {
		Map<Long, Integer> mandje = (ConcurrentHashMap<Long, Integer>)request.getSession().getAttribute("mandje");
		
		mandje.remove(verwijderUitMandjeForm.getProductId());
		
		request.getSession().setAttribute("mandje", mandje);
		
		return new ModelAndView("redirect:/mandje");
	}
}
