package model;

import java.time.LocalDateTime;

public class GuidedTour extends Product {

    private LocalDateTime dateTime;
    private double duration;

    /**
     * constructs a new guided tour
     * @param productName
     * @param productGroup
     * @param dateTime
     * @param duration
     */
    public GuidedTour(String productName, ProductGroup productGroup, LocalDateTime dateTime,
                      double duration) {
        super(productName, productGroup);
        this.dateTime = dateTime;
        this.duration = duration;
    }

    /**
     * returns the date of the tour
     * @return date
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * returns the duration of the tour
     * @return duration
     */
    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return super.toString() + " " + duration + "min";
    }
}
