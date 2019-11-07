package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.Month;


import org.junit.Test;

import model.Order;
import model.OrderLine;
import model.OrderStatusType;
import model.PriceList;
import model.Product;
import model.ProductGroup;

public class TestOrder {

	private Order testOrder1;
	private Order testOrder2;
	private Order testOrder3;
	
	private OrderLine testOrderline1;
	private OrderLine testOrderline2;
	private OrderLine testOrderline3;
	private OrderLine testOrderline4;
	
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
		priceList = new PriceList("test price list");
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
		testOrderline1 = testOrder1.createOrderLine(product, priceList, amount);
		assertEquals(1.0, testOrder1.getID(), 0.001);
	}
	
	
	@Test
	public void testCreateOrderLineFailTC2() {
		try {
			
			testOrderline2 = testOrder1.createOrderLine(null, new PriceList("Test"), 3);
			fail();
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product and priceList must not be null");
		}
	}
	
	@Test
	public void testCreateOrderLineFailTC3() {
		try {
			
			testOrderline3 = testOrder1.createOrderLine(product, null, 3);
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product and priceList must not be null");
		}
	}
	
	
	@Test
	public void testCreateOrderLineFailTC4() {
		try {
			testOrderline4 = testOrder1.createOrderLine(product, new PriceList("Test"), -1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "amount must be positive");
		}
	}
	
	@Test
	public void testDeleteOrderline() {
		testOrderline1 = testOrder1.createOrderLine(product, priceList, amount);
		
		System.out.println(testOrder1.getOrderlines().size());
		testOrder1.deleteOrderline(testOrderline1);
		System.out.println(testOrder1.getOrderlines().size());
		assertEquals(0, testOrder1.getOrderlines().size(),0.001);
	}
	
	@Test
	public void testGetTotalPrice() {
		Product p1 = new Product("klosterbryg",productgroup);
		Product p2 = new Product("julebryg", productgroup);
		priceList = new PriceList("Test Price List");
		priceList.setPrice(p1, 50.0);
		priceList.setPrice(p2, 30.0);
		testOrderline2 = testOrder2.createOrderLine(p1, priceList, 2);
		testOrderline3 = testOrder2.createOrderLine(p2, priceList, 1);
		
		assertEquals(130.0,testOrder2.getTotalPrice(), 0.001);
	}
	
}
