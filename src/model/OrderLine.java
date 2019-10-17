package model;

public class OrderLine {

	/**
	 * Attributes
	 */
	private Order order;
	private PriceList priceList;
	private Product product;
	
	/**
	 * Constructor
	 * @param product
	 */
	public OrderLine(Product product) {
		this.product = product;
	}
	/**
	 * Get method.
	 * Get total prices from priceList.
	 * @return total price.
	 */
	public double getPrice() {
		return 0.0;
	}
	
	/**
	 * Method to set order to null value.
	 */
	public void removeOrder() {
		this.order = null;
	}
}
