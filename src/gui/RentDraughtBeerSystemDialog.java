package gui;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RentDraughtBeerSystemDialog extends Stage {
	/**
	 * Components
	 */
	
	private Controller controller;
	private Button btnCreate;
	
	
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
	
	private void initContent(GridPane pane) {
		// grabs controller
		this.controller = Controller.getController();
	}

}
