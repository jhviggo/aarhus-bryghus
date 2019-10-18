package model;

import java.util.HashMap;

import static model.BeerType.BOTTLE;

public class GiftBox extends Product {

    private HashMap<Beer, Integer> productsInGiftCase;
    private int amountOfProducts;
    private int maxAmountOfProducts;
    private GiftBoxType type;

    public GiftBox(String productName, GiftBoxType type,
                   ProductGroup productGroup) {
        super(productName, productGroup);
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

    public boolean addProduct(Beer beer) {
        if (amountOfProducts == maxAmountOfProducts ||
                beer.getBeerType() != BOTTLE) {
            return false;
        }
        if (productsInGiftCase.containsKey(beer)) {
            productsInGiftCase.put(beer,
                    productsInGiftCase.get(beer)+1);
        } else {
            productsInGiftCase.put(beer, 1);
        }
        return true;
    }

    public boolean removeProduct(Beer beer) {
        if (!productsInGiftCase.containsKey(beer)) {
            return false;
        }
        if (productsInGiftCase.get(beer) == 1) {
            productsInGiftCase.remove(beer);
        } else {
            productsInGiftCase.put(beer,
                    productsInGiftCase.get(beer)-1);
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
