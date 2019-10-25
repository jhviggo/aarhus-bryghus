package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	private Button btnOpret;
	
	private GridPane grid;
	private ProductBeer productBeer;
	private ProductType selectedProductType;
	private ProductGroup selectedProductGroup;
	private String productName;
	private Controller controller;

	/**
	 * Pane constructor
	 * setup screen view
	 */
	public RegisterProductPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        
        this.controller = Controller.getController();
       
        lblProductGroup = new Label("choose Product group:");
        this.add(lblProductGroup, 0,0);
        
        cmbProductGroup = new ComboBox<>();
        cmbProductGroup.setPrefWidth(400);
        cmbProductGroup.getItems().setAll(controller.getProductGroups());
        System.out.println(controller.getProductGroups());
        this.add(cmbProductGroup, 0, 1);
        
        lblProductName = new Label("enter product name:");
        this.add(lblProductName, 1, 0);
        
        txtProductName = new TextField();
        this.add(txtProductName, 1, 1);

        productBeer = new ProductBeer(this);
        
        this.add(productBeer, 0, 5, 5, 5);
        
        btnOpret = new Button("Opret");
        this.add(btnOpret, 1, 7);
      //  selectedProductGroup
        
       // Add Click event to button btnOpret.
       btnOpret.setOnAction(event -> this.selectedProductType.create(selectedProductGroup, productName));
        
	}

	

}

