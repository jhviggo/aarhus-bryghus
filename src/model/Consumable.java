package model;

public abstract class Consumable extends Product {
    private int size;
    private String unit;
    private double alcoholPercentage;
    private String type;

    public Consumable(int size, String unit, double alcoholPercentage,
                      String type, String productName, ProductGroup productGroup) {
        super(productName, productGroup);
        this.size = size;
        this.unit = unit;
        this.alcoholPercentage = alcoholPercentage;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + type + ") " + alcoholPercentage + "%, " + size + unit;
    }
}
