package model;

import java.time.LocalDateTime;

public class GuidedTour extends Product {

    //Date and time of start of the guided tour
    private LocalDateTime dateTime;
    //Duration in minutes
    private int duration;

    public GuidedTour(String productName, LocalDateTime dateTime,
                      int duration) {
        super(productName);
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDuration() {
        return duration;
    }



}
