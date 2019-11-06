package model;

import java.util.HashMap;

import static model.BeerType.BOTTLE;

public class GiftBox extends Product {

    private HashMap<Beer, Integer> productsInGiftCase;
    private int amountOfProducts;
    private int maxAmountOfProducts;
    private GiftBoxType type;

    /**
     * constructs a new gift box
     * @param productName
     * @param productGroup
     * @param type
     */
    public GiftBox(String productName, ProductGroup productGroup, GiftBoxType type) {
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

    /**
     * returns max amount of products allowed in gift box
     * @return maxAmountOfProducts
     */
    public int getMaxAmountOfProducts() {
        return maxAmountOfProducts;
    }

    /**
     * adds a beer to the gift box
     * @param beer
     * @return boolean
     */
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
        amountOfProducts++;
        return true;
    }

    /**
     * removed a beer from the gift box
     * @param beer
     * @return boolean
     */
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
        amountOfProducts--;
        return true;
    }

    /**
     * returns type as defined in enum GiftBoxType
     * @return type
     */
    public GiftBoxType getType() {
        return type;
    }

    /**
     * returns products in gift box
     * @return products
     */
    public HashMap<Beer, Integer> getProductsInGiftCase() {
        return new HashMap(productsInGiftCase);
    }
}
