package storage;

import java.util.ArrayList;

import model.*;

public class Storage {

	/**
	 * Initialise storage ArrayList.
	 */
	private static final ArrayList<Order> orders = new ArrayList<>();
	private static final ArrayList<OrderLine> orderLines = new ArrayList<>();
	private static final ArrayList<Product> products = new ArrayList<>();
	private static final ArrayList<ProductGroup> productGroups = new ArrayList<>();
	private static final ArrayList<GiftBox> giftBoxes = new ArrayList<>();
	private static final ArrayList<PriceList> priceLists = new ArrayList<PriceList>();
	private static final ArrayList<ClipCard> clipCards = new ArrayList<ClipCard>();

	/**
	 * Get method.
	 * Method to grab all orders from storage.
	 * @return ArrayList<Order>();
	 */
	public static ArrayList<Order> getAllOrders() {
		return new ArrayList<>(orders);
	}

	/**
	 * Method to add an order to storage.
	 * @param order
	 */
	public static void addOrder(Order order) {
		if (!orders.contains(order)) {
			orders.add(order);
		}
	}

	/**
	 * Method to remove an order from storage.
	 * @param order
	 */
	public static void removeOrder(Order order) {
		if (orders.contains(order)) {
			orders.remove(order);
		}
	}

	/**
	 * Get method.
	 * Method to grab all OrderLines from storage.
	 * @return ArrayList<OrderLine>();
	 */
	public static ArrayList<OrderLine> getAllOrderLines() {
		return new ArrayList<>(orderLines);
	}

	/**
	 * Method to add an OrderLine to storage.
	 * @param orderline
	 */
	public static void addOrderLine(OrderLine orderline) {
		if (!orderLines.contains(orderline)) {
			orderLines.add(orderline);
		}
	}

	/**
	 * Method to remove an OrderLine from storage.
	 * @param orderline
	 */
	public static void removeOrderLine(OrderLine orderline) {
		if (orderLines.contains(orderline)) {
			orderLines.remove(orderline);
		}
	}

	/**
	 * Get method.
	 * Method to grab all products from storage.
	 * @return ArrayList<Product>();
	 */
	public static ArrayList<Product> getAllProducts() {
		return new ArrayList<>(products);
	}

	/**
	 * Method to add an products to storage.
	 * @param product
	 */
	public static void addProduct(Product product) {
		if (!products.contains(product)) {
			products.add(product);
		}
	}
	/**
	 * Method to remove an product from storage.
	 * @param product
	 */
	public static void removeProduct(Product product) {
		if (products.contains(product)) {
			products.remove(product);
		}
	}

	/**
	 * Get Method
	 * Method to grab all product groups from storage.
	 * @return ArrayList<ProductGroup>();
	 */
	public static ArrayList<ProductGroup> getAllproductGroups() {
		return new ArrayList<>(productGroups);
	}

	/**
	 * Method to add a ProductGroup to storage.
	 */
	public static void addProductGroup(ProductGroup productgroup) {
		if (!productGroups.contains(productgroup)) {
			productGroups.add(productgroup);
		}
	}

	/**
	 * Method to remove an ProductGroup from storage.
	 */
	public static void removeProductGroup(ProductGroup productgroup) {
		if (productGroups.contains(productgroup)) {
			productGroups.remove(productgroup);
		}
	}

	/**
	 * Get Method.
	 * Method to grab all gift boxes from storage.
	 * @return ArrayList<GiftBox>();
	 */
	public static ArrayList<GiftBox> getAllgiftBoxes() {
		return new ArrayList<>(giftBoxes);
	}

	/**
	 * Method to add an gift box to storage.
	 * @param giftbox
	 */
	public static void addGiftBox(GiftBox giftbox) {
		if (!giftBoxes.contains(giftbox)) {
			giftBoxes.add(giftbox);
		}
	}

	/**
	 * Method to remove an gift box from storage.
	 * @param giftbox
	 */
	public static void removeGiftBox(GiftBox giftbox) {
		if (giftBoxes.contains(giftbox)) {
			giftBoxes.add(giftbox);
		}
	}

	/**
	 * Method to return price lists
	 * @return
	 */
	public static ArrayList<PriceList> getPriceLists() {
		return new ArrayList<>(priceLists);
	}

	/**
	 * Method to add price list
	 * @param priceList
	 */
	public static void addPriceList(PriceList priceList) {
		if (!priceLists.contains(priceList)) {
			priceLists.add(priceList);
		}
	}

	/**
	 * Method to remove price list from priceLists
	 * @param priceList
	 */
	public static void removePriceList(PriceList priceList) {
		if (priceLists.contains(priceList)) {
			priceLists.remove(priceList);
		}
	}
	
	/**
	 * Method to get clipCards from storage
	 * @return clipCards
	 */
	public static ArrayList<ClipCard> getClipCards() {
		return new ArrayList<>(clipCards);
	}
	
	/**
	 * Method to add a clipCard to clipCards
	 * @param clipCard
	 */
	public static void addClipCard(ClipCard clipCard) {
		if (!clipCards.contains(clipCard)) {
			clipCards.add(clipCard);
		}
	}
	
	/**
	 * Method to remove a clipCard from clipCards
	 * @param clipCard
	 */
	public static void removeClipCard(ClipCard clipCard) {
		if (clipCards.contains(clipCard)) {
			clipCards.remove(clipCard);
		}
	}
}
