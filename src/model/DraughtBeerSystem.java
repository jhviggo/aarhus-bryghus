package model;

public class DraughtBeerSystem extends Product {
    private int numberOfTaps;

    public DraughtBeerSystem(String productName, ProductGroup productGroup, int numberOfTaps) {
        super(productName, productGroup);
        this.numberOfTaps = numberOfTaps;
    }

    public int getNumberOfTaps() {
        return numberOfTaps;
    }

    @Override
    public String toString() {
        return super.toString() + " " + numberOfTaps + " taps";
    }
}
