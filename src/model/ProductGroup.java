package model;

import java.util.ArrayList;

public class ProductGroup {
    private String type;
    private double tax;
    private ArrayList<Product> products;

    public ProductGroup(String type, double tax) {
        this.type = type;
        this.tax = tax;
        this.products = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public double getTax() {
        return tax;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product p) {
        if(!products.contains(p)) {
            products.add(p);
        }
    }

    public void removeProduct(Product p) {
        if(products.contains(p)) {
            products.remove(p);
        }
    }
}
