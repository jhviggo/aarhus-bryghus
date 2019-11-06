package model;

public class RawMaterial extends Product {
    private double weight;

    /**
     * constructs a new raw material
     * @param productName
     * @param productGroup
     * @param weight
     */
    public RawMaterial(String productName, ProductGroup productGroup, double weight) {
        super(productName, productGroup);
        this.weight = weight;
    }

    /**
     * returns the weight of the raw material
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return super.toString() + " " + weight + "kg";
    }
}
