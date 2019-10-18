package controller;

import model.*;
import java.lang.IllegalStateException;
import java.time.LocalDateTime;

public class Controller {

    private static int nextOrderId = 0;

    public Order createOrder(LocalDateTime startTimeStamp,
                             OrderStatusType status) {
        Order order = new Order(nextOrderId, startTimeStamp, status);
        nextOrderId++;
        return order;
    }

    public void setEndTimestampOnOrder(Order order) {
        order.setEndTimestamp(LocalDateTime.now());
    }

    public OrderLine createOrderLine(Product product, PriceList priceList,
                                     int amount, Order order) {
        OrderLine line = order.createOrderLine(product, priceList, amount);
        return line;
    }

    public void deleteOrderLineOnOrder(OrderLine orderLine, Order order) {
        order.deleteOrderline(orderLine);
    }

    public GiftBox createGiftBox(String productName, ProductGroup productGroup,
                                 GiftBoxType type) {
        GiftBox giftBox = new GiftBox(productName, productGroup, type);
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

}