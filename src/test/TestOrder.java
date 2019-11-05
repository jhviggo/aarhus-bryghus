package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import model.Order;
import model.OrderStatusType;

public class TestOrder {

	private Order newOrder;

	@Before
	public void setup() {
		
	}
	
	public TestOrder() {
		int id = 1;
		LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.JANUARY, 1, 12, 00);
		newOrder = new Order(id, startTimeStamp, OrderStatusType.DONE);
	}

	/**
	 * TestCase 1: Basic Data 
	 * id = 1
	 * startTimeStamp = ”2019-01-01”
	 */
	@Test
	public void testCase1() {
		assertEquals(1.0 , newOrder.getID(), 0.001);
	}

}
