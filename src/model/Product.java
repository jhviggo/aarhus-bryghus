package model;

public class Product implements Comparable<Product>{

	private String productName;
	private ProductGroup productGroup;

	/**
	 * constructor with product name and product group
	 * @param productName
	 * @param productGroup
	 */
	public Product(String productName, ProductGroup productGroup) {
		this.productName = productName;
		this.productGroup = productGroup;
		productGroup.addProduct(this);
	}

	/**
	 * returns product name
	 * @return product name
	 */
	public String getProductName() {
	    return productName;
    }

	/**
	 * sets product name
	 * @param name of product
	 */
	public void setProductName(String name) {
	    this.productName = name;
    }

	/**
	 * returns product group
	 * @return product group
	 */
	public ProductGroup getProductGroup() {
		return  productGroup;
	}

	/**
	 * moves the product to a new product group og sets one
	 * @param productGroup
	 */
    public void setProductGroup(ProductGroup productGroup) {
		if (this.productGroup == null) {
			this.productGroup = productGroup;
			this.productGroup.addProduct(this);
		} else if (this.productGroup != productGroup) {
			this.productGroup.removeProduct(this);
			this.productGroup = productGroup;
			productGroup.addProduct(this);
		}
	}

	public void removeProductGroup() {
		ProductGroup old = productGroup;
		productGroup = null;
		old.removeProduct(this);
	}

	@Override
	public String toString() {
		return productName;
	}

	@Override
	public int compareTo(Product product) {
		return productName.compareTo(product.getProductName());
	}
}
