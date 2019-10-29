package gui;

import controller.Controller;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Order;
import model.OrderStatusType;
import model.PaymentMethod;

public class CheckoutOrderDialog extends Stage {

    private Controller controller;
    private Button btnCheckout;
    private TextField txfOverridePrice;
    private CheckBox chbOverride;
    private RadioButton rbCreditCard, rbCash, rbPayLater, rbCreated, rbProgress,
                        rbDone;
    private Order order;
    private DatePicker startDate, endDate;
    private TextField startTime, endTime;
    private ToggleGroup paymentGroup, statusGroup;

    public CheckoutOrderDialog(Order order) {
        controller = Controller.getController();
        this.order = order;

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Order checkout");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.initContent(pane);
        this.setScene(scene);
        this.updateControls();
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setGridLinesVisible(true);

        pane.add(new Label("Choose payment option"), 0,0);

        paymentGroup = new ToggleGroup();
        VBox paymentBox = new VBox();
        paymentBox.setSpacing(10);

        rbCreditCard = new RadioButton("Credit card");
        rbCreditCard.setUserData(PaymentMethod.CREDITCARD);
        rbCreditCard.setToggleGroup(paymentGroup);
        rbCash = new RadioButton("Cash");
        rbCash.setUserData(PaymentMethod.CASH);
        rbCash.setToggleGroup(paymentGroup);
        rbPayLater = new RadioButton("Pay later");
        rbPayLater.setUserData(PaymentMethod.PAYLATER);
        rbPayLater.setToggleGroup(paymentGroup);

        paymentBox.getChildren().add(rbCreditCard);
        paymentBox.getChildren().add(rbCash);
        paymentBox.getChildren().add(rbPayLater);

        pane.add(paymentBox, 0, 1);

        pane.add(new Label("Choose order status"), 0, 2);

        statusGroup = new ToggleGroup();
        VBox statusBox = new VBox();
        statusBox.setSpacing(10);

        rbCreated = new RadioButton("Created");
        rbCreated.setUserData(OrderStatusType.CREATED);
        rbCreated.setToggleGroup(statusGroup);
        rbProgress = new RadioButton("In progress");
        rbProgress.setUserData(OrderStatusType.PROGRESS);
        rbProgress.setToggleGroup(statusGroup);
        rbDone = new RadioButton("Done");
        rbDone.setUserData(OrderStatusType.DONE);
        rbDone.setToggleGroup(statusGroup);

        statusBox.getChildren().add(rbCreated);
        statusBox.getChildren().add(rbProgress);
        statusBox.getChildren().add(rbDone);

        pane.add(statusBox, 0, 3);

        chbOverride = new CheckBox("Override Price");
        pane.add(chbOverride, 0, 4);

        txfOverridePrice = new TextField();
        pane.add(txfOverridePrice, 1, 4);

        pane.add(new Label("Start date and time"), 0, 5);
        startDate = new DatePicker(order.getStartTimestamp().toLocalDate());
        pane.add(startDate, 0, 6);
        startTime = new TextField(order.getStartTimestamp().toLocalTime().toString());
        pane.add(startTime, 1, 6);

        btnCheckout = new Button("Checkout order");
        btnCheckout.setOnAction(event -> updateOrder());
        pane.add(btnCheckout, 0, 10, 2, 1);

    }

    private void updateControls() {
        rbPayLater.setSelected(true);
        if (order.getPaymentMethod() != null) {
            switch (order.getPaymentMethod()) {
                case CREDITCARD:
                    rbCreditCard.setSelected(true);
                    break;
                case CASH:
                    rbCash.setSelected(true);
                    break;
            }
        }
        switch(order.getStatus()) {
            case CREATED: rbCreated.setSelected(true); break;
            case PROGRESS: rbProgress.setSelected(true); break;
            case DONE: rbDone.setSelected(true); break;
        }
        if (order.getPriceOverride() != -1) {
            chbOverride.setSelected(true);
            txfOverridePrice.setText("" + order.getPriceOverride());
        }
    }

    private void updateOrder() {
        try {
            OrderStatusType status =
                    (OrderStatusType) statusGroup.getSelectedToggle().getUserData();
            PaymentMethod paymentMethod =
                    (PaymentMethod) paymentGroup.getSelectedToggle().getUserData();
            if (paymentMethod == PaymentMethod.PAYLATER &&
                    status == OrderStatusType.DONE) {
                throw new IllegalStateException("Order can't be both done and pay later");
            }
            LocalDateTime startDateTime = LocalDateTime.of(startDate.getValue(),
                    LocalTime.parse(startTime.getText()));
            controller.updateOrder(startDateTime,
                    status,
                    paymentMethod,
                    order);
            if (status == OrderStatusType.DONE) {
                controller.setEndTimestampOnOrder(order);
            }
            if (chbOverride.isSelected()) {
                double priceOverride =
                        Double.parseDouble(txfOverridePrice.getText());
                if (priceOverride >= 0) {
                    controller.setPriceOverrideOnOrder(priceOverride, order);
                } else {
                    throw new IllegalArgumentException(
                            "You cannot set a negative price override"
                    );
                }
            } else {
                controller.setPriceOverrideOnOrder(-1, order);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }
        this.hide();
    }

}
