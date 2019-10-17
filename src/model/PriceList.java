package model;

import java.util.HashMap;

public class PriceList {

	/**
	 * Attributes
	 */
	private String type;
	private double prices;
	HashMap<Product, Double> productsInPriceList;
	/**
	 * Method to set price on product.
	 * @param product
	 */
	public void setPrice(Product product, double price) {
		productsInPriceList.put(product, price);
	}
	
	/**
	 * Get method.
	 * Method to get product price.
	 * @param product
	 * @return product price.
	 */
	public double getPrice(Product product) {
	    return productsInPriceList.get(product);
	}
}
