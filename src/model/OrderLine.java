package model;

public class OrderLine {

	/**
	 * Attributes
	 */
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
	
}
