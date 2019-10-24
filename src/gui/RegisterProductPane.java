package gui;

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
	
	private GridPane grid;
	private ProductBeer productBeer;

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
        // Grid Pane.
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(300, 300);
        grid.setVgap(5);
        grid.setHgap(5);
        productBeer = new ProductBeer(grid);
        
        this.add(productBeer, 0, 5, 5, 5);
        
	}

	private void add(ProductBeer productBeer2, int columnIndex, int rowIndex, int colspan, int rowspan) {
		
	}

}

