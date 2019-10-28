package controller;

import model.*;
import java.lang.IllegalStateException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import storage.Storage;

public class Controller {

    private static int nextOrderId = 0;
    private static Controller controller;

    private Controller() {}

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public Order createOrder(LocalDateTime startTimeStamp,
                             OrderStatusType status) {
        Order order = new Order(nextOrderId, startTimeStamp, status);
        Storage.addOrder(order);
        nextOrderId++;
        return order;
    }

    public void updateOrder(LocalDateTime startTimeStamp,
                            OrderStatusType status,
                            PaymentMethod paymentMethod,
                            Order order) {
        order.setStartTimestamp(startTimeStamp);
        order.setStatus(status);
        order.setPaymentMethod(paymentMethod);
    }

    public void setPriceOverrideOnOrder(double priceOverride, Order order) {
        order.setPriceOverride(priceOverride);
    }

    public void setDeliveryDateOnOrder(Order order, LocalDateTime deliveryDate) {
        order.setDeliveryDate(deliveryDate);
    }

    public ArrayList<Order> getOrders() {
        return Storage.getAllOrders();
    }

    public void setEndTimestampOnOrder(Order order) {
        order.setEndTimestamp(LocalDateTime.now().withNano(0));
    }

    public OrderLine createOrderLine(Product product, PriceList priceList,
                                     int amount, Order order) {
        OrderLine line = order.createOrderLine(product, priceList, amount);
        Storage.addOrderLine(line);
        return line;
    }

    public void deleteOrderLineOnOrder(OrderLine orderLine, Order order) {
        order.deleteOrderline(orderLine);
        Storage.removeOrderLine(orderLine);
    }

    public ArrayList<OrderLine> getOrderLines() {
        return Storage.getAllOrderLines();
    }

    public ArrayList<OrderLine> getOrderLinesOnOrder(Order order) {
        return order.getOrderlines();
    }

    public GiftBox createGiftBox(String productName, ProductGroup productGroup,
                                 GiftBoxType type) {
        GiftBox giftBox = new GiftBox(productName, productGroup, type);
        Storage.addProduct(giftBox);
        return giftBox;
    }

    public void addProductToGiftBox(GiftBox giftBox, Beer beer) {
        if (!giftBox.addProduct(beer)) {
            throw new IllegalStateException("The giftbox is either full, or" +
                    " you tried to add an invalid product.");
        }
    }

    public void removeProductFromGiftBox(GiftBox giftBox, Beer beer) {
        if (!giftBox.removeProduct(beer)) {
            throw new IllegalStateException("Product not found in giftbox");
        }
    }

    public ArrayList<GiftBox> getGiftBoxes() {
        return Storage.getAllgiftBoxes();
    }

    // Product
    public Product createProduct(String productName, ProductGroup productGroup) {
        Product product = new Product(productName, productGroup);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductAccessory(String productName, ProductGroup productGroup, String size) {
        Product product = new Accessory(productName, productGroup, size);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductGuidedTour(String productName, ProductGroup productGroup, LocalDateTime dateTime, int duration) {
        Product product = new GuidedTour(productName, productGroup, dateTime, duration);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductGrain(String productName, ProductGroup productGroup, double weight) {
        Product product = new Grain(productName, productGroup, weight);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductDraughtBeerSystem(String productName, ProductGroup productGroup, int numberOfTabs) {
        Product product = new DraughtBeerSystem(productName, productGroup, numberOfTabs);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductBeer(String productName, ProductGroup productGroup, int size, String unit, double alcoholPercentage, String type, BeerType beerType) {
        Product product = new Beer(size, unit, alcoholPercentage, type, productName, productGroup, beerType);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductSpirit(String productName, ProductGroup productGroup, int size, String unit, double alcoholPercentage, String type) {
        Product product = new Spirit(size, unit, alcoholPercentage, type, productName, productGroup);
        Storage.addProduct(product);
        return product;
    }

    public void deleteProduct(Product product) {
        product.removeProductGroup();
        Storage.removeProduct(product);
    }

    public void moveProductToProductGroup(Product product, ProductGroup productGroup) {
        product.setProductGroup(productGroup);
    }

    public ArrayList<Product> getProducts() {
        return Storage.getAllProducts();
    }

    // ProductGroup
    public ProductGroup createProductGroup(String type, double tax) {
        ProductGroup productGroup = new ProductGroup(type, tax);
        Storage.addProductGroup(productGroup);
        return productGroup;
    }

    public void deleteProductGroup(ProductGroup productGroup) {
        if (productGroup.getProducts().size() > 0) {
            throw new RuntimeException("Gruppen som ønskes slette skal være tom");
        }
        Storage.removeProductGroup(productGroup);
    }

    public ArrayList<ProductGroup> getProductGroups() {
        return Storage.getAllproductGroups();
    }

    // PriceList
    public PriceList createPriceList(String type, HashMap<Product, Double> productsInPriceList,
                                     HashMap<GiftBoxType, Double> giftBoxPrices) {
        PriceList priceList = new PriceList(type, productsInPriceList, giftBoxPrices);
        Storage.addPriceList(priceList);
        return priceList;
    }

    public PriceList createPriceList(String type) {
        PriceList priceList = new PriceList(type);
        Storage.addPriceList(priceList);
        return priceList;
    }

    public void addProductToPriceList(Product product, double price, PriceList priceList) {
        if (price <= 0) {
            throw new RuntimeException("Produckpriser må ikke være negative");
        }
        priceList.setPrice(product, price);
    }

    public void deletePriceList(PriceList priceList) {
        Storage.removePriceList(priceList);
    }

    public ArrayList<PriceList> getPriceLists() {
        return Storage.getPriceLists();
    }

    public void initializeData() {
        ProductGroup pg1 = createProductGroup("flaske", 0);
        ProductGroup pg2 = createProductGroup("fadøl", 0);
        ProductGroup pg3 = createProductGroup("spiritus", 0);
        ProductGroup pg4 = createProductGroup("fustage", 200);
        ProductGroup pg5 = createProductGroup("kulsyre", 1000);
        ProductGroup pg6 = createProductGroup("Gaveæsker", 0);

        Product p1 = createProductBeer("Klosterbryg", pg1, 60,
                "cl", 6.0, "India Pale Lager", BeerType.BOTTLE);
        Product p2 = createProduct("Sweet Georgie Brown", pg1);
        Product p3 = createProduct("Extra Pilsner", pg1);
        Product p4 = createProduct("Klosterbryg", pg2);
        Product p5 = createProduct("Jazz Classic", pg2);
        Product p6 = createProduct("Blondie", pg2);
        Product p7 = createProduct("Spirit of Aarhus", pg3);
        Product p8 = createProduct("Whiskey", pg3);
        Product p9 = createProduct("Imperial Stout, 20L", pg4);
        Product p10 = createProduct("Jazz Classic, 20L", pg4);
        Product p11 = createProduct("Julebryg, 20L", pg4);
        Product p12 = createProduct("6kg", pg5);
        Product p13 = createProduct("10kg", pg5);
        Product p14 = createGiftBox("GiftboxTest", pg6, GiftBoxType.SIXBEERSTWOGLASSES);

        PriceList pl1 = createPriceList("default");
        PriceList pl2 = createPriceList("fredagsbar");

        addProductToPriceList(p1, 36, pl1);
        addProductToPriceList(p2, 36, pl1);
        addProductToPriceList(p3, 36, pl1);
        addProductToPriceList(p4, 30, pl1);
        addProductToPriceList(p5, 30, pl1);
        addProductToPriceList(p6, 30, pl1);
        addProductToPriceList(p7, 300, pl1);
        addProductToPriceList(p8, 500, pl1);
        addProductToPriceList(p9, 775, pl1);
        addProductToPriceList(p10, 625, pl1);
        addProductToPriceList(p11, 775, pl1);
        addProductToPriceList(p12, 400, pl1);
        addProductToPriceList(p13, 400, pl1);
        addProductToPriceList(p14, 555, pl1);

        addProductToPriceList(p1, 50, pl2);
        addProductToPriceList(p2, 50, pl2);
        addProductToPriceList(p3, 50, pl2);
        addProductToPriceList(p4, 50, pl2);
        addProductToPriceList(p5, 50, pl2);
        addProductToPriceList(p6, 50, pl2);
    }
}
