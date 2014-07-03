package be.vdab.web;

import java.util.Collections;

import org.junit.*;
import org.mockito.Mockito;

import be.vdab.entities.Product;
import be.vdab.services.*;

public class ProductControllerTest {
	private ProductController productController;
	private ProductService productService;
	private SchilderService schilderService;
	private Iterable<Product> schilderijen;
	
	@Before
	public void setUp() {
		schilderijen = Collections.emptyList();
		productService = Mockito.mock(ProductService.class);
		schilderService = Mockito.mock(SchilderService.class);
		Mockito.when(productService.findAll()).thenReturn(schilderijen);
		productController = new ProductController(productService, schilderService);
	}
	
	@Test
	public void findAllActiveertJuisteView() {
		Assert.assertEquals("producten/producten", 
				productController.findAll().getViewName());
	}
	
	@Test
	public void findAllMaakRequestAttribuutSchilderijen() {
		Assert.assertSame(schilderijen, 
				productController.findAll().getModelMap().get("schilderijen"));
	}
}
