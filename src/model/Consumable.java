package model;

public abstract class Consumable extends Product {
    private int size;
    private String unit;
    private double alcoholPercentage;
    private String type;

    public Consumable(int size, String unit, double alcoholPercentage, String type) {
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
}
