package model;

public class RawMaterial extends Product {
    private double weight;

    public RawMaterial(String productName, ProductGroup productGroup, double weight) {
        super(productName, productGroup);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return super.toString() + " " + weight + "kg";
    }
}
