package be.vdab.web;

import java.util.Collections;

import javax.servlet.ServletContext;

import org.junit.*;
import org.mockito.Mockito;

import be.vdab.entities.Product;
import be.vdab.services.*;

public class ProductControllerTest {
	private ProductController productController;
	private ProductService productService;
	private SchilderService schilderService;
	private Iterable<Product> schilderijen;
	private ServletContext context;
	private Mandje mandje;
	
	@Before
	public void setUp() {
		schilderijen = Collections.emptyList();
		productService = Mockito.mock(ProductService.class);
		schilderService = Mockito.mock(SchilderService.class);
		context = Mockito.mock(ServletContext.class);
		mandje = Mockito.mock(Mandje.class);
		Mockito.when(productService.findAll(0)).thenReturn(schilderijen);
		productController = new ProductController(productService, schilderService, mandje, context);
	}
	
	@Test
	public void findAllActiveertJuisteView() {
		Assert.assertEquals("producten/producten", 
				productController.findAll().getViewName());
	}
	
//	@Test
//	public void findAllMaakRequestAttribuutSchilderijen() {
//		Assert.assertSame(schilderijen, 
//				productController.findAll().getModelMap().get("schilderijen"));
//	}
}
