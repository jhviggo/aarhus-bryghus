package gui;

import java.time.LocalDate;
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

public class TicketPane extends GridPane {

	/**
	 * Components
	 */
	private Label lblDateFrom;
	private Label lblDateTill;
	private Label lblShowClipBoardStatus;
	private Label lblClipsInfo;
	private Label lblError;
	private DatePicker dpFrom;
	private DatePicker dpTill;
	private ListView<Order> lvShowClipBoardStatus;
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
		lblShowClipBoardStatus = new Label("Clips were sold in these Orders");
		this.add(lblShowClipBoardStatus, 0, 2);

		lblClipsInfo = new Label(
				"Clips sold today is "
						+ controller.getBoughtClipsBetweenDates(LocalDate.now(), LocalDate.now())
						+ " and "
						+ controller.getUsedClipsBetweenDates(LocalDate.now(), LocalDate.now())
						+ " order used"
		);
		this.add(lblClipsInfo, 1, 2);

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

		lblError = new Label();
		this.add(lblError, 0, 7);

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
		if (dpFrom.getValue() == null && dpTill.getValue() == null) {
			this.lblError.setText("Please select start and end date");
			return;
		}

		lblClipsInfo.setText(
		"Clips sold between selected dates is "
				+ controller.getBoughtClipsBetweenDates(dpFrom.getValue(), dpTill.getValue())
				+ " and "
				+ controller.getUsedClipsBetweenDates(dpFrom.getValue(), dpTill.getValue())
				+ " was used"
		);

		lvShowClipBoardStatus.getItems().setAll(controller.getOrdersWithSoldClips(dpFrom.getValue(), dpTill.getValue()));


	}
}
