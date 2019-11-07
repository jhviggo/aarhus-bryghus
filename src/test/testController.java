package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Controller;
import model.Beer;
import model.BeerType;
import model.GiftBox;
import model.GiftBoxType;
import model.Order;
import model.OrderLine;
import model.OrderStatusType;
import model.PaymentMethod;
import model.PriceList;
import model.Product;
import model.ProductGroup;

public class testController {

	private Controller controller = Controller.getTestController();
	
	public testController() {
		
	}
	
	@Test
	public void testCreateOrderTC1() {
		LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.JANUARY, 1, 12, 00); 
		Order newOrder = controller.createOrder(startTimeStamp, OrderStatusType.DONE);
		assertEquals(newOrder.getID(), newOrder.getID(), 0.001);
	}
	
	
	@Test
	public void testCreateOrderTC2() {
		try {
			LocalDateTime startTimeStamp = null;
			Order newOrder = controller.createOrder(startTimeStamp, OrderStatusType.DONE);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "startTimeStamp must not be null");
		}
	}
	
	@Test
	public void testUpdateOrderTC1() {
		LocalDateTime startTimeStamp = LocalDateTime.of(2018, Month.MAY, 5, 12, 00);
		
		Order newOrder = controller.createOrder(startTimeStamp, OrderStatusType.PROGRESS);
		
		controller.updateOrder(OrderStatusType.DONE, PaymentMethod.CASH, newOrder);

		assertEquals(controller.getOrders().get(0).getStatus().toString(), newOrder.getStatus().toString());
	}
	
	
	@Test
	public void testUpdateOrderTC2() {
		LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.JULY, 28, 10, 00);
		
		Order newOrder = controller.createOrder(startTimeStamp, OrderStatusType.CREATED);
		
		controller.updateOrder(OrderStatusType.DONE, PaymentMethod.CREDITCARD, newOrder);
		assertEquals(controller.getOrders().get(1).getStatus().toString(), newOrder.getStatus().toString());
	}
	
	@Test
	public void testUpdateOrderTC3() {
		LocalDateTime startTimeStamp = LocalDateTime.of(2018, Month.MAY, 5, 12, 00);
		
		Order newOrder = controller.createOrder(startTimeStamp, OrderStatusType.PROGRESS);
		controller.updateOrder(OrderStatusType.DONE, PaymentMethod.CASH, newOrder);
		
		assertEquals(controller.getOrders().get(2).getStatus().toString(), newOrder.getStatus().toString());
	}
	
	@Test
	public void testCreateOrderLineTC1() {
		int amount = 2;
		LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.OCTOBER, 5, 12, 00);
		PriceList testPriceList = controller.createPriceList("Test PriceList");
		Product newProduct = controller.createProduct("Jazz Classic", new ProductGroup("Test", 0));
		testPriceList.setPrice(newProduct, 50);
		Order order = controller.createOrder(startTimeStamp, OrderStatusType.CREATED);
		OrderLine newOrderline = controller.createOrderLine(newProduct, testPriceList, amount, order);
		
		assertEquals(controller.getOrderLines().get(0).getProduct().getProductName(), newProduct.getProductName());
	}
	
	@Test
	public void testCreateOrderLineTC2() {
		try {
			int amount = 2;
			LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.OCTOBER, 5, 12, 00);
			PriceList testPriceList = controller.createPriceList("Test PriceList");
			Product newProduct = null;
			testPriceList.setPrice(newProduct, 50);
			Order order = controller.createOrder(startTimeStamp, OrderStatusType.CREATED);
			OrderLine newOrderline = controller.createOrderLine(newProduct, testPriceList, amount, order);
			
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product, order and priceList must not be null");
		}
	}
	
	@Test
	public void testCreateOrderLineTC3() {
		try {
			int amount = 2;
			LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.OCTOBER, 5, 12, 00);
			PriceList testPriceList = controller.createPriceList("Test PriceList");
			Product newProduct = controller.createProduct("Jazz Classic", new ProductGroup("Test", 0));
			testPriceList.setPrice(newProduct, 50);
			Order order = null;
			OrderLine newOrderline = controller.createOrderLine(newProduct, testPriceList, amount, order);
		
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product, order and priceList must not be null");
		}
	}
	
	
	@Test
	public void testCreateOrderLineTC4() {
		try {
			
			int amount = 0;
			LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.OCTOBER, 5, 12, 00);
			PriceList testPriceList = controller.createPriceList("Test PriceList");
			Product newProduct = controller.createProduct("Jazz Classic", new ProductGroup("Test", 0));
			testPriceList.setPrice(newProduct, 50);
			Order order = controller.createOrder(startTimeStamp, OrderStatusType.CREATED);
			OrderLine newOrderline = controller.createOrderLine(newProduct, testPriceList, amount, order);
			
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(),  "Amount must be above 0");
		}
	}
	
	@Test
	public void testCreateOrderLineTC5() {
	try {
			
			int amount = -3;
			LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.OCTOBER, 5, 12, 00);
			PriceList testPriceList = controller.createPriceList("Test PriceList");
			Product newProduct = controller.createProduct("Jazz Classic", new ProductGroup("Test", 0));
			testPriceList.setPrice(newProduct, 50);
			Order order = controller.createOrder(startTimeStamp, OrderStatusType.CREATED);
			OrderLine newOrderline = controller.createOrderLine(newProduct, testPriceList, amount, order);
			
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(),  "Amount must be above 0");
		}
	}
	
	@Test
	public void testCreateGiftBoxTC1() {
		GiftBox giftBox = controller.createGiftBox("Test", new ProductGroup("productGroup", 0), GiftBoxType.SIXBEERS);
		
		assertEquals("Test", giftBox.getProductName());
	}
	
	@Test
	public void testCreateGiftBoxTC2() {
		try {
			
			GiftBox giftBox = controller.createGiftBox("", new ProductGroup("productGroup", 0), GiftBoxType.SIXBEERS);
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(),  "Product must have a name");
		}
	}
	
	@Test
	public void testCreateGiftBoxTC3() {
		try {
			
			GiftBox giftBox = controller.createGiftBox("Test", null, GiftBoxType.SIXBEERS);
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(),  "productGroup must not be null");
		}
	}
	
	@Test
	public void testAddProductToGiftBoxTC1() {
		try {
			Beer p1 = new Beer(60, "cl", 6.0, "India pale ale", "klosterbryg", new ProductGroup("test", 0), BeerType.BOTTLE);
			GiftBox gb = controller.createGiftBox("Gaveæske 2 øl, 2 glas", new ProductGroup("test", 0), GiftBoxType.FOURBEERS);
			gb.addProduct(p1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(),  "The giftbox is full");
		}
	}
	
	@Test 
	public void testRemoveProductFromGiftBoxTC1() {
		try {
			Beer p1 = new Beer(20, "L", 6.0, "Spiced / Herbed Beer", "JuleBryg", new ProductGroup("test", 0), BeerType.KEG);
			GiftBox gb = controller.createGiftBox("Gaveæske 2 øl, 2 glas", new ProductGroup("test", 0), GiftBoxType.FOURBEERS);
			gb.removeProduct(p1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product not found in giftbox");
		}
	}
	
	@Test
	public void testCreateProductTC1() {
		Product newProduct = controller.createProduct("Klosterbryg", new ProductGroup("test", 0));
		assertEquals(newProduct.getProductName(), "Klosterbryg");
	}
	
	@Test
	public void testCreateProdductTC2() {
		try {
			
			Product newProduct = controller.createProduct("", new ProductGroup("test", 0));
			
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product must have a name");
		}
	}
	
	@Test
	public void testCreateProductTC3() {
		try {
			
			Product newProduct = controller.createProduct("Klosterbryg", null);
			
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "ProductGroup must not be null");
		}
	}
	
	@Test
	public void testCreateProductGroupTC1() {
		ProductGroup snacks = controller.createProductGroup("snacks", 15.5);
		assertEquals(15.5, snacks.getTax(), 0.01);
	}
	
	@Test
	public void testCreateProductGroupTC2() {
		ProductGroup snacks = controller.createProductGroup("snacks", -2);
		assertEquals(-2, snacks.getTax(), 0.01);
	}
	
	@Test
	public void testCreateProductGroupTC3() {
		try {
			ProductGroup pg = controller.createProductGroup("", 10.0);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Type must not be empty");
		}
	}
	
	@Test
	public void testCreatePriceListTC1() {
		PriceList pl1 = controller.createPriceList("Friday bar");
		assertEquals(pl1.getType().toString(), "Friday bar");
	}
	
	@Test
	public void testCreatePriceListTC2() {
		try {
			PriceList pl1 = controller.createPriceList("");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Type must not be empty");
		}
	}
	
	@Test
	public void testAddProductToPriceListTC1() {
		Product p1 = controller.createProduct("Klosterbryg", new ProductGroup("test productgroup", 0));
		PriceList pl1 = controller.createPriceList("Friday bar");
		controller.addProductToPriceList(p1, 50, pl1);
		assertEquals(50, pl1.getPrice(p1), 0.01);
	}
	
	@Test
	public void testAddProductToPriceListTC2() {
		try {
			
			Product p1 = controller.createProduct("Klosterbryg", new ProductGroup("test productgroup", 0));
			PriceList pl1 = controller.createPriceList("Friday bar");
			controller.addProductToPriceList(p1, 0, pl1);
			
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Price must not be negative");
		}
	}
	
	@Test
	public void testAddProductToPriceListTC3() {
		try {
			
			Product p1 = controller.createProduct("Klosterbryg", new ProductGroup("test productgroup", 0));
			PriceList pl1 = null;
			controller.addProductToPriceList(p1, 50, pl1);
			
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Product and PriceList must not be null");
		}
	}
}
