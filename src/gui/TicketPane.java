package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Product;

public class TicketPane extends GridPane {

	/**
	 * Components
	 */
	private Label lblDateFrom;
	private Label lblDateTill;
	private Label lblShowClipBoardStatus;
	private DatePicker dpFrom;
	private DatePicker dpTill;
	private ListView<Product> lvShowClipBoardStatus;
	private Button btnExport;
	private Controller controller;
	
	/**
	 * Ticket pane constructor
	 */
	public TicketPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        
		// grabs controller
		this.controller = Controller.getController();
		
		// label for from date
		lblDateFrom = new Label("select from date:");
		this.add(lblDateFrom, 0, 0);
		
		// datePicker select from date
		dpFrom = new DatePicker();
		this.add(dpFrom, 0, 1);
		
		// label till date
		lblDateTill = new Label("select till date:");
		this.add(lblDateTill, 1, 0);
		
		// datePicker select till date
		dpTill = new DatePicker();
		this.add(dpTill, 1, 1);
		
		// label for clipboard status
		lblShowClipBoardStatus = new Label("ClipBoard status");
		this.add(lblShowClipBoardStatus, 0, 2);
		
		// listView clipboard status
		lvShowClipBoardStatus = new ListView<>();
		this.add(lvShowClipBoardStatus, 0, 3, 2, 2);
		lvShowClipBoardStatus.setPrefSize(400,400);
	//	lvShowClipBoardStatus.getItems().setAll();
		
		// button export
		btnExport = new Button("Export");
		this.add(btnExport, 0, 6);
		
		// on click event handler
		btnExport.setOnAction(e -> this.exportClipBoard());
	}
	
	/**
	 * Method to handle onClick event.
	 */
	public void exportClipBoard() {
		
	}
	
	/**
	 * populate ClipBoard with clipboard status
	 */
	public void populateClipBoard() {
		
	}
}
