package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ProductGroup;

public class RegisterProductPane extends GridPane {
	/**
	 * Components
	 */
	private ComboBox<ProductGroup> cmbProductGroup;
	private Button btnCancel;
	private Button btnSave;
	private Button btnOpret;
	private TextField txtProductName;
	private Label lblProductGroup;
	private Label lblProductName;

	/**
	 * Pane constructor
	 * setup screen view
	 */
	public RegisterProductPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        
        lblProductGroup = new Label("choose Product group:");
        this.add(lblProductGroup, 0,0);
        
        cmbProductGroup = new ComboBox<>();
        cmbProductGroup.setPrefWidth(400);
        this.add(cmbProductGroup, 0, 1);
        
        lblProductName = new Label("enter product name:");
        this.add(lblProductName, 1, 0);
        
        txtProductName = new TextField();
        this.add(txtProductName, 1, 1);
        
        btnOpret = new Button("Opret");
        this.add(btnOpret, 1, 5);
        
        
        
       // Add Click event to button btnOpret.
       btnOpret.setOnAction(event -> this.openCreateProductDialog());
	}
	
	/**
	 * Action EventHandler method
	 * method opens create product dialog, with components based on selected
	 * product group.
	 * @param ProductName, ProductGroup
	 */
	private void openCreateProductDialog() {
		
	}

}

