package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ProductGroup;

public class ProductDraughtBeerSystem extends GridPane implements ProductType {
	
	/**
	 * Components
	 */
	private TextField txtNumberOfTaps;
	private Label lblNumberOfTaps; 
	private Controller controller;
	
	/**
	 * Constructor product draught beer system
	 */
	public ProductDraughtBeerSystem(GridPane grid) {
		grid.setPadding(new Insets(20));
		
		// grabs our controller
		this.controller = Controller.getController();
		
		// Label for NumberOfTaps input field.
		lblNumberOfTaps = new Label("Enter number of taps:");
		this.add(lblNumberOfTaps, 0, 2);
		
		// Input field for NumberOfTaps
		txtNumberOfTaps = new TextField();
		this.add(txtNumberOfTaps, 0, 3);
	}

	/**
	 * Method to create a new instance of product draught beer system
	 * @param productgroup, productName
	 */
	public void create(ProductGroup productgroup, String productName) {
		// creates an integer to hold txtNumberOfTaps value.
		int numberOfTaps = 0;
		
		try {
			// then we parse the string to an Integer value. 
			numberOfTaps = Integer.parseInt(txtNumberOfTaps.getText().trim());
			
		} catch(NumberFormatException error) {
			System.out.println("the amount of taps entered must be a real number.");
		} finally {
			// finally we create a new instance of productDraughtBeerSystem
			this.controller.createProductDraughtBeerSystem(productName, productgroup, numberOfTaps);
		}
	}
}
