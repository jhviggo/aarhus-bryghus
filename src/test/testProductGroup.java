package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Product;
import model.ProductGroup;

public class testProductGroup {
	
	private Product product;
	private ProductGroup productgroup1;
	private ProductGroup productgroup2;

	public testProductGroup() {
		productgroup1 = new ProductGroup("fustage", 0);
		productgroup2 = new ProductGroup("flaske",0);
		product = new Product("flaske", productgroup1);
		
		productgroup1.addProduct(product);
	}
	
	@Test
	public void testRemoveProductTC1() {
		Product superProduct = new Product("Flaske øl", productgroup1);
		productgroup2.addProduct(superProduct);
		productgroup1.removeProduct(superProduct);
		assertEquals(0.0, productgroup1.getTax(), 0.01);
	}
	
	@Test(expected = RuntimeException.class)
	public void testRemoveProductTC2() {
		
	}

}
