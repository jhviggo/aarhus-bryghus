package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.ProductGroup;

public class ProductBeer extends GridPane implements ProductType {
	/**
	 * Components
	 */
	private Label lblSize;
	private Label lblUnit;
	private Label lblAlcoholPercentage;
	private Label lblBeerType;
	private TextField txtSize;
	private TextField txtUnit;
	private TextField txtAlcoholPercentage;

	/**
	 * Product Beer Constructor
	 * Constructor to create a register beer pane.
	 * @param pane
	 */
	public ProductBeer(GridPane grid) {
        grid.setPadding(new Insets(20));
        
        // label for size textfield.
        lblSize = new Label("Enter size:");
        grid.add(lblSize, 0, 2);
        
        // input size textfield.
        txtSize = new TextField();
        grid.add(txtSize, 0, 3, 1, 1);
        
        // Label for unit textfield.
        lblUnit = new Label("Enter unit:");
        grid.add(lblUnit, 1, 2);
        
        // input unit textfield.
        txtUnit = new TextField();
        grid.add(txtUnit, 1, 3, 1, 1);
        
        // label for AlcoholPercentage textfield.
        lblAlcoholPercentage = new Label("Enter alcoholpercentage %:");
        grid.add(lblAlcoholPercentage, 0, 4);
        
        // input AlcoholPercentage textfield.
        txtAlcoholPercentage = new TextField();
        grid.add(txtAlcoholPercentage, 0, 5, 1, 1);
        
        
        // label for Beer type textfield.
        lblBeerType = new Label("choose Beer Type:");
        grid.add(lblBeerType, 1, 4);
        
	}
	
	/**
	 * Method to create new product beer.
	 */
	public void create(ProductGroup productgroup, String productName) {
		
	}
}
