package be.vdab.web;

import java.math.BigDecimal;

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
		return new ModelAndView("producten/producten", "schilderijen",
				productService.findAll());
	}

	@RequestMapping(value = "zoeken", method = RequestMethod.GET)
	public ModelAndView zoekForm() {
		return new ModelAndView("producten/zoeken", "zoekForm", new ZoekForm());
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
				String titel = zoekForm.getTitel();
				String schilderNaam = zoekForm.getSchilderNaam();
				String stijl = zoekForm.getStijl();
				BigDecimal vanPrijs = zoekForm.getVanPrijs();
				BigDecimal totPrijs = zoekForm.getTotPrijs();
				int vanJaartal = zoekForm.getVanJaartal();
				int totJaartal = zoekForm.getTotJaartal();
				Iterable<Product> resultaat = productService.zoek(titel,
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
