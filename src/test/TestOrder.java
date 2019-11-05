package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.Month;


import org.junit.Test;

import model.Order;
import model.OrderStatusType;
import model.PriceList;
import model.Product;
import model.ProductGroup;

public class TestOrder {

	private Order testOrder1;
	private Order testOrder2;
	private Order testOrder3;
	
	private int amount;
	private Product product;
	private PriceList priceList;
	private ProductGroup productgroup;
	
	public TestOrder() {
		// Test Order Basic Data 8.1
		int id = 1;
		LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.JANUARY, 1, 12, 00);
		// TestCase 1:
		testOrder1 = new Order(id, startTimeStamp, OrderStatusType.CREATED);
		// TestCase 2:
		testOrder2 = new Order(id, startTimeStamp, OrderStatusType.PROGRESS);
		// TestCase 3:
		testOrder3 = new Order(id, startTimeStamp, OrderStatusType.DONE);
		
		
		// TestCase CreateOrderLine Basic Data 8.1.2
		productgroup = new ProductGroup("spiritus", 0);
		product = new Product("Spirit of Aarhus", productgroup);
		amount = 3;
	}

	/**
	 * TestCases 8.1 Order: Basic Data 
	 * id = 1
	 * startTimeStamp = ”2019-01-01”
	 * endTimeStamp = "2019-01-01"	 
	 * */
	@Test
	public void testOrderConstructor() {
		// TestCase 1:
		assertEquals(1.0 , testOrder1.getID(), 0.001);
		// State : OK
		
		// TestCase 2:
		assertEquals(1.0, testOrder2.getID(), 0.001);
		// State : OK
		
		// TestCase 3:
		assertEquals(1.0, testOrder3.getID(), 0.001);
		// State : OK
	}

	@Test
	public void testCreateOrderLineSuccesTC1() {
		testOrder1.createOrderLine(product, priceList, amount);
		assertEqual(1.0, (double) testOrder1.getID(), 0.001);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateOrderLineFailTC2() {
		try {
			
			testOrder2.createOrderLine(null, new PriceList("Test"), 3);
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product and priceList must not be null");
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateOrderLineFailTC3() {
		try {
			
			testOrder2.createOrderLine(product, null, 3);
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product and priceList must not be null");
		}
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateOrderLineFailTC4() {
		try {
			testOrder2.createOrderLine(product, new PriceList("Test"), -1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "amount must be positive");
		}
	}
}
