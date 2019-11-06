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
	public void testConstructorTC1() {
		assertEquals(4, testOrderline1.getAmount(), 0.001);
	}
	
	@Test
	public void testGetPriceTC1() {
		testOrderline1.setAmount(3);
		pricelist.setPrice(p1, 50);
		assertEquals(150.0, testOrderline1.getPrice(), 0.001);
	}
	
	@Test
	public void testGetPriceTC2() {
		testOrderline1.setAmount(1);
		pricelist.setPrice(p1, 50);
		assertEquals(50.0, testOrderline1.getPrice(), 0.001);
	}
}
