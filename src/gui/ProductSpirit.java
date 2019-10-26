package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ProductGroup;

public class ProductSpirit extends GridPane implements ProductType {

	private Label lblSize;
	private Label lblUnit;
	private Label lblAlcoholPercentage;
	private Label lblType;
	private TextField txtSize;
	private TextField txtUnit;
	private TextField txtAlcoholPercentage;
	private Controller controller;
	
	public ProductSpirit(GridPane grid) {
		grid.setPadding(new Insets(20));
        // grabs controller
        controller = Controller.getController();
        
        // label size input.
        lblSize = new Label("enter size:");
        grid.add(lblSize, 0, 2);
        
        // textfield size
        txtSize = new TextField();
        grid.add(txtSize, 0, 3, 1, 1);

        // label unit input
        lblUnit = new Label("enter unit:");
        grid.add(lblUnit, 1, 3);
        
        // textfield unit
        txtUnit = new TextField();
        grid.add(txtUnit, 1, 4);
        
        // label alcoholpercentage
        lblAlcoholPercentage = new Label("enter alcoholpercentage:");
        grid.add(lblAlcoholPercentage, 0, 4);
       
        // textfield alcoholpercentage
        txtAlcoholPercentage = new TextField();
        grid.add(txtAlcoholPercentage, 0, 5, 1, 1);
        
        
	}
	
	/**
	 * Method to create an spirit product.
	 * @param ProductGroup productgroup, String productName
	 */
	public void create(ProductGroup productgroup, String productName) {
		try {
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException err) {
			System.out.println(err.getMessage());
		} finally {
			
		}
		
	}
	
}
