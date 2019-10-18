package model;

public class Grain extends Product {
    private double weight;

    public Grain(String productName, ProductGroup productGroup, double weight) {
        super(productName, productGroup);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
