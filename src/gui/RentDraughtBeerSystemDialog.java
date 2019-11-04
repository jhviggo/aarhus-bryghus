package gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DraughtBeerSystem;
import model.Product;
import model.ProductGroup;

public class RentDraughtBeerSystemDialog extends Stage {
	/**
	 * Components numberOfTaps
	 */
	private Controller controller;
	private Label lblProductName;
	private Label lblProductGroup;
	private Label lblStartDate;
	private Label lblEndDate;
	private Label lblNumOfTaps;
	private ComboBox<ProductGroup> cmbProductGroups;
	private ComboBox<String>cmbHoursLeft, cmbMinsLeft;
	private ComboBox<String> cmbHoursRight, cmbMinsRight;
	private TextField txtProductName;
	private TextField txtNumOfTaps;
	private TextField txtHour;
	private TextField txtMin;
	private DatePicker dpStartDate, dpEndDate;
	
	// Constructor with no Arguments.
	private Button btnCreate;
	private ProductGroup selectedProductGroup;
	// variables containing values for date interval.
	private LocalDate selectedStartDate;
	private LocalDate selectedEndDate;
	
	
	// constructor with Arguments.
	private Button btnSave;
	private DraughtBeerSystem selectedDraughtBeerSystem;
	
	/**
	 * Constructor Create RentDraughtBeerySystem dialog pane
	 */
	public RentDraughtBeerSystemDialog() {
        controller = Controller.getController();
       
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Rent draughtbeersystem");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.initContent(pane);
        this.setScene(scene);
	}
	
	/**
	 * Constructor Update RentDraughtBeerySystem dialog pane
	 */
	public RentDraughtBeerSystemDialog(DraughtBeerSystem product) {
		// grabs controller
        controller = Controller.getController();
        
        // stores product
        selectedDraughtBeerSystem = product;

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Rent draughtbeersystem");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.initContent(pane);
        this.setScene(scene);
	}
	
	
	/**
	 * Method to create content inside dialog
	 * @param pane
	 */
	private void initContent(GridPane grid) {
		grid.setPadding(new Insets(20));
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setGridLinesVisible(false);
		grid.setMinHeight(400);
		grid.setMinWidth(400);

		// grabs controller
		this.controller = Controller.getController();
		
		// label product name
		lblProductName = new Label("Enter product name:");
		grid.add(lblProductName, 0, 0);
		
		// text field product name
		txtProductName = new TextField();
		grid.add(txtProductName, 0, 1);
		
		// label product group
		lblProductGroup = new Label("Choose productgroup:");
		grid.add(lblProductGroup, 1, 0);
		
		// ComboBox containing product groups
		cmbProductGroups = new ComboBox<>();
		cmbProductGroups.setPrefWidth(175);
		cmbProductGroups.getItems().setAll(controller.getProductGroups());
		grid.add(cmbProductGroups, 1, 1, 1, 1);
		
		// label start date
		lblStartDate = new Label("Select start rent date:"); 
		grid.add(lblStartDate, 0, 2);
		
		// label end date
		lblEndDate = new Label("Select end date:");
		grid.add(lblEndDate, 1, 2);
		
		// date picker start date
		dpStartDate = new DatePicker();
		grid.add(dpStartDate, 0, 3);
		
		// date picker end date
		dpEndDate = new DatePicker();
		grid.add(dpEndDate, 1, 3);
		
		// label number of taps
		lblNumOfTaps = new Label("Enter number of taps:");
		grid.add(lblNumOfTaps, 0, 4,1,1);
		
		// textfield number of taps
		txtNumOfTaps = new TextField();
		grid.add(txtNumOfTaps, 0, 5, 1, 1);
		
		
		// checks if a product was given as argument in constructor
		if (selectedDraughtBeerSystem != null) {
			// save button
			btnSave = new Button("Save");
			btnSave.setPrefWidth(170);
			grid.add(btnSave, 1, 5);
			// attaches click event to save new changes in selected value
			btnSave.setOnAction(e -> this.updateSelectedRentDraughtBeerSystem());
		} 
		else {
			// create button
			btnCreate = new Button("Create");
			btnCreate.setPrefWidth(170);
			grid.add(btnCreate, 1, 5);
			
			
			// attaches click event on create button
			btnCreate.setOnAction(e -> this.createRentDraughtBeerSystem());
		}
	}
	
	
	
	/**
	 * Method to create a new rent of draught beer system
	 */
	private void createRentDraughtBeerSystem() {
		// integer containing text field value
		int amountOfTaps = 0;
		// String to hold productName entered value
		String productName = "";
		
		// checks if the received data is correct format
		try {
			// grabs selected product group
			selectedProductGroup = cmbProductGroups.getSelectionModel().getSelectedItem();
			
			amountOfTaps = Integer.parseInt(txtNumOfTaps.getText().trim());
			
			// checks if user selected dates
			if (dpStartDate.getValue() != null && dpEndDate.getValue() != null) {
				
				// selected startDate value
				selectedStartDate = LocalDate.of(dpStartDate.getValue().getYear(), 
						dpStartDate.getValue().getMonth(), dpStartDate.getValue().getDayOfMonth());
				
				// selected endDate value
				selectedEndDate = LocalDate.of(dpEndDate.getValue().getYear(), dpEndDate.getValue().getMonth(),
						dpEndDate.getValue().getDayOfMonth());
			}
			
			
		} catch (NullPointerException err) {
			System.out.println(err + "missing value");
		} catch (NumberFormatException error) {
			System.out.println(error + "missing value");
		} finally {
			controller.createProductDraughtBeerSystem(productName, selectedProductGroup, amountOfTaps, selectedStartDate, selectedEndDate);
		}
		// closes the dialog widget
		this.hide();
	}
	
	/**
	 * Method to setValue in each dialog widget element 
	 * with the selected product, values in each component
	 */
	private void updateSelectedRentDraughtBeerSystem() {
		txtProductName.setText(selectedDraughtBeerSystem.getProductName());
		cmbProductGroups.setValue(selectedDraughtBeerSystem.getProductGroup());
		
		dpStartDate.setValue(selectedDraughtBeerSystem.getStartDate());
		dpEndDate.setValue(selectedDraughtBeerSystem.getEndDate());
		
		txtNumOfTaps.setText(" " + selectedDraughtBeerSystem.getNumberOfTaps());
		
	}

}
