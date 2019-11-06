package model;

import java.util.ArrayList;

public class ProductGroup {
    private String type;
    private double tax;
    private ArrayList<Product> products;

    /**
     * constructs the product group with a type and tax
     * @param type
     * @param tax
     */
    public ProductGroup(String type, double tax) {
        this.type = type;
        this.tax = tax;
        this.products = new ArrayList<>();
    }

    /**
     * returns the group type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * returns the tax
     * @return tax
     */
    public double getTax() {
        return tax;
    }

    /**
     * returns the products in the group
     * @return products
     */
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    /**
     * adds a new product to the group
     * @param product
     */
    public void addProduct(Product product) {
        if(!products.contains(product)) {
            products.add(product);
            product.setProductGroup(this);
        }
    }

    /**
     * removes a product from the product group
     * @param product
     */
    public void removeProduct(Product product) {
        if (product.getProductGroup() == this) {
            throw new RuntimeException("Produktet skal flyttes til en ny produktgruppe før det kan fjernes fra " + type);
        }
        if(products.contains(product)) {
            products.remove(product);
        }
    }

    @Override
    public String toString() {
        return type;
    }
}
