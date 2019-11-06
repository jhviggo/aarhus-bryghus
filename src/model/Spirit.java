package model;

public class Spirit extends Consumable {

    /**
     * constructs a new spirit
     * @param size
     * @param unit
     * @param alcoholPercentage
     * @param type
     * @param productName
     * @param productGroup
     */
    public Spirit(int size, String unit, double alcoholPercentage, String type, String productName, ProductGroup productGroup) {
        super(size, unit, alcoholPercentage, type, productName, productGroup);
    }
}
