package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

import model.*;
import controller.Controller;

public class RegisterSalePane extends GridPane {

    private ListView<Product> lstProducts = new ListView<>();
    private ListView<OrderLine> lstOrderLines = new ListView<>();
    private ComboBox<PriceList> cmbPriceList;
    private TextField txfAmount;
    private Button btnLockPriceList;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnIncrementAmount;
    private Button btnDeductAmount;
    private Button btnCheckout;
    private Label lblTotal;

    public RegisterSalePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(new Label("Price list:"), 0, 0);
        cmbPriceList = new ComboBox<>();
        cmbPriceList.setPrefWidth(400);
        this.add(cmbPriceList, 0, 1);
        btnLockPriceList = new Button("\uD83D\uDD13");
        btnLockPriceList.setOnAction(event -> this.setLockPriceList());
        this.add(btnLockPriceList, 1, 1);

        this.add(new Label("Choose product"), 0, 2);
        lstProducts.setPrefSize(400,400);
        this.add(lstProducts, 0, 3, 1, 4);

        this.add(new Label("Amount"), 1, 2);

        txfAmount = new TextField();
        this.add(txfAmount, 1, 3);

        btnAdd = new Button("Add");
        this.add(btnAdd, 1, 4);

        this.add(new Label("Order lines"), 0, 7);
        lstOrderLines.setPrefSize(400, 200);
        this.add(lstOrderLines, 0, 8, 1, 4);

        btnIncrementAmount = new Button("+");
        btnDeductAmount = new Button("-");
        HBox editAmountControlBox = new HBox();

        editAmountControlBox.getChildren().add(btnIncrementAmount);
        editAmountControlBox.getChildren().add(btnDeductAmount);
        editAmountControlBox.setSpacing(10);
        this.add(editAmountControlBox, 1, 8);

        btnRemove = new Button("Remove");
        this.add(btnRemove, 1, 9);

        lblTotal = new Label("Total: ");
        this.add(lblTotal, 1, 10);

        btnCheckout = new Button("Checkout");
        btnCheckout.setOnAction(event -> this.checkoutAction());
        this.add(btnCheckout, 1, 11);
    }

    private void setLockPriceList() {
        if (cmbPriceList.isDisabled()) {
            cmbPriceList.setDisable(false);
            btnLockPriceList.setText("\uD83D\uDD13");
        } else {
            cmbPriceList.setDisable(true);
            btnLockPriceList.setText("\uD83D\uDD12");
        }
    }

    private void checkoutAction() {
        CheckoutOrderDialog checkoutDialog = new CheckoutOrderDialog();
        checkoutDialog.showAndWait();
    }

}
