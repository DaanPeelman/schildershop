package be.vdab.web;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Product;
import be.vdab.services.ProductService;

@Controller
@RequestMapping("/producten")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("producten/producten", "schilderijen",
				productService.findAll());
		mav.addObject("zoekForm", new ZoekForm());
		mav.addObject("stijlen", productService.findAllStijlen());
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "titel",
			"schilderNaam", "stijl", "vanPrijs", "totPrijs", "vanJaartal",
			"totJaartal" })
	public ModelAndView zoek(@Valid ZoekForm zoekForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/zoeken");
		if (!bindingResult.hasErrors()) {
			if (!zoekForm.isValidJaartal()) {
				bindingResult.reject("fouteVanTotJaartal", new Object[] {
						zoekForm.getVanJaartal(), zoekForm.getTotJaartal() },
						"");
			} else if (!zoekForm.isValidPrijs()) {
				bindingResult.reject("fouteVanTotPrijs", new Object[] {
						zoekForm.getVanPrijs(), zoekForm.getTotPrijs() }, "");
			} else {
				String titel = zoekForm.getTitel().toLowerCase();
				String schilderNaam = zoekForm.getSchilderNaam().toLowerCase();
				String stijl = zoekForm.getStijl().toLowerCase();
				BigDecimal vanPrijs = zoekForm.getVanPrijs();
				BigDecimal totPrijs = zoekForm.getTotPrijs();
				Integer vanJaartal = zoekForm.getVanJaartal();
				Integer totJaartal = zoekForm.getTotJaartal();
				Iterator<Product> resultaat = productService.zoek(titel,
						schilderNaam, stijl, vanPrijs, totPrijs, vanJaartal,
						totJaartal);
				mav.addObject("resultaat", resultaat);
			}
		}
		return mav;
	}

	@InitBinder("zoekForm")
	public void intBinderZoekForm(DataBinder dataBinder) {

	}
}
