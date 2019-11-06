package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Product;
import model.ProductGroup;

public class TestProduct {

	private ProductGroup productgroup1;
	private ProductGroup productgroup2;
	private Product product1;
	private Product product2;
	
	
	public TestProduct() {
		this.productgroup1 = new ProductGroup("flaske øl", 0);
		this.productgroup2 = new ProductGroup("fustage", 0);
		this.product1 = new Product("flaske øl", productgroup1);
		this.product2 = new Product("fustage", productgroup2);
	}
	
	@Test
	public void testSetProductGroupTC1() {
		product2.setProductGroup(productgroup1);
		assertEquals(productgroup1.getType(), product2.getProductGroup().getType());
	}

	@Test
	public void testSetProductGroupTC2() {
		
	}
	
	@Test
	public void testSetProductGroupTC3() {
		
	}
}
