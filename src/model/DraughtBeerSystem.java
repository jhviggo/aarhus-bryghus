package model;

import java.time.LocalDateTime;

public class DraughtBeerSystem extends Product {
    
	/**
	 * Attributes
	 */
	private int numberOfTaps;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    /**
     * Constructor
     * @param productName
     * @param productGroup
     * @param numberOfTaps
     * @param startDate
     * @param endDate
     */
    public DraughtBeerSystem(String productName, ProductGroup productGroup, int numberOfTaps) {
        super(productName, productGroup);
        this.numberOfTaps = numberOfTaps;
    }

    /**
     * Method to get number of taps
     * @return numberOfTaps
     */
    public int getNumberOfTaps() {
        return numberOfTaps;
    }
    
    /**
     * Method to set value of start date for rent
     * @param startDate
     */
    public void setStartDate(LocalDateTime startDate) {
    	this.startDate = startDate;
    }
    
    /**
     * Method to get start date of rent
     * @return startDate
     */
    public LocalDateTime getStartDate() {
    	return this.startDate;
    }
    
    /**
     * Method to set end date of rent
     * @param endDate
     */
    public void setEndDate(LocalDateTime endDate) {
    	this.endDate = endDate;
    }
    
    /**
     * Method to get end date of rent
     * @return
     */
    public LocalDateTime getEndDate() {
    	return this.endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " " + numberOfTaps + " taps";
    }
}
