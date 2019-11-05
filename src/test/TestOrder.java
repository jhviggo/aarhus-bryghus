package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import model.Order;
import model.OrderStatusType;

public class TestOrder {

	private Order testOrder1;
	private Order testOrder2;
	private Order testOrder3;
	
	public TestOrder() {
		// Basic Data
		int id = 1;
		LocalDateTime startTimeStamp = LocalDateTime.of(2019, Month.JANUARY, 1, 12, 00);
		// TestCase 1:
		testOrder1 = new Order(id, startTimeStamp, OrderStatusType.CREATED);
		// TestCase 2:
		testOrder2 = new Order(id, startTimeStamp, OrderStatusType.PROGRESS);
		// TestCase 3:
		testOrder3 = new Order(id, startTimeStamp, OrderStatusType.DONE);
	}

	/**
	 * TestCases: Basic Data 
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


}
