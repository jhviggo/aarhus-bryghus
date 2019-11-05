package gui;

import controller.Controller;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import model.*;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateUpdateOrderDialog extends Stage {
    private Controller controller;
    private Order order;
    private PriceList prefPriceList;

    private double prefWidth, prefHeight, prefWidthSmall, prefHeightSmall;

    private ComboBox<PriceList> cmbPriceLists;
    private ListView<Product> lstProducts;
    private ListView<OrderLine> lstOrderLines;
    private TextField txfAmount, txfOverridePrice;
    private Label lblTotal, lblError;
    private RadioButton rbCreated, rbProgress, rbDone, rbCreditCard, rbCash, rbPayLater;
    private ToggleGroup statusGroup, paymentGroup;
    private CheckBox chbOverridePrice;

    public CreateUpdateOrderDialog(Order order, PriceList prefPriceList) {
        controller = Controller.getController();
        this.order = order;
        this.prefPriceList = prefPriceList;
        prefWidth = 400;
        prefHeight = 400;
        prefWidthSmall = 100;
        prefHeightSmall = 150;

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle(order.toString());

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.initContent(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);

        //Column 0
        pane.add(new Label("Price list:"), 0, 0);
        cmbPriceLists = new ComboBox<>();
        cmbPriceLists.setPrefWidth(prefWidth);
        cmbPriceLists.setOnAction(event -> updateProducts());
        pane.add(cmbPriceLists, 0, 1);

        pane.add(new Label("Choose product:"), 0, 2);
        lstProducts = new ListView<>();
        lstProducts.setPrefSize(prefWidth, prefHeight);
        pane.add(lstProducts, 0, 3, 1, 4);

        pane.add(new Label("Order lines"), 0, 7);
        lstOrderLines = new ListView<>();
        lstOrderLines.setPrefSize(prefWidth, prefHeightSmall);
        pane.add(lstOrderLines, 0, 8, 1, 3);

        //Column 1
        pane.add(new Label("Amount"), 1, 3);
        txfAmount = new TextField();
        txfAmount.setPrefWidth(prefWidthSmall);
        pane.add(txfAmount, 1, 4);

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(event -> this.addOrderLine());
        btnAdd.setPrefWidth(prefWidthSmall);
        pane.add(btnAdd, 1, 5);

        HBox increaseDecreaseBox = new HBox();
        increaseDecreaseBox.setSpacing(10);
        increaseDecreaseBox.setPrefWidth(prefWidthSmall);

        Button btnDecrease = new Button("-");
        btnDecrease.prefWidthProperty().bind(increaseDecreaseBox.prefWidthProperty());
        btnDecrease.setOnAction(event -> decreaseAmount());
        increaseDecreaseBox.getChildren().add(btnDecrease);
        Button btnIncrease = new Button("+");
        btnIncrease.prefWidthProperty().bind(increaseDecreaseBox.prefWidthProperty());
        btnIncrease.setOnAction(event -> increaseAmount());
        increaseDecreaseBox.getChildren().add(btnIncrease);
        pane.add(increaseDecreaseBox, 1, 8);

        Button btnRemove = new Button("Remove");
        btnRemove.setPrefWidth(prefWidthSmall);
        btnRemove.setOnAction(event -> removeSelectedOrderLine());
        pane.add(btnRemove, 1,9);

        HBox parentBox = new HBox();
        parentBox.setSpacing(10);
        parentBox.setAlignment(Pos.BASELINE_CENTER);

        statusGroup = new ToggleGroup();
        VBox statusBox = new VBox();
        statusBox.setSpacing(10);

        statusBox.getChildren().add(new Label("Order status:"));

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

        parentBox.getChildren().add(statusBox);

        paymentGroup = new ToggleGroup();
        VBox paymentBox = new VBox();
        paymentBox.setSpacing(10);

        paymentBox.getChildren().add(new Label("Payment type:"));

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

        parentBox.getChildren().add(paymentBox);

        VBox priceBox = new VBox();
        priceBox.setSpacing(10);

        lblTotal = new Label("Total: ");
        priceBox.getChildren().add(lblTotal);

        chbOverridePrice = new CheckBox("Override price");
        chbOverridePrice.setOnAction(event -> updatePriceField());
        priceBox.getChildren().add(chbOverridePrice);

        txfOverridePrice = new TextField();
        txfOverridePrice.setPrefWidth(prefWidthSmall);
        priceBox.getChildren().add(txfOverridePrice);

        parentBox.getChildren().add(priceBox);

        pane.add(parentBox, 0, 11);

        Button btnCheckout = new Button("Checkout");
        btnCheckout.setPrefWidth(prefWidth);
        btnCheckout.setOnAction(event -> checkout());
        pane.add(btnCheckout, 0, 12);

        lblError = new Label("");
        lblError.setTextFill(Color.RED);
        pane.add(lblError, 0, 13);

        updatePriceLists();
        updateProducts();
        updateControls();
        updateOrderLines();
    }

    private void updatePriceLists() {
        cmbPriceLists.getItems().setAll(controller.getPriceLists());
        cmbPriceLists.getSelectionModel().select(prefPriceList);
    }

    private void updateProducts() {
        PriceList pl = cmbPriceLists.getSelectionModel().getSelectedItem();
        lstProducts.getItems().setAll(controller.getProductsInPriceList(pl));
    }

    private void updateOrderLines() {
        lstOrderLines.getItems().setAll(controller.getOrderLinesOnOrder(order));
        lblTotal.setText("Total: " + controller.getTotalPriceForOrder(order));
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
        switch (order.getStatus()) {
            case CREATED: rbCreated.setSelected(true); break;
            case PROGRESS: rbProgress.setSelected(true); break;
            case DONE: rbDone.setSelected(true); break;
        }
        if (order.getPriceOverride() != -1) {
            chbOverridePrice.setSelected(true);
            txfOverridePrice.setText("" + order.getPriceOverride());
        }
        updatePriceField();
    }

    private void updatePriceField() {
        if (chbOverridePrice.isSelected()) {
            txfOverridePrice.setDisable(false);
        } else {
            txfOverridePrice.setDisable(true);
        }
    }

    private void increaseAmount() {
        try {
            OrderLine orderLine =
                    lstOrderLines.getSelectionModel().getSelectedItem();
            if (orderLine == null) {
                throw new NullPointerException("You need to choose an orderline" +
                        " to perform this action");
            }
            controller.adjustOrderLineAmount(orderLine, 1);
        }
        catch (Exception e) {
            lblError.setText(e.getMessage());
        }
        updateOrderLines();
    }

    private void decreaseAmount() {
        try {
            OrderLine orderLine =
                    lstOrderLines.getSelectionModel().getSelectedItem();
            if (orderLine == null) {
                throw new NullPointerException("You need to choose an orderline" +
                        " to perform this action");
            }
            if (orderLine.getAmount() <= 1) {
                throw new IllegalStateException("You cannot reduce the amount " +
                        "to less than one");
            }
            controller.adjustOrderLineAmount(orderLine, -1);
        }
        catch (Exception e) {
            lblError.setText(e.getMessage());
        }
        updateOrderLines();
    }

    private void addOrderLine() {
        try {
            if (lstProducts.getSelectionModel().getSelectedItem() == null) {
                throw new NullPointerException("You have not chosen a product" +
                        " to add");
            }
            Product product = lstProducts.getSelectionModel().getSelectedItem();
            if (product instanceof GiftBox) {
                product = controller.createGiftBox(
                        "Gift Box",
                        product.getProductGroup(),
                        ((GiftBox) product).getType());
                AddGiftboxDialog addGiftboxDialog = new AddGiftboxDialog(
                        (GiftBox) product);
                addGiftboxDialog.showAndWait();
                if (addGiftboxDialog.getCancelled()) {
                    return;
                }
            }
            OrderLine orderLine = controller.createOrderLine(
                    product,
                    cmbPriceLists.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txfAmount.getText()), order);
            updateOrderLines();
        }
        catch (NumberFormatException e) {
            lblError.setText("Amount must be a number");
        }
        catch (Exception e) {
            lblError.setText(e.getMessage());
        }
    }

    private void removeSelectedOrderLine() {
        OrderLine orderLine = lstOrderLines.getSelectionModel().getSelectedItem();
        try {
            if (orderLine == null) {
                throw new NullPointerException("You have not selected an" +
                        "order line to remove");
            }
            controller.deleteOrderLineOnOrder(orderLine, order);
            updateOrderLines();
        }
        catch (Exception e) {
            lblError.setText(e.getMessage());
        }
    }

    private void checkout() {
        try {
            OrderStatusType status =
                    (OrderStatusType) statusGroup.getSelectedToggle().getUserData();
            PaymentMethod paymentMethod =
                    (PaymentMethod) paymentGroup.getSelectedToggle().getUserData();
            if (paymentMethod == PaymentMethod.PAYLATER &&
                    status == OrderStatusType.DONE) {
                throw new IllegalStateException("Order can't be both done and pay later");
            }
            controller.updateOrder(status, paymentMethod, order);
            if (status == OrderStatusType.DONE) {
                controller.setEndTimestampOnOrder(order);
            }
            if (chbOverridePrice.isSelected()) {
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
        catch (NumberFormatException e) {
            lblError.setText("Price override must be a number");
            return;
        }
        catch (Exception e) {
            lblError.setText(e.getMessage());
            return;
        }
        this.hide();
    }

}
