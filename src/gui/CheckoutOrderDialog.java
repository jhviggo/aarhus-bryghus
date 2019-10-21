package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Order;

public class CheckoutOrderDialog extends Stage {

    private Controller controller;
    private Button btnCheckout;
    private TextField txfOverridePrice;
    private CheckBox chbOverride;
    private RadioButton rbCreditCard, rbCash, rbPayLater, rbCreated, rbProgress,
                        rbDone;
    private Order order;

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
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setGridLinesVisible(true);

        pane.add(new Label("Choose payment option"), 0,0);

        final ToggleGroup paymentGroup = new ToggleGroup();
        VBox paymentBox = new VBox();
        paymentBox.setSpacing(10);

        rbCreditCard = new RadioButton("Credit card");
        rbCreditCard.setToggleGroup(paymentGroup);
        rbCash = new RadioButton("Cash");
        rbCash.setToggleGroup(paymentGroup);
        rbPayLater = new RadioButton("Pay later");
        rbPayLater.setToggleGroup(paymentGroup);

        paymentBox.getChildren().add(rbCreditCard);
        paymentBox.getChildren().add(rbCash);
        paymentBox.getChildren().add(rbPayLater);

        pane.add(paymentBox, 0, 1);

        pane.add(new Label("Choose order status"), 0, 2);

        final ToggleGroup statusGroup = new ToggleGroup();
        VBox statusBox = new VBox();
        statusBox.setSpacing(10);

        rbCreated = new RadioButton("Created");
        rbCreated.setToggleGroup(statusGroup);
        rbProgress = new RadioButton("In progress");
        rbProgress.setToggleGroup(statusGroup);
        rbDone = new RadioButton("Done");
        rbDone.setToggleGroup(statusGroup);

        statusBox.getChildren().add(rbCreated);
        statusBox.getChildren().add(rbProgress);
        statusBox.getChildren().add(rbDone);

        pane.add(statusBox, 0, 3);

        chbOverride = new CheckBox("Override Price");
        pane.add(chbOverride, 0, 4);

        txfOverridePrice = new TextField();
        pane.add(txfOverridePrice, 1, 4);

        btnCheckout = new Button("Checkout order");
        pane.add(btnCheckout, 0, 5, 2, 1);

    }

}
