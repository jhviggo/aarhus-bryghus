package model;

public class DraughtBeerSystem extends Product {
    private int numberOfTaps;

    /**
     * constructs a new draught beer system
     * @param productName
     * @param productGroup
     * @param numberOfTaps
     */
    public DraughtBeerSystem(String productName, ProductGroup productGroup, int numberOfTaps) {
        super(productName, productGroup);
        this.numberOfTaps = numberOfTaps;
    }

    /**
     * returns number of beer tabs
     * @return numberOfTaps
     */
    public int getNumberOfTaps() {
        return numberOfTaps;
    }

    @Override
    public String toString() {
        return super.toString() + " " + numberOfTaps + " taps";
    }
}
