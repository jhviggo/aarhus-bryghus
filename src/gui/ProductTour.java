package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProductTour {
	
	/**
	 * Components.
	 */
	private Label lblDato;
	private Label lblHour;
	private Label lblMinut;
	private Button btnCreate;
	private DatePicker dpDato;
	private TextField txtHour;
	private TextField txtMin;
	
	/**
	 * Product Tour Constructor
	 * Constructor to create a register tour pane.
	 * @param grid
	 */
	public ProductTour(GridPane grid) {
        grid.setPadding(new Insets(20));
        
        // Label for DatePicker.
        lblDato = new Label("Choose date:");
        grid.add(lblDato, 0, 0);
        
        // DatePicker added to product tour grid.
        dpDato = new DatePicker();
        grid.add(dpDato, 0, 1);
        
        
        
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
