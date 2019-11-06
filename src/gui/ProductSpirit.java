package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Product;
import model.ProductGroup;

public class ProductSpirit extends GridPane implements ProductType {

	/**
	 * Components
	 */
	private Label lblSize;
	private Label lblUnit;
	private Label lblAlcoholPercentage;
	private Label lblType;
	private TextField txtSize;
	private TextField txtType;
	private TextField txtUnit;
	private TextField txtAlcoholPercentage;
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
        controller = Controller.getController();

        // label size input.
        lblSize = new Label("enter size:");
        grid.add(lblSize, 0, 2);

        // textfield size
        txtSize = new TextField();
        grid.add(txtSize, 0, 3);

        // label unit input
        lblUnit = new Label("enter unit:");
        grid.add(lblUnit, 1, 2);

        // textfield unit
        txtUnit = new TextField();
        grid.add(txtUnit, 1, 3);

        // label alcoholpercentage
        lblAlcoholPercentage = new Label("enter alcoholpercentage:");
        grid.add(lblAlcoholPercentage, 0, 4);

        // textfield alcoholpercentage
        txtAlcoholPercentage = new TextField();
        grid.add(txtAlcoholPercentage, 0, 6);

        // label type input field
        lblType = new Label("Choose type:");
        grid.add(lblType, 1, 4);

		txtType = new TextField();
		grid.add(txtType, 1, 6);
	}

	/**
	 * Method to create an spirit product.
	 * @param productgroup
	 * @param productName
	 */
	public void create(ProductGroup productgroup, String productName) {
		int size;
		String unit = "";
		double alcoholPercentage;

		try {
			// checks if the input value size is a valid Integer
			size = Integer.parseInt(txtSize.getText().trim());

			// checks if a unit has been entered in the input field.
			if (txtUnit.getText().length() < 1) {
				unit = txtUnit.getText().trim();
			}

			if (txtType.getText().length() == 0) {
				throw new RuntimeException("Please enter a spirit type");
			}

			// checks if the input value alcoholpercentage is a valid double
			alcoholPercentage = Double.parseDouble(txtAlcoholPercentage.getText().trim());

			Product p = this.controller.createProductSpirit(productName, productgroup, size, unit, alcoholPercentage, selectedType);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException err) {
			System.out.println(err.getMessage());
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to delete all components, used to cleanse pane.
	 */
	public void delete() {
		// deleting all elements from grid.
		grid.getChildren().removeAll(
				lblSize, lblUnit,lblAlcoholPercentage, lblType,
				txtSize, txtUnit, txtAlcoholPercentage
				);
	}
}
