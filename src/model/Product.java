package model;

public class Product {

	private String productName;
	private ProductGroup productGroup;

	public Product(String productName, ProductGroup productGroup) {
		this.productName = productName;
		this.productGroup = productGroup;
		productGroup.addProduct(this);
	}

	public String getProductName() {
	    return productName;
    }

	public void setProductName(String name) {
	    this.productName = name;
    }
}
