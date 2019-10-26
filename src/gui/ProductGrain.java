package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Grain;
import model.ProductGroup;

public class ProductGrain extends GridPane implements ProductType {

	/**
	 * Components 
	 */
	private Controller controller;
	private Label lblWeight;
	private TextField txtWeight;
	
	/**
	 * Constructor for productGrain pane.
	 * @param grid
	 */
	public ProductGrain(GridPane grid) {
        grid.setPadding(new Insets(20));
        // grabs controller
        controller = Controller.getController();
        
        // Label weight
        lblWeight = new Label("Enter weight:");
        grid.add(lblWeight, 0,2);
        
        txtWeight = new TextField();
        grid.add(txtWeight, 0, 3, 1, 1);
	}
	
	/**
	 * Method to create product grain
	 * @param ProductGroup productgroup, String productName
	 */
	public void create(ProductGroup productgroup, String productName) {
		// double created to hold weight textfield value.
		double weight = 0;
		try {
			weight = Double.parseDouble(txtWeight.getText().trim());
		} catch (NullPointerException e) { // throws nullpointer if string is null.
			System.out.println(e.getMessage());
		} catch (NumberFormatException err) { // throws string parsed does not contain a parsable double.
			System.out.println(err.getMessage());
		}
		finally {
			// call createProductGrain method from controller.
			this.controller.createProductGrain(productName, productgroup, weight);	
		}
		
	}

}
