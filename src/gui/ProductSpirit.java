package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Product;
import model.ProductGroup;
import model.Spirit;

public class ProductSpirit extends GridPane implements ProductType {

	/**
	 * Components
	 */
	private Label lblSize;
	private Label lblUnit;
	private Label lblAlcoholPercentage;
	private Label lblType;
	private TextField txtSize;
	private TextField txtUnit;
	private TextField txtAlcoholPercentage;
	private ComboBox<String> cmbType;
	private String selectedType;
	private Controller controller;
	private GridPane grid;
	
	/**
	 * Constructor to create a ProductSpirit pane.
	 * @param grid
	 */
	public ProductSpirit(GridPane grid) {
		grid.setPadding(new Insets(20));
		this.grid = grid;
        // grabs controller
        controller = Controller.getController();
        // Array containing different types of spirit
        String[] types = {""};
        
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
        
        // label type input field
        lblType = new Label("Choose type:");
        grid.add(lblType, 1, 4);
        
        // feeds the comboBox with array types
        cmbType = new ComboBox<>();
        cmbType.setPrefWidth(175);
        cmbType.getItems().addAll(types);
        grid.add(cmbType, 1, 5, 1, 1);
        
        // grabs the selectedValue from cmbType
        selectedType = cmbType.getSelectionModel().getSelectedItem();
	}
	
	/**
	 * Method to create an spirit product.
	 * @param ProductGroup productgroup, String productName
	 */
	public void create(ProductGroup productgroup, String productName) {
		int size = 0;
		String unit = "";
		double alcoholPercentage = 0;
		
		try {
			// checks if the input value size is a valid Integer
			size = Integer.parseInt(txtSize.getText().trim());
			
			// checks if a unit has been entered in the input field.
			if (txtUnit.getText().length() < 1) {
				unit = txtUnit.getText().trim();
			}
			
			// checks if the input value alcoholpercentage is a valid double
			alcoholPercentage = Double.parseDouble(txtAlcoholPercentage.getText().trim());
			
			// checks if cmbType has changed value
			if (cmbType.hasProperties()) {
				selectedType.trim();
				System.out.println("value has changed" + selectedType.trim());
			} else {
				throw new NullPointerException("Du skal vælge en type");
			}
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException err) {
			System.out.println(err.getMessage());
		} finally {
			// calls controller method to create product spirit
			this.controller.createProductSpirit(productName, productgroup, size, unit, alcoholPercentage, selectedType);
		}
		
	}

	/**
	 * Method to delete all components, used to cleanse pane.
	 */
	public void delete() {
		// deleting all elements from grid.
		grid.getChildren().removeAll(
				lblSize, lblUnit,lblAlcoholPercentage, lblType,
				txtSize, txtUnit, txtAlcoholPercentage, cmbType
				);
	}
}
