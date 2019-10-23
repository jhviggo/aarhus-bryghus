package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Order;

public class InventoryExportPane extends GridPane {

    private Controller controller;

    private ListView<Order> lstOrders;

    public InventoryExportPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(true);

        controller = Controller.getController();

        this.add(new Label("Orders"), 0, 0);
        lstOrders = new ListView<>();
        lstOrders.setPrefSize(760,400);
        this.add(lstOrders, 0, 1, 1, 4);

        Button btnExport = new Button("Export to CSV");
        this.add(btnExport, 0, 5);

    }
}
