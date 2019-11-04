package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

	/**
	 * Attributes.
	 */
	private int ID;
	private LocalDateTime startTimestamp;
	private LocalDateTime endTimestamp;
	private LocalDateTime deliveryDate;
	private OrderStatusType status;
	private ArrayList<OrderLine> orderlines;
	private PaymentMethod paymentMethod;
	private double priceOverride;

	/**
	 * Constructor
	 * @param id, startTimestamp, endTimestamp, status
	 */
	public Order(int id, LocalDateTime startTimestamp, OrderStatusType status) {
		this.ID = id;
		this.startTimestamp = startTimestamp;
		this.status = status;
		this.orderlines = new ArrayList<>();
		priceOverride = -1;

		if (status == OrderStatusType.DONE) {
			this.endTimestamp = startTimestamp;
		}
	}

	/**
	 * Returns delivery date
	 * @return LocalDate
	 */
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Sets delivery date
	 * @param deliveryDate
	 */
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
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

	/**
	 * Set method
	 * sets startTimestamp attribute value
	 * @param startTimeStamp
	 */
	public void setStartTimestamp(LocalDateTime startTimeStamp) {
		this.startTimestamp = startTimeStamp;
	}

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
	 * Get mehod
	 * @return paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Sets paymentMethod
	 * @param paymentMethod
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Sets priceOverride
	 * @param price
	 */
	public void setPriceOverride(double price) {
		priceOverride = price;
	}

	/**
	 * Returns value of priceOverride
	 * @return priceOverride
	 */
	public double getPriceOverride() {
		return priceOverride;
	}

	/**
	 * Method to create a new OrderLine.
	 * @return a new instance of OrderLine.
	 */
	public OrderLine createOrderLine(Product product, PriceList priceList, int amount) {
		if (product == null || priceList == null) {
			throw new IllegalArgumentException("Product and priceList must not be null");
		}
		if (amount < 0) {
			throw new IllegalArgumentException("amount must be positive");
		}

		OrderLine orderLine = new OrderLine(product, priceList, amount);
		orderlines.add(orderLine);
		return orderLine;
	}

	/**
	 * Method to delete a OrderLine.
	 * @param orderLine
	 */
	public void deleteOrderline(OrderLine orderLine) {
		if (orderlines.contains(orderLine)) {
			orderlines.remove(orderLine);
		}
	}

	public ArrayList<OrderLine> getOrderlines() {
		return new ArrayList<>(orderlines);
	}

	public double getTotalPrice() {
		return orderlines
				.stream()
				.mapToDouble(OrderLine::getPrice)
				.reduce(0, Double::sum);
	}

	@Override
	public String toString() {
		return status + " - Order " + ID + " - " + getTotalPrice() + " DKK - " + paymentMethod;
	}
}
