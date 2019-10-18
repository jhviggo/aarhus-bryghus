package model;

import java.util.HashMap;

public class PriceList {

	/**
	 * Attributes
	 */
	private String type;
	HashMap<Product, Double> productsInPriceList;
	HashMap<GiftBoxType, Double> giftBoxPrices;

	public PriceList(String type) {
		this.type = type;
		productsInPriceList = new HashMap<>();
		giftBoxPrices = new HashMap<>();
	}

	public PriceList(String type, HashMap<Product, Double> productsInPriceList,
					 HashMap<GiftBoxType, Double> giftBoxPrices) {
		this.type = type;
		this.productsInPriceList = productsInPriceList;
		this.giftBoxPrices = giftBoxPrices;
	}

	/**
	 * Method to set price on product.
	 * @param product
	 */
	public void setPrice(Product product, double price) {
		if (product instanceof GiftBox) {
			giftBoxPrices.put(((GiftBox) product).getType(), price);
		} else {
			productsInPriceList.put(product, price);
		}
	}

	/**
	 * Get method.
	 * Method to get product price.
	 * @param product
	 * @return product price.
	 */
	public double getPrice(Product product) {
		if (product instanceof GiftBox) {
			return giftBoxPrices.get(((GiftBox) product).getType());
		}
	    return productsInPriceList.get(product);
	}
}
