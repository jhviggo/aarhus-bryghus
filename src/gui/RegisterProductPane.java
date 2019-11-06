package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.ProductGroup;

public class RegisterProductPane extends GridPane {
	/**
	 * Components
	 */
	private ComboBox<ProductGroup> cmbProductGroup;
	private TextField txtProductName;
	private Label lblProductGroup;
	private Label lblProductName;
	private Label lblError;
	private Button btnCreate;

	/**
	 * Dynamic components.
	 */
	private ProductBeer productBeerPane;
	private ProductSpirit productSpiritusPane;
	private ProductDraughtBeerSystem productDraughtBeerSystem;
	private ProductRawMaterial productRawMaterial;
	private ProductType selectedProductType;
	private ProductGroup selectedProductGroup;
	private String selectedProductGroupName;
	private String productName;
	private Controller controller;
	private ProductType selectedPane;

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

        cmbProductGroup.setOnAction(event -> buildPane());
        selectedProductGroupName = cmbProductGroup.getSelectionModel().getSelectedItem().getType();

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
        btnCreate.setOnAction(e -> this.createSelection());

        this.lblError = new Label();
        this.add(lblError, 0, 8);

        // build pane with current seleted product inputfields.
        buildPane();
	}

	private void createSelection() {
		try {
			this.productName = txtProductName.getText();

			if (this.productName.length() < 1) {
				throw new RuntimeException("Products must have a name");
			}

			if (this.selectedProductGroupName == null) {
				throw new RuntimeException("A product group must be selected");
			}

			this.selectedPane.create(this.selectedProductGroup, this.productName);
		} catch (RuntimeException e) {
			this.lblError.setText(e.getMessage());
		}
	}


	/**
	 * Method to get the selected productGroup.
	 */
	public void buildPane() {
		this.clearPane();
		this.selectedProductGroupName = cmbProductGroup.getSelectionModel().getSelectedItem().getType();

		switch(this.selectedProductGroupName) {
			case "spiritus":
				productSpiritusPane = new ProductSpirit(this);
				selectedPane = this.productSpiritusPane;
				this.add(productSpiritusPane, 0, 5);
            break;
			case "fustage":
				productDraughtBeerSystem = new ProductDraughtBeerSystem(this);
				selectedPane = this.productDraughtBeerSystem;
				this.add(productDraughtBeerSystem, 0, 5);

			break;
			case "kulsyre":
				productRawMaterial = new ProductRawMaterial(this);
				selectedPane = this.productRawMaterial;
				this.add(productRawMaterial, 0, 5);
		    break;
			case "flaske":
			case "fadøl":
			default:
				productBeerPane = new ProductBeer(this);
				selectedPane = this.productBeerPane;
				this.add(productBeerPane, 0, 55);
		}
	}

	/**
	 * Method to clear pane
	 */
	public void clearPane() {
		if (selectedPane != null) {
			selectedPane.delete();
		}
	}

}

