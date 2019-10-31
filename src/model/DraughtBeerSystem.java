package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DraughtBeerSystem extends Product {
    
	/**
	 * Attributes
	 */
	private int numberOfTaps;
    private LocalDate startDate;
    private LocalDate endDate;
    
    /**
     * Constructor
     * @param productName
     * @param productGroup
     * @param numberOfTaps
     * @param startDate
     * @param endDate
     */
    public DraughtBeerSystem(String productName, ProductGroup productGroup, int numberOfTaps, 
    		LocalDate startDate, LocalDate endDate) {
        super(productName, productGroup);
        this.startDate = startDate;
        this.endDate = endDate;
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
    public void setStartDate(LocalDate startDate) {
    	this.startDate = startDate;
    }
    
    /**
     * Method to get start date of rent
     * @return startDate
     */
    public LocalDate getStartDate() {
    	return this.startDate;
    }
    
    /**
     * Method to set end date of rent
     * @param endDate
     */
    public void setEndDate(LocalDate endDate) {
    	this.endDate = endDate;
    }
    
    /**
     * Method to get end date of rent
     * @return
     */
    public LocalDate getEndDate() {
    	return this.endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " " + numberOfTaps + " taps";
    }
}
