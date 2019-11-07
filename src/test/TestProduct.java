package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Product;
import model.ProductGroup;

public class TestProduct {

	private ProductGroup productgroup1;
	private ProductGroup productgroup2;
	private Product product1;
	private Product product2;


	public TestProduct() {

	}

	@Test
	public void testRemoveProductTC1() {
		productgroup1 = new ProductGroup("test 1", 0);
		productgroup2 = new ProductGroup("test 2", 0);
		Product superProduct = new Product("Flaske øl", productgroup1);
		productgroup2.addProduct(superProduct);
		productgroup1.removeProduct(superProduct);
		assertEquals(0.0, productgroup1.getTax(), 0.01);
		
	}

	@Test
	public void testRemoveProductTC2() {
		ProductGroup pg3 = new ProductGroup("flaske øl", 0);
		Product p = new Product("Bajer", pg3);
		try {
			p.setProductGroup(pg3);
			pg3.removeProduct(p);
			
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Produktet skal flyttes til en ny produktgruppe før det kan fjernes fra flaske øl");
		}
		
	}

}
