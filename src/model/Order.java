package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.OrderLine;

public class Order {

	/**
	 * Attributes.
	 */
	private int ID;
	private LocalDateTime startTimestamp;
	private LocalDateTime endTimestamp;
	private OrderStatusType status;
	private ArrayList<OrderLine> orderlines;
	
	/**
	 * Constructor
	 * @param ID, startTimestamp, endTimestamp, status
	 */
	public Order(int id, LocalDateTime startTimestamp, LocalDateTime endTimestamp, OrderStatusType status) {
		this.ID = id;
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
		this.status = status;
		this.orderlines = new ArrayList<>();
	}

	/**
	 * Get method.
	 * Method to get endTimestamp value.
	 * @return endTimestamp
	 */
	public LocalDateTime getEndTimestamp() {
		return endTimestamp;
	}

	/**
	 * Set method.
	 * set endTimestamp attribute value.
	 * @param endTimestamp
	 */
	public void setEndTimestamp(LocalDateTime endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	/**
	 * Get method
	 * Method to get order status
	 * @return status
	 */
	public OrderStatusType getStatus() {
		return status;
	}

	/**
	 * Set method.
	 * set status attribute value.
	 * @param status
	 */
	public void setStatus(OrderStatusType status) {
		this.status = status;
	}

	/**
	 * Get method.
	 * Method to get order ID.
	 * @return ID
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Get method.
	 * Method to get start time stamp. 
	 * @return startTimestamp
	 */
	public LocalDateTime getStartTimestamp() {
		return startTimestamp;
	}
	
	/**
	 * Method to create a new OrderLine.
	 * @return a new instance of OrderLine.
	 */
	public OrderLine createOrderLine(Product product) {
		OrderLine orderLine = new OrderLine(product);
		orderlines.add(orderLine);
		return orderLine;
	}
	/**
	 * Method to delete a OrderLine.
	 * @param order
	 */
	public void deleteOrderline(OrderLine orderLine) {
		if (orderlines.contains(orderLine)) {
			orderlines.remove(orderLine);
		}
	}
	
	
	
}
