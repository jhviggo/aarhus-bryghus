package model;

public class Accessory extends Product {
    String size;

    public Accessory(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
