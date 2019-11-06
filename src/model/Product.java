package model;

public class Product implements Comparable<Product>{

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

    public ProductGroup getProductGroup() {
		return  productGroup;
	}

    public void setProductGroup(ProductGroup productGroup) {
		if (this.productGroup == null) {
			this.productGroup = productGroup;
			this.productGroup.addProduct(this);
		} else if (this.productGroup != productGroup) {
			ProductGroup pg = this.productGroup;
			this.productGroup = productGroup;
			this.productGroup.addProduct(this);
			pg.removeProduct(this);
		}
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
