package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ProductGroup;

public class ProductRawMaterial extends GridPane implements ProductType {

	/**
	 * Components
	 */
	private Controller controller;
	private Label lblWeight;
	private TextField txtWeight;
	private GridPane grid;

	/**
	 * Constructor for productRawMaterial pane.
	 * @param grid
	 */
	public ProductRawMaterial(GridPane grid) {
        grid.setPadding(new Insets(20));
        this.grid = grid;
        controller = Controller.getController();

        lblWeight = new Label("Enter weight:");
        grid.add(lblWeight, 0,2);

        txtWeight = new TextField();
        grid.add(txtWeight, 0, 3);
	}

	/**
	 * Method to create product raw material
	 * @param productgroup
	 * @param productName
	 */
	public void create(ProductGroup productgroup, String productName) {
		double weight = 0;
		try {
			weight = Double.parseDouble(txtWeight.getText().trim());
		} catch (NullPointerException e) { // throws nullpointer if string is null.
			System.out.println(e.getMessage());
		} catch (NumberFormatException err) { // throws string parsed does not contain a parsable double.
			System.out.println(err.getMessage());
		}
		finally {
			this.controller.createProductRawMaterial(productName, productgroup, weight);
		}

	}

	/**
	 * Method to delete all components, used to cleanse pane.
	 */
	public void delete() {
		grid.getChildren().removeAll(
				lblWeight, txtWeight
				);
	}
}
