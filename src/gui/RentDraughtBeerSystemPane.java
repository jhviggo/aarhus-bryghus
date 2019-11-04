package gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent.EventType;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.DraughtBeerSystem;
import model.PriceList;
import model.Product;

public class RentDraughtBeerSystemPane extends GridPane {
	/**
	 * Components
	 */
	private Controller controller;
	private RentDraughtBeerSystemDialog rentDraughtBeerSystemDialog;
	private ArrayList<Product> systems;
	
	private Label lblRentedProducts;
	private Label lblProductsNotReturned;
	private ListView<Product> lvProducts;
	private ListView<Product> lvProductsNotReturned;
	private Button btnCreate;
	
	private Button btnUpdate;
	private Product selectedProduct;
	
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
		// fills systems array with products
		systems = controller.getProducts();
		
		// label for rented products
		lblRentedProducts = new Label("All products:");
		this.add(lblRentedProducts, 0, 0);
		
		// listView rent products
		lvProducts = new ListView<>();
		this.add(lvProducts, 0, 1, 2, 3);
		populateProductsListView();
		
		
		// label products not returned
		lblProductsNotReturned = new Label("Products not returned:");
		this.add(lblProductsNotReturned, 2, 0);
		
		// listView containing not returned products
		lvProductsNotReturned = new ListView<>();
		this.add(lvProductsNotReturned, 2, 1, 2, 3);
		populateListViewNotReturnedProducts(); 
		
		// button to create a rent of product DraughtBeerSystem
		btnCreate = new Button("Create Rent");
		this.add(btnCreate, 0, 5);
		
		// attaches a click event handler
		btnCreate.setOnAction(e -> this.openCreateRentDraughtBeerSystemDialog());
		
		// update button
		btnUpdate = new Button("Update");
		btnUpdate.setDisable(true);
		this.add(btnUpdate, 1, 5);
		
		// attaches click event on update button
		btnUpdate.setOnAction(ev -> this.updateRentDraughtBeerSystemDialog());
		
		
		// mouse pressed event handler listView product
		lvProducts.setOnMousePressed(event -> {
			btnUpdate.setDisable(false); 
			// stores selected product
			selectedProduct = lvProducts.getSelectionModel().getSelectedItem();
		});
		
		lvProductsNotReturned.setOnMousePressed(e -> {
			btnUpdate.setDisable(false); 
			// stores selected product
			selectedProduct = lvProducts.getSelectionModel().getSelectedItem();
		});
	}
	
	/**
	 * Method to update selected draught beer system
	 */
	public void updateRentDraughtBeerSystemDialog() {
		if (selectedProduct != null) {
			// stores selected product
			selectedProduct = lvProducts.getSelectionModel().getSelectedItem();
			
			// creates a new instance of dialog pane with argument
			rentDraughtBeerSystemDialog = new RentDraughtBeerSystemDialog(selectedProduct);
			rentDraughtBeerSystemDialog.showAndWait();
			
			// refresh view
			refreshProducts();
			refreshNotReturnedProducts();
		}
	}
	
	
	/**
	 * Method to open DraughtBeerSystemDialog
	 */
	public void openCreateRentDraughtBeerSystemDialog() {
		// creates a new instance of dialog pane
		rentDraughtBeerSystemDialog = new RentDraughtBeerSystemDialog();
		rentDraughtBeerSystemDialog.showAndWait();
	
		// refresh view
		refreshProducts();
		refreshNotReturnedProducts();
	}
	
	/**
	 * Method to populate listView with non delivered products
	 */
	public void populateListViewNotReturnedProducts() {
		ArrayList<Product> missingProducts = new ArrayList<>();
		systems = controller.getProducts();
		for (Product p : systems) {
			if (p instanceof DraughtBeerSystem && ((DraughtBeerSystem) p).getEndDate().isBefore(LocalDate.now())) {
				missingProducts.add(p);
			}
		}
		lvProductsNotReturned.getItems().addAll(missingProducts);
	}
	
	/**
	 * Method to refresh listView with not returned products
	 */
	public void refreshNotReturnedProducts() {
		ArrayList<Product> missingProducts = new ArrayList<>();
		systems = controller.getProducts();
		for (Product p: systems) {
			if (p instanceof DraughtBeerSystem && ((DraughtBeerSystem) p).getEndDate().isBefore(LocalDate.now())) {
				missingProducts.add(p);
			}
		}
		lvProductsNotReturned.getItems().setAll(missingProducts);
	}
	
	/**
	 * Method to refresh DraughtBeerSystems
	 */
	public void refreshProducts() {
		ArrayList<Product> systemProducts = new ArrayList<>();
		systems = controller.getProducts();
		for (Product p : systems) {
			if (p instanceof DraughtBeerSystem) {
				systemProducts.add(p);
			}
		}
		lvProducts.getItems().setAll(systemProducts);
	}
	
	/**
	 * Method to populate product listView
	 */
	public void populateProductsListView() {
		for (Product p : systems) {
			if (p instanceof DraughtBeerSystem) {
				lvProducts.getItems().add(p);
			}
		}
	}
	
	
}
