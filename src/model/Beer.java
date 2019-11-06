package model;

public class Beer extends Consumable {

    private BeerType beerType;

    /**
     * constructs a new beer
     * @param size
     * @param unit
     * @param alcoholPercentage
     * @param type
     * @param productName
     * @param productGroup
     * @param beerType
     */
    public Beer(int size, String unit, double alcoholPercentage,
                String type, String productName, ProductGroup productGroup, BeerType beerType) {
        super(size, unit, alcoholPercentage, type, productName, productGroup);
        this.beerType = beerType;
    }

    /**
     * returns beer type as defined by enum BeerType
     * @return beerType
     */
    public BeerType getBeerType() {
        return beerType;
    }
}
