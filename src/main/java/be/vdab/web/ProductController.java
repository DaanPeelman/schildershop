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
	
	private Mandje mandje;

	@Autowired
	public ProductController(ProductService productService,
			SchilderService schilderService, Mandje mandje, ServletContext servletContext) {
		this.productService = productService;
		this.schilderService = schilderService;
		this.mandje = mandje;
		this.servletContext = servletContext;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("producten/producten",
				"schilderijen", productService.findAll());
		mav.addObject("aantalInMandje", mandje.getProducten().size());
		mav.addObject("zoekTermForm", new ZoekTermForm());
		mav = addMinsMaxs(mav);
		
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "zoekterm",
			"vanPrijs", "totPrijs", "vanJaartal", "totJaartal" })
	public ModelAndView findByZoektermen(@Valid ZoekTermForm zoekTermForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("producten/producten");
		mav.addObject("aantalInMandje", mandje.getProducten().size());
		
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
			String filter = "tussen €" + vanPrijs + " en €"
					+ totPrijs + ", gemaakt tussen " + vanJaartal + " en "
							+ totJaartal;
			if (!zoekterm.equals("")) {
				filter += ", met " + zoekterm;
			}
			mav.addObject("filter", filter);
		}

		mav = addMinsMaxs(mav);
		mav.addObject("sMinPrijs", zoekTermForm.getVanPrijs());
		mav.addObject("sMaxPrijs", zoekTermForm.getTotPrijs());
		mav.addObject("sMinJaar", zoekTermForm.getVanJaartal());
		mav.addObject("sMaxJaar", zoekTermForm.getTotJaartal());
		
		return mav;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	public ModelAndView createForm() {
		ModelAndView mav = new ModelAndView("producten/toevoegen", "product",
				new Product());
		mav.addObject("aantalInMandje", mandje.getProducten().size());
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
			
			if(part.getSize() > 1000000) {
				bindingResult.reject("teGroteFoto");
			}
		}
		ModelAndView mav = new ModelAndView("producten/toevoegen", "schilder",
				new Schilder());
		mav.addObject("aantalInMandje", mandje.getProducten().size());
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
		mav.addObject("aantalInMandje", mandje.getProducten().size());
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
		mav.addObject("aantalInMandje", mandje.getProducten().size());
		if (product != null) {
			String productFotoPad = servletContext.getRealPath(File.separator + "img") + File.separator
					+ product.getProductId() + ".jpg";
			System.out.println(productFotoPad);
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
	
	private ModelAndView addMinsMaxs(ModelAndView mav) {
		mav.addObject("maxPrijs", productService.findMaxPrijs());
		mav.addObject("minDatum", productService.findMinJaartal());
		mav.addObject("maxDatum", productService.findMaxJaartal());
		return mav;
	}
}
