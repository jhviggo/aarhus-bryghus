package model;

public class Beer extends Consumable {

    private BeerType beerType;

    public Beer(int size, String unit, double alcoholPercentage,
                String type, String productName, ProductGroup productGroup, BeerType beerType) {
        super(size, unit, alcoholPercentage, type, productName, productGroup);
        this.beerType = beerType;
    }

    public BeerType getBeerType() {
        return beerType;
    }
}
