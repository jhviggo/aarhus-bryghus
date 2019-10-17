package model;

public class Product {

	private String productName;

	public Product(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
	    return productName;
    }

	public void setProductName(String name) {
	    this.productName = name;
    }
}
