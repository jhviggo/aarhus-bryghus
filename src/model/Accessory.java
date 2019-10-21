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

    @Override
    public String toString() {
        return super.toString() + " (" + size + ")";
    }
}
