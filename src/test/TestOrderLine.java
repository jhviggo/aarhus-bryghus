package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.OrderLine;
import model.PriceList;
import model.Product;
import model.ProductGroup;

public class TestOrderLine {
	
	private int amount;
	private Product p1;
	private OrderLine testOrderline1;	
	private ProductGroup productgroup;
	private PriceList pricelist;
	
	
	public TestOrderLine() {
		amount = 4;
		productgroup = new ProductGroup("flaske", 0);
		p1 = new Product("India Pale Ale", productgroup);
		pricelist = new PriceList("Test Orderline pricelist");
		testOrderline1 = new OrderLine(p1, pricelist, amount);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(4, testOrderline1.getAmount(), 0.001);
	}
	
	@Test
	public void testGetPrice() {
		pricelist.setPrice(p1, 50);
		assertEquals(200.0, testOrderline1.getPrice(), 0.001);
	}
}
