package model;

public class Accessory extends Product {
    String size;

    public Accessory(String productName, ProductGroup productGroup, String size) {
        super(productName, productGroup);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
