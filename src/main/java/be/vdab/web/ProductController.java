package be.vdab.web;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.*;
import be.vdab.exceptions.SchilderMetDezeNaamBestaatAlException;
import be.vdab.services.*;

@Controller
@RequestMapping("/producten")
public class ProductController {
	private final ProductService productService;
	private final SchilderService schilderService;

	@Autowired
	public ProductController(ProductService productService, 
			SchilderService schilderService) {
		this.productService = productService;
		this.schilderService = schilderService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("producten/producten",
				"schilderijen", productService.findAll());
		mav.addObject("titelForm", new TitelForm());
		mav.addObject("schilderForm", new SchilderForm());
		mav.addObject("stijlForm", new StijlForm());
		mav.addObject("stijlen", productService.findAllStijlen());
		mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
		mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "titel" })
	public ModelAndView findByTitel(@Valid TitelForm titelForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		if (!bindingResult.hasErrors()) {
			String titel = titelForm.getTitel().toLowerCase();
			Iterable<Product> resultaat = productService.findByTitel(titel);
			mav.addObject("schilderijen", resultaat);
			mav.addObject("schilderForm", new SchilderForm());
			mav.addObject("stijlForm", new StijlForm());
			mav.addObject("stijlen", productService.findAllStijlen());
			mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
			mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "schilderNaam" })
	public ModelAndView zoek(@Valid SchilderForm schilderForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		if (!bindingResult.hasErrors()) {
			String schilderNaam = schilderForm.getSchilderNaam().toLowerCase();
			Iterable<Product> resultaat = productService
					.findBySchilderNaam(schilderNaam);
			mav.addObject("schilderijen", resultaat);
			mav.addObject("titelForm", new TitelForm());
			mav.addObject("stijlForm", new StijlForm());
			mav.addObject("stijlen", productService.findAllStijlen());
			mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
			mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "stijl" })
	public ModelAndView findBystijl(@Valid StijlForm stijlForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		if (!bindingResult.hasErrors()) {
			String stijl = stijlForm.getStijl().toLowerCase();
			Iterable<Product> resultaat = productService.findByStijl(stijl);
			mav.addObject("schilderijen", resultaat);
			mav.addObject("titelForm", new TitelForm());
			mav.addObject("schilderForm", new SchilderForm());
			mav.addObject("stijlen", productService.findAllStijlen());
			mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
			mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "vanPrijs",
			"totPrijs" })
	public ModelAndView findByPrijsBetween(
			@Valid VanTotPrijsForm vanTotPrijsForm, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		if (!bindingResult.hasErrors() && !vanTotPrijsForm.isValid()) {
			bindingResult.reject("fouteVanTotPrijs",
					new Object[] { vanTotPrijsForm.getVanPrijs(),
							vanTotPrijsForm.getTotPrijs() }, "");
		}
		if (!bindingResult.hasErrors()) {
			BigDecimal vanPrijs = vanTotPrijsForm.getVanPrijs();
			BigDecimal totPrijs = vanTotPrijsForm.getTotPrijs();
			Iterable<Product> resultaat = productService.findByPrijsBetween(
					vanPrijs, totPrijs);
			mav.addObject("schilderijen", resultaat);
			mav.addObject("titelForm", new TitelForm());
			mav.addObject("schilderForm", new SchilderForm());
			mav.addObject("stijlForm", new StijlForm());
			mav.addObject("stijlen", productService.findAllStijlen());
			mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "vanJaartal",
			"totJaartal" })
	public ModelAndView findByJaartalBetween(
			@Valid VanTotJaartalForm vanTotJaartalForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		if (!bindingResult.hasErrors() && !vanTotJaartalForm.isValid()) {
			bindingResult.reject("fouteVanTotJaartal",
					new Object[] { vanTotJaartalForm.getVanJaartal(),
							vanTotJaartalForm.getTotJaartal() }, "");
		}
		if (!bindingResult.hasErrors()) {
			Integer vanJaartal = vanTotJaartalForm.getVanJaartal();
			Integer totJaartal = vanTotJaartalForm.getTotJaartal();
			Iterable<Product> resultaat = productService.findByJaartalBetween(
					vanJaartal, totJaartal);
			mav.addObject("schilderijen", resultaat);
			mav.addObject("titelForm", new TitelForm());
			mav.addObject("schilderForm", new SchilderForm());
			mav.addObject("stijlForm", new StijlForm());
			mav.addObject("stijlen", productService.findAllStijlen());
			mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
		}
		return mav;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	public ModelAndView createForm() {
		ModelAndView mav = new ModelAndView("producten/toevoegen", "product", new Product());
		mav.addObject("schilders", schilderService.findAll());
		mav.addObject("schilder", new Schilder());
		return mav;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.POST)
	public ModelAndView createProduct(@Valid Product product, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/toevoegen", "schilder", new Schilder());
		mav.addObject("schilders", schilderService.findAll());
		if (!bindingResult.hasErrors()) {
			productService.create(product);
			mav.addObject("succesProduct", product.getTitel() + " is succesvol toegevoegd.");
			mav.addObject("product", new Product());
		}
		mav.addObject("product", product);
		return mav;
	}
	
	@RequestMapping(value = "toevoegen", method = RequestMethod.POST, params = { "naam" })
	public ModelAndView createSchilder(@Valid Schilder schilder, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/toevoegen", "product", new Product());
		mav.addObject("schilders", schilderService.findAll());
		if (!bindingResult.hasErrors()) {
			try {
				schilderService.create(schilder);
				mav.addObject("schilder", new Schilder());
				mav.addObject("succesSchilder", schilder.getNaam() + " is succesvol toegevoegd.");
				return mav;
			} catch (SchilderMetDezeNaamBestaatAlException ex) {
				bindingResult.rejectValue("naam", "SchilderMetDezeNaamBestaatAl");
			}
		}
		mav.addObject("schilder", schilder);
		return mav;
	}


	@InitBinder("titelForm")
	public void intBinderTitelForm(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	@InitBinder("schilderForm")
	public void intBinderSchilderForm(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	@InitBinder("stijlForm")
	public void intBinderStijlForm(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	@InitBinder("vanTotJaartalForm")
	public void intBinderVanTotJaartalForm(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	@InitBinder("vanTotPrijsForm")
	public void intBinderVantotPrijsForm(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	@InitBinder("product")
	public void initBinderProduct(DataBinder dataBinder) {
	}
}
