package gui;

import java.util.ArrayList;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Order;
import model.OrderLine;
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
	private ListView<OrderLine> lvShowClipBoardStatus;
	private Button btnExport;
	private Button btnHent;
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
		
		
		// button hent
		btnHent = new Button("Hent");
		this.add(btnHent, 0, 6);
		
		// EventHandler selected date in DatePicker
		btnHent.setOnAction(e -> this.populateClipBoard());
		
		// button export
		btnExport = new Button("Export");
		this.add(btnExport, 1, 6);
		
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
		// Arraylist orders & arrayList result containing matching objects
		ArrayList<Order> orders = controller.getOrders(); 
		ArrayList<OrderLine> results = new ArrayList<OrderLine>();
		// iteration over each oder
		for (int i = 0; i < orders.size(); i++) {
			// checks to find orders within the selected date interval
			if (orders.get(i).getEndTimestamp() != null && dpFrom.getValue().compareTo(orders.get(i).getStartTimestamp().toLocalDate()) < 0 &&
					dpTill.getValue().compareTo(orders.get(i).getEndTimestamp().toLocalDate()) > 0) {
				System.out.println("kommer herind");
				// Arraylist orderlines within interval 
				ArrayList<OrderLine> orderlines = controller.getOrders().get(i).getOrderlines();
				// iteration over each oderline
				for (int j = 0; j < orderlines.size(); j++) {
					results.add(orderlines.get(j));
				}
			}
			// fills listView.
			lvShowClipBoardStatus.getItems().setAll(results);
		}
		
	}
}
