package model;

public abstract class Consumable extends Product {
    private int size;
    private String unit;
    private double alcoholPercentage;
    private String type;

    /**
     * constructs a new consumable
     * @param size
     * @param unit
     * @param alcoholPercentage
     * @param type
     * @param productName
     * @param productGroup
     */
    public Consumable(int size, String unit, double alcoholPercentage,
                      String type, String productName, ProductGroup productGroup) {
        super(productName, productGroup);
        this.size = size;
        this.unit = unit;
        this.alcoholPercentage = alcoholPercentage;
        this.type = type;
    }

    /**
     * returns size of consumable
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * returns unit of consumable
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * returns alcohol percentage of consumable
     * @return alcoholPercentage
     */
    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    /**
     * returns type of consumable
     * @return type
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + type + ") " + alcoholPercentage + "%, " + size + unit;
    }
}
