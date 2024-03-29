package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PriceList {

	private String type;
	private HashMap<Product, Double> productsInPriceList;
	private HashMap<GiftBoxType, Double> giftBoxPrices;
	private ArrayList<Product> giftboxes;

	/**
	 * construcs a price list with a type
	 * @param type
	 */
	public PriceList(String type) {
		this.type = type;
		productsInPriceList = new HashMap<>();
		giftBoxPrices = new HashMap<>();
		giftboxes = new ArrayList<>();
	}

	/**
	 * constructs a price list with a type, products and gift boxes
	 * @param type
	 * @param productsInPriceList
	 * @param giftBoxPrices
	 */
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
			if (!giftboxes.contains(product)) {
				giftboxes.add(product);
			}
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

	/**
	 * Get method.
	 * Method to get PriceList type
	 * @return PriceList type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * returns a list of strings with product name and price
	 * @return product names and prices
	 */
	public ArrayList<String> getProductsWithPriceInPriceList() {
		ArrayList<String> productPricesList = new ArrayList<>();

		productsInPriceList
				.entrySet()
				.forEach(item -> productPricesList.add(item.getKey().getProductName()
						+ "  -  " + item.getValue() + "kr"));

		return productPricesList;
	}

	/**
	 * returns products
	 * @return products
	 */
	public ArrayList<Product> getProducts() {
		ArrayList<Product> result = new ArrayList<>(this.productsInPriceList.keySet());
		Collections.sort(result);
		result.addAll(giftboxes);
		return result;
	}

	@Override
	public String toString() {
		return type;
	}
}
