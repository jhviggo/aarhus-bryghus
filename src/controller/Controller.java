package controller;

import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

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
        if (startTimeStamp == null) {
            throw new IllegalArgumentException("startTimeStamp must not be null");
        }
        Order order = new Order(nextOrderId, startTimeStamp, status);
        Storage.addOrder(order);
        nextOrderId++;
        return order;
    }

    public void updateOrder( OrderStatusType status,
                            PaymentMethod paymentMethod,
                            Order order) {
        order.setStatus(status);
        order.setPaymentMethod(paymentMethod);
    }

    public void setPriceOverrideOnOrder(double priceOverride, Order order) {
        order.setPriceOverride(priceOverride);
    }

    public double getTotalPriceForOrder(Order order) {
        return order.getTotalPrice();
    }

    public void setDeliveryDateOnOrder(Order order, LocalDateTime deliveryDate) {
        order.setDeliveryDate(deliveryDate);
    }

    public ArrayList<Order> getOrders() {
        return Storage.getAllOrders();
    }

    public LocalDateTime getStartTimestampOnOrder(Order order) {
        return order.getStartTimestamp();
    }

    public LocalDateTime getEndTimestampOnOrder(Order order) {
        return order.getEndTimestamp();
    }

    public void setEndTimestampOnOrder(Order order) {
        order.setEndTimestamp(LocalDateTime.now().withNano(0));
    }

    public void setEndTimestampOnOrder(Order order, LocalDate date) {
        order.setEndTimestamp(LocalDateTime.of(date, LocalTime.of(12,0)));
    }

    public void setStartTimestampOnOrder(Order order, LocalDate date) {
        order.setStartTimestamp(LocalDateTime.of(date, LocalTime.of(12, 0)));
    }

    public OrderLine createOrderLine(Product product, PriceList priceList,
                                     int amount, Order order) {
        if (product == null || priceList == null || order == null) {
            throw new IllegalArgumentException("Product, order and priceList must not be null");
        }
        if (amount < 1) {
            throw new IllegalArgumentException("Amount must be above 0");
        }
        OrderLine line = order.createOrderLine(product, priceList, amount);
        Storage.addOrderLine(line);
        return line;
    }

    public void adjustOrderLineAmount(OrderLine orderLine, int amount) {
        orderLine.setAmount(orderLine.getAmount()+amount);
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
        if (productName.length() == 0) {
            throw new IllegalArgumentException("Product must have a name");
        }
        if (productGroup == null) {
            throw new IllegalArgumentException("productGroup must not be null");
        }
        GiftBox giftBox = new GiftBox(productName, productGroup, type);
        Storage.addProduct(giftBox);
        return giftBox;
    }

    public void addProductToGiftBox(GiftBox giftBox, Beer beer) {
        if (!giftBox.addProduct(beer)) {
            throw new IllegalStateException("The giftbox is full");
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
        if (productName.length() == 0) {
            throw new IllegalArgumentException("Product must have a name");
        }
        if (productGroup == null) {
            throw new IllegalArgumentException("ProductGroup must not be null");
        }
        Product product = new Product(productName, productGroup);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductAccessory(String productName, ProductGroup productGroup, String size) {
        Product product = new Accessory(productName, productGroup, size);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductGuidedTour(String productName, ProductGroup productGroup, LocalDateTime dateTime, double duration) {
        Product product = new GuidedTour(productName, productGroup, dateTime, duration);
        Storage.addProduct(product);
        return product;
    }

    public Product createProductRawMaterial(String productName, ProductGroup productGroup, double weight) {
        Product product = new RawMaterial(productName, productGroup, weight);
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

    public Product createProductClipCard(String productName, ProductGroup productGroup) {
    	Product product = new ClipCard(productName, productGroup);
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
        if (type.length() == 0) {
            throw new IllegalArgumentException("Type must not be empty");
        }
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
        if (type.length() == 0) {
            throw new IllegalArgumentException("Type must not be empty");
        }
        PriceList priceList = new PriceList(type);
        Storage.addPriceList(priceList);
        return priceList;
    }

    public void addProductToPriceList(Product product, double price, PriceList priceList) {
        if (product == null || priceList == null) {
            throw new IllegalArgumentException("Product and PriceList must not be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must not be negative");
        }
        priceList.setPrice(product, price);
    }

    public void deletePriceList(PriceList priceList) {
        Storage.removePriceList(priceList);
    }

    public ArrayList<PriceList> getPriceLists() {
        return Storage.getPriceLists();
    }

    /**
     * Method to create a ClipCard product
     * @param productName
     * @param productGroup
     * @return clipCard
     */
    public ClipCard createClipCard(String productName, ProductGroup productGroup) {
    	ClipCard clipCard = new ClipCard(productName, productGroup);
    	Storage.addClipCard(clipCard);
		return clipCard;
    }

    /**
     * Method to delete a clipCard
     * @param clipCard
     */
    public void deleteClipCard(ClipCard clipCard) {
    	Storage.removeClipCard(clipCard);
    }

    /**
     * Method to get all ClipCards from storage.
     * @return ArrayList clipCards
     */
    public ArrayList<ClipCard> getClipCards() {
    	return Storage.getClipCards();
    }

    public ArrayList<Order> getNotReturnedOrders() {
        return Storage.getAllOrders()
                .stream()
                .filter(order -> order.getStatus() == OrderStatusType.RENTED)
                .filter(order -> LocalDate.now().compareTo(order.getStartTimestamp().toLocalDate()) > 0
                        && order.getEndTimestamp() != null
                        && LocalDate.now().compareTo(order.getEndTimestamp().toLocalDate()) < 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public ArrayList<Product> getProductsInPriceList(PriceList priceList) {
        return priceList.getProducts();
    }

    private ArrayList<Order> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        return Storage.getAllOrders()
                .stream()
                .filter(order -> startDate.compareTo(order.getStartTimestamp().toLocalDate()) < 0
                        && order.getEndTimestamp() != null
                        && endDate.compareTo(order.getEndTimestamp().toLocalDate()) > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Order> getOrdersWithSoldClips(LocalDate startDate, LocalDate endDate) {
        return this.getOrdersBetweenDates(startDate, endDate)
                .stream()
                .filter(order -> this.amountOfContainedClipsInOrder(order) > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getUsedClipsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return (int) this.getOrdersBetweenDates(startDate, endDate)
                .stream()
                .filter(order -> order.getPaymentMethod() == PaymentMethod.CLIPCARD)
                .mapToInt(order -> order.getOrderlines()
                        .stream()
                        .mapToInt(OrderLine::getAmount)
                        .reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    public int getBoughtClipsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return this.getOrdersBetweenDates(startDate, endDate)
                .stream()
                .mapToInt(this::amountOfContainedClipsInOrder)
                .reduce(0, Integer::sum);
    }

    private int amountOfContainedClipsInOrder(Order order) {
        return order.getOrderlines()
                .stream()
                .filter(line -> line.getProduct().getProductName().equals("klippe kort"))
                .mapToInt(OrderLine::getAmount)
                .reduce(0, Integer::sum);
    }

    public void exportOrders() {
        String csvToExport = "ID,STATUS,DATE,PAYMENT METHOD,PRODUCT,AMOUNT,SINGLE PRICE,TOTAL PRICE;\n";

        for(Order o : this.getOrders()) {
            if (o.getStatus() == OrderStatusType.DONE && o.getOrderlines().size() > 0) {
                csvToExport += this.exportOrderAsCSV(o);
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("orders" + ".csv"));
            writer.write(csvToExport);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file");
        }
    }

    public String exportOrderAsCSV(Order order) {
        StringBuilder csvOutput = new StringBuilder();
        for (OrderLine ol : order.getOrderlines()) {
            csvOutput
                .append(order.getID()).append(',')
                .append(order.getStatus()).append(',')
                .append(order.getStartTimestamp()).append(',')
                .append(order.getPaymentMethod()).append(',')
                .append(ol.getProduct().getProductName()).append(',')
                .append(ol.getAmount()).append(',')
                .append(ol.getPriceList().getPrice(ol.getProduct())).append(',')
                .append(ol.getPrice()).append(";\n");
        }

        return csvOutput.toString();
    }

    public void initializeData() {
    	// Productgroups
        ProductGroup pg1 = createProductGroup("flaske", 0);
        ProductGroup pg2 = createProductGroup("fadøl", 0);
        ProductGroup pg3 = createProductGroup("spiritus", 0);
        ProductGroup pg4 = createProductGroup("fustage", 200);
        ProductGroup pg5 = createProductGroup("kulsyre", 1000);
        ProductGroup pg6 = createProductGroup("Gaveæsker", 0);
        ProductGroup pg7 = createProductGroup("Snacks", 0);
        ProductGroup pg8 = createProductGroup("Beklædning", 0);
        ProductGroup pg9 = createProductGroup("Fadølsanlæg", 0);
        ProductGroup pg10 = createProductGroup("Rundvisning", 0);
        ProductGroup pg11 = createProductGroup("Malt", 0);

        //Bottled beer
        Product p1 = createProductBeer("Klosterbryg", pg1, 60,
                "cl", 6.0, "India Pale Lager", BeerType.BOTTLE);
        Product p2 = createProductBeer("Sweet Georgia Brown", pg1, 60,
                "cl", 5.5, "Brown Ale", BeerType.BOTTLE);
        Product p3 = createProductBeer("Extra Pilsner", pg1, 60,
                "cl", 5.0, "Pilsner", BeerType.BOTTLE);
        Product p4 = createProductBeer("Celebration", pg1, 60,
                "cl", 6.5, "Pale Ale", BeerType.BOTTLE);
        Product p5 = createProductBeer("Blondie", pg1, 60,
                "cl", 5.0, "Pale Ale", BeerType.BOTTLE);
        Product p6 = createProductBeer("Forårsbryg", pg1, 60,
                "cl", 7.0, "Dortmunder", BeerType.BOTTLE);
        Product p7 = createProductBeer("India Pale Ale", pg1, 60,
                "cl", 6.0, "India Pale Ale", BeerType.BOTTLE);
        Product p8 = createProductBeer("Julebryg", pg1, 60,
                "cl", 6.0, "Spiced / Herbed Beer", BeerType.BOTTLE);
        Product p9 = createProductBeer("Juletønden", pg1, 60,
                "cl", 8.0, "Winter Ale", BeerType.BOTTLE);
        Product p10 = createProductBeer("Old Strong Ale", pg1, 60,
                "cl", 8.0, "Old Ale", BeerType.BOTTLE);
        Product p11 = createProductBeer("Fregatten Jylland", pg1, 60,
                "cl", 8.0, "Winter Warmer", BeerType.BOTTLE);
        Product p12 = createProductBeer("Imperial Stout", pg1, 60,
                "cl", 8.0, "Imperial Stout", BeerType.BOTTLE);
        Product p13 = createProductBeer("Tribute", pg1, 60,
                "cl", 9.0, "Barley Wine", BeerType.BOTTLE);
        Product p14 = createProductBeer("Black Monster", pg1, 60,
                "cl", 10.0, "Porter", BeerType.BOTTLE);

        //Draught beer and soda
        Product p15 = createProductBeer("Klosterbryg", pg2, 40,
                "cl", 6.0, "India Pale Lager", BeerType.DRAUGHT);
        Product p16 = createProductBeer("Jazz Classic", pg2, 40,
                "cl", 5.0, "English Mild Ale", BeerType.DRAUGHT);
        Product p17 = createProductBeer("Extra Pilsner", pg2, 40,
                "cl", 5.0, "Pilsner", BeerType.DRAUGHT);
        Product p18 = createProductBeer("Celebration", pg2, 40,
                "cl", 6.5, "Pale Ale", BeerType.DRAUGHT);
        Product p19 = createProductBeer("Blondie", pg2, 40,
                "cl", 5.0, "Pale Ale", BeerType.DRAUGHT);
        Product p20 = createProductBeer("Forårsbryg", pg2, 40,
                "cl", 7.0, "Dortmunder", BeerType.DRAUGHT);
        Product p21 = createProductBeer("India Pale Ale", pg2, 40,
                "cl", 6.0, "India Pale Ale", BeerType.DRAUGHT);
        Product p22 = createProductBeer("Julebryg", pg2, 40,
                "cl", 6.0, "Spiced / Herbed Beer", BeerType.DRAUGHT);
        Product p23 = createProductBeer("Imperial Stout", pg2, 40,
                "cl", 8.0, "Imperial Stout", BeerType.DRAUGHT);
        Product p24 = createProductBeer("Special", pg2, 40,
                "cl", 0.0, "??", BeerType.DRAUGHT);
        Product p25 = createProductBeer("Æblebrus", pg2, 40,
                "cl", 0.0, "Alcoholfree Cider", BeerType.DRAUGHT);
        Product p26 = createProductBeer("Cola", pg2, 40,
                "cl", 0.0, "Cola", BeerType.DRAUGHT);
        Product p27 = createProductBeer("Nikoline", pg2, 40,
                "cl", 0.0, "Squash", BeerType.DRAUGHT);
        Product p28 = createProductBeer("7-Up", pg2, 40,
                "cl", 0.0, "Soda", BeerType.DRAUGHT);
        Product p29 = createProductBeer("Water", pg2, 40,
                "cl", 0.0, "Water", BeerType.DRAUGHT);

        //Snacks
        Product p30 = createProduct("Chips", pg7);
        Product p31 = createProduct("Peanuts", pg7);

        //Spirits
        Product p32 = createProductSpirit("Spirit of Aarhus", pg3, 50,
                "cl", 40.0, "Whisky");
        Product p33 = createProductSpirit("Spirit of Aarhus (med egesplint", pg3, 50,
                "cl", 40.0, "Whisky");
        Product p34 = createProductSpirit("Whisky", pg3, 50,
                "cl", 45.0, "Whisky");
        Product p35 = createProductSpirit("Liquor of Aarhus", pg3, 35,
                "cl", 30.0, "Liquor");

        //Kegs
        Product p36 = createProductBeer("Klosterbryg", pg4, 20,
                "L", 6.0, "India Pale Lager", BeerType.KEG);
        Product p37 = createProductBeer("Jazz Classic", pg4, 25,
                "L", 5.0, "English Mild Ale", BeerType.KEG);
        Product p38 = createProductBeer("Extra Pilsner", pg4, 25,
                "L", 5.0, "Pilsner", BeerType.KEG);
        Product p39 = createProductBeer("Celebration", pg4, 20,
                "L", 6.5, "Pale Ale", BeerType.KEG);
        Product p40 = createProductBeer("Blondie", pg4, 25,
                "L", 5.0, "Pale Ale", BeerType.KEG);
        Product p41 = createProductBeer("Forårsbryg", pg4, 20,
                "L", 7.0, "Dortmunder", BeerType.KEG);
        Product p42 = createProductBeer("India Pale Ale", pg4, 20,
                "L", 6.0, "India Pale Ale", BeerType.KEG);
        Product p43 = createProductBeer("Julebryg", pg4, 20,
                "L", 6.0, "Spiced / Herbed Beer", BeerType.KEG);
        Product p44 = createProductBeer("Imperial Stout", pg4, 20,
                "L", 8.0, "Imperial Stout", BeerType.KEG);

        //Carbon dioxide
        Product p45 = createProduct("Kulsyre 6kg", pg5);
        Product p46 = createProduct("Kulsyre 4kg", pg5);
        Product p47 = createProduct("Kulsyre 10kg", pg5);

        //Clothes
        Product p48 = createProductAccessory("T-Shirt", pg8, "L");
        Product p49 = createProductAccessory("Polo", pg8, "L");
        Product p50 = createProductAccessory("Cap", pg8, "One-size");

        //Draught Beer Systems
        Product p51 = createProductDraughtBeerSystem("Fadølsanlæg 1 hane", pg9, 1);
        Product p52 = createProductDraughtBeerSystem("Fadølsanlæg 2 haner", pg9, 2);
        Product p53 = createProductDraughtBeerSystem("Fadølsanlæg med flere haner",
                pg9, 0);

        //Giftboxes
        Product p54 = createGiftBox("Gaveæske 2 øl, 2 glas",
                pg6, GiftBoxType.TWOBEERSTWOGLASSES);
        Product p55 = createGiftBox("Gaveæske 4 øl",
                pg6, GiftBoxType.FOURBEERS);
        Product p56 = createGiftBox("Trækasse 6 øl",
                pg6, GiftBoxType.SIXBEERS);
        Product p57 = createGiftBox("Gavekurv 6 øl, 2 glas",
                pg6, GiftBoxType.SIXBEERSTWOGLASSES);
        Product p58 = createGiftBox("Trækasse 6 øl, 6 glas",
                pg6, GiftBoxType.SIXBEERSSIXGLASSES);
        Product p59 = createGiftBox("Trækasse 12 øl",
                pg6, GiftBoxType.TWELVEBEERSWOOD);
        Product p60 = createGiftBox("Papkasse 12 øl",
                pg6, GiftBoxType.TWELVEBEERSCARDBOARD);

        // Raw material
        Product p61 = createProductRawMaterial("Malt 25kg", pg11, 25);

        PriceList pl1 = createPriceList("Default");
        PriceList pl2 = createPriceList("Fredagsbar");

        //Bottled beer default
        pl1.setPrice(p1, 36);
        pl1.setPrice(p2, 36);
        pl1.setPrice(p3, 36);
        pl1.setPrice(p4, 36);
        pl1.setPrice(p5, 36);
        pl1.setPrice(p6, 36);
        pl1.setPrice(p7, 36);
        pl1.setPrice(p8, 36);
        pl1.setPrice(p9, 36);
        pl1.setPrice(p10, 36);
        pl1.setPrice(p11, 36);
        pl1.setPrice(p12, 36);
        pl1.setPrice(p13, 36);
        pl1.setPrice(p14, 50);

        //Spirits default
        pl1.setPrice(p32, 300);
        pl1.setPrice(p33, 350);
        pl1.setPrice(p34, 500);
        pl1.setPrice(p35, 175);

        //Kegs default
        pl1.setPrice(p36, 775);
        pl1.setPrice(p37, 625);
        pl1.setPrice(p38, 575);
        pl1.setPrice(p39, 775);
        pl1.setPrice(p40, 700);
        pl1.setPrice(p41, 775);
        pl1.setPrice(p42, 775);
        pl1.setPrice(p43, 775);
        pl1.setPrice(p44, 775);

        //Carbon dioxide default
        pl1.setPrice(p45, 400);

        //Raw material default
        pl1.setPrice(p61, 300);

        //Clothing default
        pl1.setPrice(p48, 70);
        pl1.setPrice(p49, 100);
        pl1.setPrice(p50, 30);

        //Draught beer systems default
        pl1.setPrice(p51, 250);
        pl1.setPrice(p52, 400);
        pl1.setPrice(p53, 500);

        //Giftboxes default
        pl1.setPrice(p54, 100);
        pl1.setPrice(p55, 130);
        pl1.setPrice(p56, 240);
        pl1.setPrice(p57, 250);
        pl1.setPrice(p58, 290);
        pl1.setPrice(p59, 390);
        pl1.setPrice(p60, 360);


        //Bottled beer friday bar
        pl2.setPrice(p1, 50);
        pl2.setPrice(p2, 50);
        pl2.setPrice(p3, 50);
        pl2.setPrice(p4, 50);
        pl2.setPrice(p5, 50);
        pl2.setPrice(p6, 50);
        pl2.setPrice(p7, 50);
        pl2.setPrice(p8, 50);
        pl2.setPrice(p9, 50);
        pl2.setPrice(p10, 50);
        pl2.setPrice(p11, 50);
        pl2.setPrice(p12, 50);
        pl2.setPrice(p13, 50);
        pl2.setPrice(p14, 50);

        //Draught beer friday bar
        pl2.setPrice(p15, 30);
        pl2.setPrice(p16, 30);
        pl2.setPrice(p17, 30);
        pl2.setPrice(p18, 30);
        pl2.setPrice(p19, 30);
        pl2.setPrice(p20, 30);
        pl2.setPrice(p21, 30);
        pl2.setPrice(p22, 30);
        pl2.setPrice(p23, 30);
        pl2.setPrice(p24, 30);
        pl2.setPrice(p25, 15);
        pl2.setPrice(p26, 15);
        pl2.setPrice(p27, 15);
        pl2.setPrice(p28, 15);
        pl2.setPrice(p29, 10);

        //Snacks friday bar
        pl2.setPrice(p30, 15);
        pl2.setPrice(p31, 15);

        //Spirits friday bar
        pl2.setPrice(p32, 300);
        pl2.setPrice(p33, 350);
        pl2.setPrice(p34, 500);
        pl2.setPrice(p35, 175);

        //Clothing friday bar
        pl2.setPrice(p48, 70);
        pl2.setPrice(p49, 100);
        pl2.setPrice(p50, 30);

        Order o1 = createOrder(LocalDateTime.now(), OrderStatusType.CREATED);
        createOrderLine(p1, pl1, 4, o1);
        createOrderLine(p3, pl1, 2, o1);
        updateOrder(OrderStatusType.PROGRESS, PaymentMethod.PAYLATER, o1);
        Order o2 = createOrder(LocalDateTime.now(), OrderStatusType.CREATED);
        createOrderLine(p2, pl1, 5, o2);
        createOrderLine(p4, pl1, 3, o2);
        updateOrder(OrderStatusType.DONE, PaymentMethod.CREDITCARD, o2);


        // ClipCards
        Product clip1 = createProductClipCard("klippe kort", pg1);
        Product clip2 = createProductClipCard("klippe kort", pg2);
        Product clip3 = createProductClipCard("klippe kort", pg1);
        Product clip4 = createProductClipCard("klippe kort", pg2);
        Product clip5 = createProductClipCard("klippe kort", pg1);
        Product clip6 = createProductClipCard("klippe kort", pg2);

        // adds clipcards to pricelists
        addProductToPriceList(clip1, 100, pl2);
        addProductToPriceList(clip2, 100, pl2);
        addProductToPriceList(clip3, 100, pl2);
        addProductToPriceList(clip4, 100, pl2);
        addProductToPriceList(clip5, 100, pl1);
        addProductToPriceList(clip6, 100, pl1);

        // Orders
        Order co1 = createOrder(LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 11), LocalTime.of(17, 45)), OrderStatusType.DONE);
        Order co2 = createOrder(LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 12), LocalTime.of(14, 31)), OrderStatusType.DONE);
        Order co3 = createOrder(LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 12), LocalTime.of(15, 02)), OrderStatusType.DONE);
        Order co4 = createOrder(LocalDateTime.of(LocalDate.of(2019, Month.OCTOBER, 29), LocalTime.of(9, 54)), OrderStatusType.CREATED);
        Order co5 = createOrder(LocalDateTime.of(LocalDate.of(2019, Month.OCTOBER, 10), LocalTime.of(16, 30)), OrderStatusType.PROGRESS);
        Order co6 = createOrder(LocalDateTime.of(LocalDate.of(2019, Month.OCTOBER, 10), LocalTime.of(17, 15)), OrderStatusType.PROGRESS);

        // adds a new orderline to each Order.
        co1.createOrderLine(clip1, pl2, 10);
        co2.createOrderLine(clip2, pl2, 25);
        co3.createOrderLine(clip3, pl2, 3);
        co4.createOrderLine(clip4, pl2, 4);
        co5.createOrderLine(clip5, pl1, 2);
        co6.createOrderLine(clip6, pl1, 2);

        co1.setPaymentMethod(PaymentMethod.CLIPCARD);
    }
}
