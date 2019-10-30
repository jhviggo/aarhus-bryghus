package gui;

import model.ProductGroup;

public interface ProductType {
	public abstract void create(ProductGroup productgroup, String productName);
	
	public abstract void delete();
}
