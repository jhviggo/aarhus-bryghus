package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.BeerType;
import model.ProductGroup;

public class ProductBeer extends GridPane implements ProductType {
	/**
	 * Components
	 *
	 * lblSize;
	   lblUnit;
	   lblAlcoholPercentage;
	   lblBeerType;
	   lblType;
	 */
	private Label lblSize;
	private Label lblUnit;
	private Label lblAlcoholPercentage;
	private Label lblBeerType;
	private Label lblType;
	private TextField txtSize;
	private TextField txtUnit;
	private TextField txtAlcoholPercentage;
	private TextField txtType;
	private ComboBox<BeerType> cmbBeerType;
	private GridPane grid;
	private Controller controller;

	private BeerType selectedBeerType;

	/**
	 * Product Beer Constructor
	 * Constructor to create a register beer pane.
	 * @param pane
	 */
	public ProductBeer(GridPane grid) {
        grid.setPadding(new Insets(20));
        this.grid = grid;
        // grabs our controller
        this.controller = Controller.getController();

        // label for size textfield
        lblSize = new Label("Enter size:");
        grid.add(lblSize, 0, 2);

        // input size textfield
        txtSize = new TextField();
        grid.add(txtSize, 0, 3);

        // Label for unit textfield
        lblUnit = new Label("Enter unit:");
        grid.add(lblUnit, 1, 2);

        // input unit textfield
        txtUnit = new TextField();
        grid.add(txtUnit, 1, 3);

        // label for AlcoholPercentage textfield
        lblAlcoholPercentage = new Label("Enter alcoholpercentage %:");
        grid.add(lblAlcoholPercentage, 0, 4);

        // input AlcoholPercentage textfield
        txtAlcoholPercentage = new TextField();
        grid.add(txtAlcoholPercentage, 0, 5);

        // label for Beer type textfield
        lblBeerType = new Label("choose Beer Type:");
        grid.add(lblBeerType, 1, 4);

        // combo box hour
        cmbBeerType = new ComboBox<>();
        cmbBeerType.setPrefWidth(175);
        cmbBeerType.getItems().addAll(BeerType.values());
        cmbBeerType.getSelectionModel().selectFirst();
        selectedBeerType = cmbBeerType.getSelectionModel().getSelectedItem();
        grid.add(cmbBeerType, 1, 5);

        // label for type textfield
        lblType = new Label("enter a type:");
        grid.add(lblType, 0, 6);

        // type textfield
        txtType = new TextField();
        grid.add(txtType, 0, 7);
	}

	/**
	 * Method to create new product beer.
	 */
	public void create(ProductGroup productgroup, String productName) {
		int size = 0;
		double alcoholPercentage = 0;
		String unit = "";
		String type = "";

		try {
			// checks if size is a valid number
			size = Integer.parseInt(txtSize.getText().trim());

			// Checks if an unit has been entered.
			if (txtUnit.getText().length() >= 1) {
				unit = txtUnit.getText().trim();
			}

			// Checks if the alcohoPercentage is a valid decimal number
			alcoholPercentage = Double.parseDouble(txtAlcoholPercentage.getText().trim());

			// Checks if a type was entered
			if (txtType.getText().length() >= 1) {
				type = txtType.getText().trim();
			}

			selectedBeerType = cmbBeerType.getSelectionModel().getSelectedItem();

		} catch(NumberFormatException error) {
			System.out.println(error.getMessage() + " input fields must contain a real number");
		} catch(NullPointerException err) {
			System.out.println(err.getMessage());
		} finally {
			this.controller.createProductBeer(productName, productgroup, size, unit, alcoholPercentage, type, selectedBeerType);
		}
	}

	/**
	 * Method to delete all components, used to cleanse pane.
	 */
	public void delete() {
		// deleting all elements from grid.
		grid.getChildren().removeAll(
				lblSize, lblUnit, lblAlcoholPercentage,
				lblBeerType, lblType, txtSize, txtUnit,
				txtAlcoholPercentage, cmbBeerType, txtType
				);
	}
}
