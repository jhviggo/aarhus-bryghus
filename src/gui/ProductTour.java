package gui;

import java.time.LocalDateTime;
import java.time.LocalTime;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.ProductGroup;

public class ProductTour extends GridPane implements ProductType {
	
	/**
	 * Components.
	 */
	private Label lblDato;
	private Label lblHour;
	private Label lblMinut;
	private Label lblDuration;
	private DatePicker dpDato;
	private ComboBox<String> cmbHour;
	private ComboBox<String> cmbMin;
	private TextField txtDuration;
	private Controller controller;
	private GridPane grid;
	
	/**3
	 * Product Tour Constructor
	 * Constructor to create a register tour pane.
	 * @param grid
	 */
	public ProductTour(GridPane grid) {
        grid.setPadding(new Insets(20));
        this.grid = grid;
        
        // grabs our controller
        this.controller = Controller.getController();
        
        // Arrays for hours and minuts.
        String[] hours = {"00", "01", "02","03","04","05","06","07","08","09","10","11",
                          "12","13","14","15","16","17","18","19","20","21","22","23"};
        String[] minuts = {"00","15","30","45"};
        
        
        // Label for DatePicker.
        lblDato = new Label("Choose date:");
        grid.add(lblDato, 0, 0);
        
        // DatePicker added to product tour grid.
        dpDato = new DatePicker();
        grid.add(dpDato, 0, 1);
        
        // creates a horizontal box used as container,
        // for time selection.
        HBox container = new HBox();
        grid.add(container, 0, 2, 3, 4);
        
        // Label for timestamp
        lblHour = new Label("Choose start hour:");
        
        // combo box hour
        cmbHour = new ComboBox<>();
        cmbHour.setPrefWidth(175);
        cmbHour.getSelectionModel().selectFirst();
        cmbHour.getItems().addAll(hours);
        
        lblMinut = new Label("Choose start minut:");
        
        // combo box min
        cmbMin = new ComboBox<>();
        cmbMin.setPrefWidth(175);
        cmbMin.getSelectionModel().selectFirst();
        cmbMin.getItems().addAll(minuts);
        
        // appends each component to container. 
        container.getChildren().add(lblHour);
        container.getChildren().add(cmbHour);
        container.getChildren().add(lblMinut);
        container.getChildren().add(cmbMin);
        
        // label for duration textfield
        lblDuration = new Label("enter full duration");
        grid.add(lblDuration, 0, 2);
        
        // textfield duration
        txtDuration = new TextField();
        grid.add(txtDuration, 0, 3, 1, 1);
        
    }
	
	/**
	 * Method to create new product tour.
	 */
	@Override
	public void create(ProductGroup productgroup, String productName) {
		LocalDateTime selectedDateTime = null;
		int hour = 0;
		int min = 0;
		double duration = 0;
		
		try {
			// checks if duration is a valid decimal
			duration = Double.parseDouble(txtDuration.getText().trim());
			
			// checks if hour and min selected is a real number
			hour = Integer.parseInt(cmbHour.getSelectionModel().getSelectedItem());
			min = Integer.parseInt(cmbMin.getSelectionModel().getSelectedItem());
			// creates a time object with hour and min
			LocalTime time = LocalTime.of(hour, min);
			
			// creates a LocalDateTime with entered values
			selectedDateTime = LocalDateTime.of(dpDato.getValue(), time);
			
		} catch(NumberFormatException e) {
			System.out.println("the duration must be valid number");
		} catch (NullPointerException err) {
			System.out.println("please select a date and enter a valid duration");
		} finally {
			this.controller.createProductGuidedTour(productName, productgroup, selectedDateTime, duration);
		}
		
	}
	
	/**
	 * Method to delete all components, used to cleanse pane.
	 */
	public void delete() {
		// deleting all elements from grid.
		grid.getChildren().removeAll(
				lblDato, lblHour, lblHour,lblMinut, lblDuration,
				dpDato, cmbHour, cmbMin, txtDuration
				);
	}
}
