package model;

public class OrderLine {

	/**
	 * Attributes
	 */
	private PriceList priceList;
	private Product product;
	private int amount;
	
	/**
	 * Constructor
	 * @param product
	 */
	public OrderLine(Product product, PriceList priceList, int amount) {
		this.product = product;
		this.priceList = priceList;
		this.amount = amount;
	}


	/**
	 * Gets amount of product
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}


	/**
	 * Sets amount of product
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Get method.
	 * Get total prices from priceList.
	 * @return total price.
	 */
	public double getPrice() {
		double sum = 0.0;
		for (int i = 0; i < amount; i++) {
			sum += priceList.getPrice(product);
		}
	}
	
}
