package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Order;
import model.OrderStatusType;

import java.time.LocalDateTime;

public class OrdersPane extends GridPane {

    private Controller controller;

    private ListView<Order> lstOrders;
    private Label lblError;

    public OrdersPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        controller = Controller.getController();

        this.add(new Label("Orders"), 0, 0);
        lstOrders = new ListView<>();
        lstOrders.setPrefSize(760,400);
        this.add(lstOrders, 0, 1, 2, 1);

        HBox buttonBoxLeft = new HBox();
        buttonBoxLeft.setSpacing(10);

        Button btnCreateOrder = new Button("Create order");
        btnCreateOrder.setOnAction(event -> this.createOrder());
        buttonBoxLeft.getChildren().add(btnCreateOrder);
        Button btnUpdateOrder = new Button("Update order");
        btnUpdateOrder.setOnAction(event -> updateOrder());
        buttonBoxLeft.getChildren().add(btnUpdateOrder);
        Button btnShowRented = new Button("Show still rented");
        btnShowRented.setOnAction(event -> showRented());
        buttonBoxLeft.getChildren().add(btnShowRented);
        Button btnShowAll = new Button("Show all orders");
        btnShowAll.setOnAction(event -> showAllOrders());
        buttonBoxLeft.getChildren().add(btnShowAll);

        this.add(buttonBoxLeft, 0, 2);

        HBox buttonBoxRight = new HBox();
        buttonBoxRight.setSpacing(10);
        buttonBoxRight.setAlignment(Pos.BASELINE_RIGHT);
        Button btnExport = new Button("Export DONE orders");
        btnExport.setOnAction(event -> controller.exportOrders());
        buttonBoxRight.getChildren().add(btnExport);
        this.add(buttonBoxRight, 1, 2);

        lblError = new Label("");
        lblError.setTextFill(Color.RED);
        this.add(lblError, 0, 3);

        updateContent();
    }

    private void showRented() {
        lstOrders.getItems().setAll(controller.getNotReturnedOrders());
    }

    private void showAllOrders() {
        lstOrders.getItems().setAll(controller.getOrders());
    }

    private void updateContent() {
        lstOrders.getItems().setAll(controller.getOrders());
    }

    private void createOrder() {
        Order order = controller.createOrder(LocalDateTime.now().withNano(0),
                OrderStatusType.CREATED);
        CreateUpdateOrderDialog createUpdateOrderDialog =
                new CreateUpdateOrderDialog(order, controller.getPriceLists().get(0));
        createUpdateOrderDialog.showAndWait();
        updateContent();
    }

    private void updateOrder() {
        Order order = lstOrders.getSelectionModel().getSelectedItem();
        try {
            if (order == null) {
                throw new NullPointerException("You have not chosen an order" +
                        " to update");
            }
            CreateUpdateOrderDialog createUpdateOrderDialog =
                    new CreateUpdateOrderDialog(order, controller.getPriceLists().get(0));
            createUpdateOrderDialog.showAndWait();
            updateContent();
        }
        catch (Exception e) {
            lblError.setText(e.getMessage());
        }
    }

    private void exportData() {

    }
}
