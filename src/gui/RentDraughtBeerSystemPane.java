package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.PriceList;
import model.Product;

public class RentDraughtBeerSystemPane extends GridPane {
	/**
	 * Components
	 */
	private Controller controller;
	
	private Label lblRentedProducts;
	private Label lblCreateRent;
	private Label lblPriceList;
	private Label lblTotalProducts;
	private TextField txtTotalProducts;
	private ListView<Product> lvRentedProducts;
	private ComboBox<PriceList> cmbPriceList;
	private Button btnCreateRent;
	
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
		
		// label for rented products
		lblRentedProducts = new Label("Rented products:");
		this.add(lblRentedProducts, 0, 0);
		
		// listView rent products
		lvRentedProducts = new ListView<>();
		this.add(lvRentedProducts, 0, 1, 2, 3);
		//lvRentedProducts.getItems().setAll();
		
		// button to create a rent of product DraughtBeerSystem
		btnCreateRent = new Button("Create Rent");
		this.add(btnCreateRent, 3, 1);
		
		// attaches a click event handler
		btnCreateRent.setOnAction(e -> this.createRentDraughtBeerSystem());
		
	}
	
	/**
	 * Method to create a rent of a draughtbeersystem
	 */
	public void createRentDraughtBeerSystem() {
		
	}
}
