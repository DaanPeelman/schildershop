package be.vdab.web;

import java.io.*;
import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.slf4j.*;
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
		mav.addObject("minPrijs", 0);
		mav.addObject("maxPrijs", 2400);
		mav.addObject("minDatum", 1400);
		mav.addObject("maxDatum", 2700);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "zoekterm",
			"vanPrijs", "totPrijs", "vanJaartal", "totJaartal" })
	public ModelAndView findByZoektermen(@Valid ZoekTermForm zoekTermForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		if (!bindingResult.hasErrors() && !zoekTermForm.isValidPrijs()) {
			bindingResult.reject("fouteVanTotPrijs", new Object[] {
					zoekTermForm.getVanPrijs(), zoekTermForm.getTotPrijs() },
					"");
		}
		if (!bindingResult.hasErrors() && !zoekTermForm.isValidJaartal()) {
			bindingResult.reject("fouteVanTotJaartal",
					new Object[] { zoekTermForm.getVanJaartal(),
							zoekTermForm.getTotJaartal() }, "");
		}
		if (!bindingResult.hasErrors()) {
			String zoekterm = zoekTermForm.getZoekterm().toLowerCase();
			BigDecimal vanPrijs = zoekTermForm.getVanPrijs();
			BigDecimal totPrijs = zoekTermForm.getTotPrijs();
			Integer vanJaartal = zoekTermForm.getVanJaartal();
			Integer totJaartal = zoekTermForm.getTotJaartal();
			Iterable<Product> resultaat = productService.findByZoektermen(
					zoekterm, vanPrijs, totPrijs, vanJaartal, totJaartal);
			mav.addObject("schilderijen", resultaat);
			mav.addObject("zoekTermForm", new ZoekTermForm());
			String filter = "tussen €" + vanPrijs + " en €"
					+ totPrijs + ", gemaakt tussen " + vanJaartal + " en "
							+ totJaartal;
			if (!zoekterm.equals("")) {
				filter += ", met " + zoekterm;
			}
			mav.addObject("filter", filter);
		}
		mav.addObject("zoekTermForm", new ZoekTermForm());
		mav.addObject("minPrijs", 0);
		mav.addObject("maxPrijs", 2400);
		mav.addObject("minDatum", 1400);
		mav.addObject("maxDatum", 2600);
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
					part.write(productFotosPad + "/" + product.getProductId()
							+ ".jpg");
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
		ModelAndView mav = new ModelAndView("producten/details", "product",
				product);
		if (product != null) {
			String productFotoPad = servletContext.getRealPath("/img") + "\\"
					+ product.getProductId() + ".jpg";
			File file = new File(productFotoPad);
			mav.addObject("heeftFoto", file.exists());
		}
		BestelProductForm bestelProductForm = new BestelProductForm(productId);
		mav.addObject("bestelProductForm", bestelProductForm);

		return mav;
	}

	@InitBinder("zoekTermForm")
	public void intBinderZoekTermForm(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	@InitBinder("product")
	public void initBinderProduct(DataBinder dataBinder) {
	}
}
