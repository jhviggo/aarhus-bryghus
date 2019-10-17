package model;

import java.util.HashMap;

public class GiftBox extends Product {

    private HashMap<Product, Integer> productsInGiftCase;
    private int amountOfProducts;
    private int maxAmountOfProducts;
    private GiftBoxType type;

    public GiftBox(String productName, GiftBoxType type) {
        super(productName);
        productsInGiftCase = new HashMap<>();
        amountOfProducts = 0;
        this.type = type;
        switch(type) {
            case TWOBEERSTWOGLASSES: maxAmountOfProducts = 2; break;
            case FOURBEERS: maxAmountOfProducts = 4; break;
            case SIXBEERS:
            case SIXBEERSSIXGLASSES:
            case SIXBEERSTWOGLASSES:
                maxAmountOfProducts = 6; break;
            case TWELVEBEERSWOOD:
            case TWELVEBEERSCARDBOARD:
                maxAmountOfProducts = 12; break;
        }
    }

    public boolean addProduct(Product product) {
        if (amountOfProducts == maxAmountOfProducts ||
                !(product instanceof Beer)) {
            return false;
        }
        if (productsInGiftCase.containsKey(product)) {
            productsInGiftCase.put(product,
                    productsInGiftCase.get(product)+1);
        } else {
            productsInGiftCase.put(product, 1);
        }
        return true;
    }

    public boolean removeProduct(Product product) {
        if (!productsInGiftCase.containsKey(product)) {
            return false;
        }
        if (productsInGiftCase.get(product) == 1) {
            productsInGiftCase.remove(product);
        } else {
            productsInGiftCase.put(product,
                    productsInGiftCase.get(product)-1);
        }
        return true;
    }

    public GiftBoxType getType() {
        return type;
    }

    public HashMap<Product, Integer> getProductsInGiftCase() {
        return new HashMap(productsInGiftCase);
    }
}
