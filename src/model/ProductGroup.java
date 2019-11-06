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
            p.setProductGroup(this);
        }
    }

    public void removeProduct(Product p) {
        if (p.getProductGroup() == this) {
            throw new RuntimeException("Produktet skal flyttes til en ny produktgruppe før det kan fjernes fra " + type);
        }
        if(products.contains(p)) {
        	p.removeProductGroup();
            products.remove(p);
        }
    }

    @Override
    public String toString() {
        return type;
    }
}
