package model;

import java.time.LocalDateTime;

public class GuidedTour extends Product {

    private LocalDateTime dateTime;
    private double duration;

    public GuidedTour(String productName, ProductGroup productGroup, LocalDateTime dateTime,
                      double duration) {
        super(productName, productGroup);
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return super.toString() + " " + duration + "min";
    }
}
