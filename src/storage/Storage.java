package storage;

import java.util.ArrayList;

import model.GiftBox;
import model.Order;
import model.OrderLine;
import model.Product;
import model.ProductGroup;

public class Storage {

	/**
	 * Initialise storage ArrayList.
	 */
	private ArrayList<Order> orders;
	private ArrayList<OrderLine> orderLines;
	private ArrayList<Product> products;
	private ArrayList<ProductGroup> productgroups;
	private ArrayList<GiftBox> giftboxes;
	
	/**
	 * Constructor Storage.
	 */
	public Storage() {
		orders 			= new ArrayList<>();
		orderLines 		= new ArrayList<>();
		products 		= new ArrayList<>();
		productgroups 	= new ArrayList<>();
		giftboxes 		= new ArrayList<>();
	}
	
	/**
	 * Get method.
	 * Method to grab all orders from storage.
	 * @return ArrayList<Order>();
	 */
	public ArrayList<Order> getAllOrders() {
		return new ArrayList<>(orders);
	}
	
	/**
	 * Method to add an order to storage.
	 * @param order
	 */
	public void addOrder(Order order) {
		if (!orders.contains(order)) {
			orders.add(order);
		}
	}
	
	/**
	 * Method to remove an order from storage.
	 * @param order
	 */
	public void removeOrder(Order order) {
		if (orders.contains(order)) {
			orders.remove(order);
		}
	}
	
	/**
	 * Get method.
	 * Method to grab all OrderLines from storage.
	 * @return ArrayList<OrderLine>();
	 */
	public ArrayList<OrderLine> getAllOrderLines() {
		return new ArrayList<>(orderLines);
	}
	
	/**
	 * Method to add an OrderLine to storage.
	 * @param orderline
	 */
	public void addOrderLine(OrderLine orderline) {
		if (!orderLines.contains(orderline)) {
			orderLines.add(orderline);
		}
	}
	
	/**
	 * Method to remove an OrderLine from storage.
	 * @param orderline
	 */
	public void removeOrderLine(OrderLine orderline) {
		if (orderLines.contains(orderline)) {
			orderLines.remove(orderline);
		}
	}
	
	/**
	 * Get method.
	 * Method to grab all products from storage.
	 * @return ArrayList<Product>();
	 */
	public ArrayList<Product> getAllProducts() {
		return new ArrayList<>(products);
	}
	
	/**
	 * Method to add an products to storage.
	 * @param product
	 */
	public void addProduct(Product product) {
		if (!products.contains(product)) {
			products.add(product);
		}
	}
	/**
	 * Method to remove an product from storage.
	 * @param product
	 */
	public void removeProduct(Product product) {
		if (products.contains(product)) {
			products.remove(product);
		}
	}
	
	/**
	 * Get Method
	 * Method to grab all product groups from storage.
	 * @return ArrayList<ProductGroup>();
	 */
	public ArrayList<ProductGroup> getAllProductGroups() {
		return new ArrayList<>(productgroups);
	}
	
	/**
	 * Method to add a ProductGroup to storage.
	 */
	public void addProductGroup(ProductGroup productgroup) {
		if (!productgroups.contains(productgroup)) {
			productgroups.add(productgroup);
		}
	}
	
	/**
	 * Method to remove an ProductGroup from storage.
	 */
	public void removeProductGroup(ProductGroup productgroup) {
		if (productgroups.contains(productgroup)) {
			productgroups.remove(productgroup);
		}
	}
	
	/**
	 * Get Method.
	 * Method to grab all gift boxes from storage.
	 * @return ArrayList<GiftBox>();
	 */
	public ArrayList<GiftBox> getAllGiftBoxes() {
		return new ArrayList<>(giftboxes);
	}
	
	/**
	 * Method to add an gift box to storage.
	 * @param giftbox
	 */
	public void addGiftBox(GiftBox giftbox) {
		if (!giftboxes.contains(giftbox)) {
			giftboxes.add(giftbox);
		}
	}
	
	/**
	 * Method to remove an gift box from storage.
	 * @param giftbox
	 */
	public void removeGiftBox(GiftBox giftbox) {
		if (giftboxes.contains(giftbox)) {
			giftboxes.add(giftbox);
		}
	}
}
