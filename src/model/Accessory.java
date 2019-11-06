package model;

public class Accessory extends Product {
    String size;

    /**
     * constructs a new accessory
     * @param productName
     * @param productGroup
     * @param size
     */
    public Accessory(String productName, ProductGroup productGroup, String size) {
        super(productName, productGroup);
        this.size = size;
    }

    /**
     * returns accessory size
     * @return size
     */
    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + size + ")";
    }
}
