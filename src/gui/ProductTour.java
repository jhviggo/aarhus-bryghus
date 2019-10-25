package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.PriceList;

public class ProductTour {
	
	/**
	 * Components.
	 */
	private Label lblDato;
	private Label lblHour;
	private Label lblMinut;
	private Button btnCreate;
	private DatePicker dpDato;
	private ComboBox<String> cmbHour;
	private ComboBox<String> cmbMin;
	
	/**
	 * Product Tour Constructor
	 * Constructor to create a register tour pane.
	 * @param grid
	 */
	public ProductTour(GridPane grid) {
        grid.setPadding(new Insets(20));
        
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
        cmbHour.getItems().addAll(hours);
        
        // combo box min
        cmbMin = new ComboBox<>();
        cmbMin.setPrefWidth(175);
        cmbMin.getItems().addAll(minuts);
        
        // appends each component to container. 
        container.getChildren().add(lblHour);
        container.getChildren().add(cmbHour);
        container.getChildren().add(cmbMin);
        
        
        btnCreate = new Button("Opret");
        grid.add(btnCreate, 1, 6);
        
        // click event wired with method createProductTour.
        btnCreate.setOnAction(event -> this.createProductTour());
	}
	
	/**
	 * Method to create new product tour.
	 */
	private void createProductTour() {
		
	}

}
