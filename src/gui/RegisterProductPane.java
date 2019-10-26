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
	private Button btnCreate;
	
	private GridPane grid;
	private ProductBeer productBeer;
	private ProductType selectedProductType;
	private ProductGroup selectedProductGroup;
	private String selectedProductGroupName;
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
        btnCreate.setOnAction(event -> this.selectedProductType.create(selectedProductGroup, productName));
	}

	/**
	 * Method to get the selected productGroup.
	 */
	public void openDynamicPane() {
		switch(this.selectedProductGroupName) {
			case "flaske": 
				System.out.println("flaske"); 
            break; 
			case "fadøl": 
				System.out.println("fadøl"); 
            break; 
			case "spiritus": 
				System.out.println("spiritus"); 
            break;
			case "fustage":
				System.out.println("fustage"); 
			break;
			case "kulsyre":
				System.out.println("kulsyre"); 
		    break;
			default: 
            System.out.println("no match"); 
		}
		 
		 this.add(productBeer, 0, 5, 5, 5);
	}
	

}

