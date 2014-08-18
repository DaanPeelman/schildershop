package be.vdab.web;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.*;
import be.vdab.exceptions.*;
import be.vdab.services.*;

@Controller
@RequestMapping("/producten")
public class ProductController {
	private final ProductService productService;
	private final SchilderService schilderService;
	private final ServletContext servletContext;
	private final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Autowired
	public ProductController(ProductService productService,
			SchilderService schilderService, ServletContext servletContext) {
		this.productService = productService;
		this.schilderService = schilderService;
		this.servletContext = servletContext;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("producten/producten",
				"schilderijen", productService.findAll());
		mav.addObject("zoekTermForm", new ZoekTermForm());
		mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
		mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "zoekterm" })
	public ModelAndView findByZoekterm(@Valid ZoekTermForm zoekTermForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		if (!bindingResult.hasErrors()) {
			String zoekterm = zoekTermForm.getZoekterm().toLowerCase();
			Iterable<Product> resultaat = productService.findByZoekterm(zoekterm);
			mav.addObject("schilderijen", resultaat);
			mav.addObject("zoekTermForm", new ZoekTermForm());
			mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
			mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
			mav.addObject("filter", "met " + zoekterm);
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
			mav.addObject("zoekTermForm", new ZoekTermForm());
			mav.addObject("stijlen", productService.findAllStijlen());
			mav.addObject("vanTotJaartalForm", new VanTotJaartalForm());
			mav.addObject("filter", "tussen €" + vanPrijs + " en €" + totPrijs);
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
			mav.addObject("zoekTermForm", new ZoekTermForm());
			mav.addObject("stijlen", productService.findAllStijlen());
			mav.addObject("vanTotPrijsForm", new VanTotPrijsForm());
			mav.addObject("filter", "gemaakt tussen " + vanJaartal + " en " + totJaartal);
		}
		return mav;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	public ModelAndView createForm() {
		ModelAndView mav = new ModelAndView("producten/toevoegen", "product",
				new Product());
		mav.addObject("schilders", schilderService.findAll());
		mav.addObject("schilder", new Schilder());
		return mav;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.POST)
	public ModelAndView createProduct(@Valid Product product,
			BindingResult bindingResult, @RequestParam("foto") Part part) {
		if (part != null && part.getSize() != 0) {
			String contentType = part.getContentType();
			if (!"image/jpeg".equals(contentType)
					&& "image/pjpeg".equals(contentType)) {
				bindingResult.reject("fotoFout");
			}
		}
		ModelAndView mav = new ModelAndView("producten/toevoegen", "schilder",
				new Schilder());
		mav.addObject("schilders", schilderService.findAll());
		if (!bindingResult.hasErrors()) {
			try {
				productService.create(product);
				if (part != null && part.getSize() != 0) {
					String productFotosPad = servletContext.getRealPath("/img");
					part.write(productFotosPad + "/" + product.getProductId() + ".jpg");
				}
				mav.addObject("succesProduct", product.getTitel()
						+ " is succesvol toegevoegd.");
				mav.addObject("product", new Product());
				return mav;
			} catch (ProductBestaatAlException ex) {
				bindingResult.rejectValue("titel", "ProductIsNietUniek");
			} catch (IOException e) {
				logger.error("fouten bij opslaan foto" + e.getStackTrace());
			}
		}
		mav.addObject("product", product);
		return mav;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.POST, params = "naam")
	public ModelAndView createSchilder(@Valid Schilder schilder,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/toevoegen", "product",
				new Product());
		if (!bindingResult.hasErrors()) {
			try {
				schilderService.create(schilder);
				mav.addObject("schilder", new Schilder());
				mav.addObject("succesSchilder", schilder.getNaam()
						+ " is succesvol toegevoegd.");
				mav.addObject("schilders", schilderService.findAll());
				return mav;
			} catch (SchilderMetDezeNaamBestaatAlException ex) {
				bindingResult.rejectValue("naam",
						"SchilderMetDezeNaamBestaatAl");
			}
		}
		mav.addObject("schilders", schilderService.findAll());
		return mav;
	}

	@RequestMapping(value = "{productId}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable long productId) {
		Product product = productService.findOne(productId);
		ModelAndView mav =  new ModelAndView("producten/details", "product",
				product);
		if (product != null) {
			String productFotoPad = servletContext.getRealPath("/img") + "\\" + product.getProductId() + ".jpg";
			File file = new File(productFotoPad);
			mav.addObject("heeftFoto", file.exists());
		}
		MandjeForm mandjeForm = new MandjeForm(productId);
		mav.addObject("mandjeForm", mandjeForm);
		
		return mav;
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
