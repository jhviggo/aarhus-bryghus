package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Product;

public class RentDraughtBeerSystemPane extends GridPane {
	/**
	 * Components
	 */
	private Controller controller;
	
	private Label lblRentedProducts;
	private ListView<Product> lvRentedProducts;
	

	/**
	 * Constructor rent pane
	 */
	public RentDraughtBeerSystemPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

		// grabs controller
		this.controller = Controller.getController();
	}
}
