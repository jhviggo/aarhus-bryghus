package model;

import java.time.LocalDateTime;

public class GuidedTour extends Product {

    //Date and time of start of the guided tour
    private LocalDateTime dateTime;
    //Duration in minutes
    private int duration;

    public GuidedTour(String productName, ProductGroup productGroup, LocalDateTime dateTime,
                      int duration) {
        super(productName, productGroup);
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return super.toString() + " " + duration + "min";
    }
}
