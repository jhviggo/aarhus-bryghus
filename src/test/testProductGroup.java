package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Product;
import model.ProductGroup;

public class testProductGroup {

	private Product product;
	private ProductGroup productgroup1;
	private ProductGroup productgroup2;
	private Product product1;
	private Product product2;

	public testProductGroup() {
		productgroup1 = new ProductGroup("fustage", 0);
		productgroup2 = new ProductGroup("flaske",0);
		product = new Product("flaske", productgroup1);

		productgroup1.addProduct(product);
		product1 = new Product("flaske", productgroup1);
		
		productgroup1.addProduct(product1);
	}

	@Test
	public void testSetProductGroupTC1() {
		productgroup1 = new ProductGroup("flaske øl", 0);
		productgroup2 = new ProductGroup("fustage", 0);
		product1 = new Product("flaske øl", productgroup1);
		product2 = new Product("fustage", productgroup2);
		product2.setProductGroup(productgroup1);

		assertEquals(productgroup1.getType(), product2.getProductGroup().getType());
	}

	@Test
	public void testSetProductGroupTC2() {
		productgroup1 = new ProductGroup("flaske øl", 0);
		productgroup2 = new ProductGroup("fustage", 0);
		product1 = new Product("fustage", productgroup1);
		product1.setProductGroup(productgroup2);
		assertEquals(productgroup2.getType(), product1.getProductGroup().getType());
	}


}
