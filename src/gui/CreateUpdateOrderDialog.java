package gui;

import controller.Controller;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private TextField txfAmount;
    private Label lblTotal;
    private RadioButton rbCreated, rbProgress, rbDone, rbCreditCard, rbCash, rbPayLater;
    private ToggleGroup statusGroup, paymentGroup;

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
        increaseDecreaseBox.getChildren().add(btnDecrease);
        Button btnIncrease = new Button("+");
        btnIncrease.prefWidthProperty().bind(increaseDecreaseBox.prefWidthProperty());
        increaseDecreaseBox.getChildren().add(btnIncrease);
        pane.add(increaseDecreaseBox, 1, 8);

        Button btnRemove = new Button("Remove");
        btnRemove.setPrefWidth(prefWidthSmall);
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

        pane.add(parentBox, 0, 11);

        Button btnCheckout = new Button("Checkout");
        btnCheckout.setPrefWidth(prefWidth);
        pane.add(btnCheckout, 0, 12);
    }

    private void addOrderLine() {

    }

}
