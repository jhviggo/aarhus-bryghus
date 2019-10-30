package gui;

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
	private Label lblStartMinLeft;
	private Label lblStartHourLeft;
	private Label lblStartMinRight;
	private Label lblStartHourRight;
	private Label lblNumOfTaps;
	private ComboBox<ProductGroup> cmbProductGroups;
	private ComboBox<String>cmbHoursLeft, cmbMinsLeft;
	private ComboBox<String> cmbHoursRight, cmbMinsRight;
	private TextField txtProductName;
	private TextField txtNumOfTaps;
	private TextField txtHour;
	private TextField txtMin;
	private DatePicker dpStartDate, dpEndDate;
	private Button btnCreate;
	
	private ProductGroup selectedProductGroup;
	// variables containing values for date interval.
	private LocalDateTime selectedStartDate;
	private LocalDateTime selectedEndDate;
	
	/**
	 * Constructor for dialog pane
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
		
		String[] hours = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"
				,"16","17","18","19","20","21","22","23","23"};
		String[] minuts = {"0","15","30","45"};
		
		
		// label product name
		lblProductName = new Label("enter product name:");
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
		
		// boxes containing hour and min selection
		HBox left = new HBox();
		HBox right = new HBox();
		grid.add(left, 0, 4, 2, 2);
		grid.add(right, 1, 4, 2, 2);
		
		// label select start hour
		lblStartHourLeft = new Label("Hours:");
		grid.add(lblStartHourLeft, 0, 4);
		
		// hours options 
		cmbHoursLeft = new ComboBox<>();
		cmbHoursLeft.setPrefWidth(30);
		cmbHoursLeft.getItems().addAll(hours);
		grid.add(cmbHoursLeft, 0, 5);
	
		// label select start minuts
		lblStartMinLeft = new Label("Minuts:");
		grid.add(lblStartMinLeft, 0, 4);
		
		// minut options
		cmbMinsLeft = new ComboBox<>();
		cmbMinsLeft.setPrefWidth(30);
		cmbMinsLeft.getItems().addAll(minuts);
		grid.add(cmbMinsLeft, 0, 5);
		
		left.getChildren().add(lblStartHourLeft);
		left.getChildren().add(cmbHoursLeft);
		left.getChildren().add(lblStartMinLeft);
		left.getChildren().add(cmbMinsLeft);

		// label select start hour
		lblStartHourRight = new Label("Hours:");
		grid.add(lblStartHourRight, 0, 6);
	
		// hours options
		cmbHoursRight = new ComboBox<>();
		cmbHoursRight.setPrefWidth(30);
		cmbHoursRight.getItems().addAll(hours);
		grid.add(cmbHoursRight, 0, 8);
		
		// label select start minuts
		lblStartMinRight = new Label("Minuts:");
		grid.add(lblStartMinRight, 0, 6);
		
		// minut options
		cmbMinsRight = new ComboBox<>();
		cmbMinsLeft.setPrefWidth(30);
		cmbMinsRight.getItems().addAll(minuts);
		grid.add(cmbMinsRight, 0, 8);
		
		// feeds right container with children .. XD
		right.getChildren().add(lblStartHourRight);
		right.getChildren().add(cmbHoursRight);
		right.getChildren().add(lblStartMinRight);
		right.getChildren().add(cmbMinsRight);
		
		// label number of taps
		lblNumOfTaps = new Label("Enter number of taps:");
		grid.add(lblNumOfTaps, 0, 8,1,1);
		
		// textfield number of taps
		txtNumOfTaps = new TextField();
		grid.add(txtNumOfTaps, 0, 9, 1, 1);
		
		// create button
		btnCreate = new Button("Create");
		btnCreate.setPrefWidth(125);
		grid.add(btnCreate, 1, 9);
		
		
		// attaches click event on create button
		btnCreate.setOnAction(e -> this.createRentDraughtBeerSystem());
	}

	
	/**
	 * Method to create a new rent of draught beer system
	 */
	private void createRentDraughtBeerSystem() {
		// integer containing textfield value
		int amountOfTaps = 0;
		// String to hold productName entered value
		String productName = "";
	
		
		// timestamps for each date
		LocalTime selectedHBoxLeft;
		LocalTime selectedHBoxRight;
		
		
		// checks if the received data is correct format
		try {
			// grabs selected product group
			selectedProductGroup = cmbProductGroups.getSelectionModel().getSelectedItem();
			
			amountOfTaps = Integer.parseInt(txtNumOfTaps.getText().trim());

			// Time value from Hbox left.
			selectedHBoxLeft = LocalTime.of(Integer.parseInt(cmbHoursLeft.getSelectionModel().getSelectedItem().trim()),
					Integer.parseInt(cmbMinsLeft.getSelectionModel().getSelectedItem().trim()));
			
			// Time value from Hbox right.
			selectedHBoxRight = LocalTime.of(Integer.parseInt(cmbHoursRight.getSelectionModel().getSelectedItem().trim()),
					Integer.parseInt(cmbMinsRight.getSelectionModel().getSelectedItem().trim()));
			
			// checks if user selected dates
			if (dpStartDate.getValue() != null && dpEndDate.getValue() != null) {
				
				// selected startDate value
				selectedStartDate = LocalDateTime.of(dpStartDate.getValue().getYear(), 
						dpStartDate.getValue().getMonth(), dpStartDate.getValue().getDayOfMonth(),
						selectedHBoxLeft.getHour(), selectedHBoxLeft.getMinute());
				
				// selected endDate value
				selectedEndDate = LocalDateTime.of(dpEndDate.getValue().getYear(), dpEndDate.getValue().getMonth(),
						dpEndDate.getValue().getDayOfMonth(), selectedHBoxRight.getHour(), selectedHBoxRight.getMinute());
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

}
