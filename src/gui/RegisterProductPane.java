package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.stage.Stage; 
import javafx.scene.text.Text.*; 
import javafx.scene.paint.*; 
import javafx.scene.text.*;
import model.ProductGroup;

public class RegisterProductPane extends GridPane {
	/**
	 * Components
	 */
	private ComboBox<ProductGroup> cmbProductGroup;
	private Button btnCancel;
	private Button btnSave;
	private TextField txtProductName;
	private Label lblProductGroup;
	private Label lblProductName;
	private Button btnCreate;
	
	/**
	 * Dynamic components.
	 */
	private GridPane grid;
	private ProductBeer productBeerPane;
	private ProductSpirit productSpiritusPane;
	private ProductDraughtBeerSystem productDraughtBeerSystem;
	private ProductGrain productGrain;
	private ProductType selectedProductType;
	private ProductGroup selectedProductGroup;
	private String selectedProductGroupName;
	private String productName;
	private Controller controller;
	private GridPane selectedPane;

	/**
	 * Pane constructor
	 * setup screen view
	 */
	public RegisterProductPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        // grabs controller
        this.controller = Controller.getController();
        
        // label for product group
        lblProductGroup = new Label("choose Product group:");
        this.add(lblProductGroup, 0,0);
        
        // product group drop down menu component
        cmbProductGroup = new ComboBox<>();
        cmbProductGroup.setPrefWidth(400);
        cmbProductGroup.getItems().setAll(controller.getProductGroups());
        
        
        cmbProductGroup.getSelectionModel().selectFirst();
 
        // Selected product group index
        selectedProductGroup = cmbProductGroup.getSelectionModel().getSelectedItem();
        
        // EventHandler to handle selected index changed event.
        // Create action event 
        EventHandler<ActionEvent> event = 
                  new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	// stores the new selected item product group name
                selectedProductGroupName = cmbProductGroup.getSelectionModel().getSelectedItem().getType();
                // Calls buildPane
                buildPane();
            } 
        }; 
  
        // Set on action 
        cmbProductGroup.setOnAction(event);
        
        selectedProductGroupName = cmbProductGroup.getSelectionModel().getSelectedItem().getType();
        
        // cmbProductGroup.getOnMouseClicked(this.openDynamicPane());
        this.add(cmbProductGroup, 0, 1);
        
        // Label product name
        lblProductName = new Label("enter product name:");
        this.add(lblProductName, 1, 0);
        
        // textfield product name
        txtProductName = new TextField();
        this.add(txtProductName, 1, 1);
        productName = txtProductName.getText();
        
        // Button Create Product. 
        btnCreate = new Button("Opret");
        this.add(btnCreate, 1, 7);
        
        // Add Click event to button btnOpret.
        btnCreate.setOnAction(e -> this.selectedProductType.create(selectedProductGroup, productName));
        
        // build pane with current seleted product inputfields.
        buildPane();
	}
	

	/**
	 * Method to get the selected productGroup.
	 */
	public void buildPane() {
		switch(this.selectedProductGroupName) {
			case "flaske": 
				// checks if a selectedPane has been selected
				// if so we clear pane
				if (selectedPane != null) {
					clearPane();
				}
				// Creates a new instance of productbeer pane with input fields
				productBeerPane = new ProductBeer(this);
				selectedPane = this.productBeerPane;
				this.add(productBeerPane, 0, 5);
            break; 
			case "fadøl": 
				System.out.println("fadøl"); 
				clearPane();
				// Creates a new instance of productbeer pane with input fields
				productBeerPane = new ProductBeer(this);
				selectedPane = this.productBeerPane;
				this.add(productBeerPane, 0, 5);

            break; 
			case "spiritus": 
				clearPane();
				System.out.println("spiritus"); 
				// Creates a new instance of productspirit pane with input fields
				productSpiritusPane = new ProductSpirit(this);
				selectedPane = this.productSpiritusPane;
				this.add(productSpiritusPane, 0, 5);
            break;
			case "fustage":
				System.out.println("fustage"); 
				clearPane();
				// Creates a new instance of productDraughtBeerSystem pane with input fields
				productDraughtBeerSystem = new ProductDraughtBeerSystem(this);
				selectedPane = this.productDraughtBeerSystem;
				this.add(productDraughtBeerSystem, 0, 5);

			break;
			case "kulsyre":
				System.out.println("kulsyre"); 
				clearPane();
				// Creates a new instance of productGrain pane with input fields
				productGrain = new ProductGrain(this);
				selectedPane = this.productGrain;
				this.add(productGrain, 0, 5);
		    break;
			default: 
			// Default set to ProductBeer Pane
			if (selectedPane != null) {
				clearPane();
			}
            productBeerPane = new ProductBeer(this);
            selectedPane = this.productBeerPane;
			this.add(productBeerPane, 0, 5);
		}
	}
	
	/**
	 * Method to clear pane
	 */
	public void clearPane() {
		// Check witch pane was chosen last, then clears with view when user selects
		// a new productgroup 
		if (selectedPane.equals(productBeerPane)) {
			productBeerPane.delete();
		} else if (selectedPane.equals(productSpiritusPane)) {
			productSpiritusPane.delete();
		} else if (selectedPane.equals(productDraughtBeerSystem)) {
			productDraughtBeerSystem.delete();
		} else if (selectedPane.equals(productGrain)) {
			productGrain.delete();
		}
		
	}

}

