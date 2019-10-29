package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import java.time.LocalDateTime;

import model.*;
import controller.Controller;

public class RegisterSalePane extends GridPane {

    private Controller controller;

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
    private Button btnCreateOrder;
    private Button btnExport;
    private Label lblTotal;
    private Label lblOrderId;
    private Order order;

    public RegisterSalePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        controller = Controller.getController();
        controller.initializeData();

        btnCreateOrder = new Button("Create new order");
        btnCreateOrder.setOnAction(event -> this.createOrder());
        this.add(btnCreateOrder, 0, 0);
        lblOrderId = new Label("");
        this.add(lblOrderId, 1, 0);

        this.add(new Label("Price list:"), 0, 1);
        cmbPriceList = new ComboBox<>();
        cmbPriceList.setPrefWidth(400);
        cmbPriceList.getItems().setAll(controller.getPriceLists());
        this.add(cmbPriceList, 0, 2);
        cmbPriceList.getSelectionModel().select(0);
        cmbPriceList.setOnAction(event -> this.updateProducts());
        btnLockPriceList = new Button("\uD83D\uDD13");
        btnLockPriceList.setOnAction(event -> this.setLockPriceList());
        this.add(btnLockPriceList, 1, 2);

        this.add(new Label("Choose product"), 0, 3);
        lstProducts.setPrefSize(400,400);
        this.add(lstProducts, 0, 4, 1, 3);

        this.add(new Label("Amount"), 1, 3);

        txfAmount = new TextField();
        this.add(txfAmount, 1, 4);

        btnAdd = new Button("Add");
        btnAdd.setOnAction(event -> this.addOrderLine());
        btnAdd.setDisable(true);
        this.add(btnAdd, 1, 5);

        this.add(new Label("Order lines"), 0, 8);
        lstOrderLines.setPrefSize(400, 200);
        this.add(lstOrderLines, 0, 9, 1, 4);

        btnIncrementAmount = new Button("+");
        btnDeductAmount = new Button("-");
        HBox editAmountControlBox = new HBox();

        editAmountControlBox.getChildren().add(btnIncrementAmount);
        editAmountControlBox.getChildren().add(btnDeductAmount);
        editAmountControlBox.setSpacing(10);
        this.add(editAmountControlBox, 1, 9);

        btnRemove = new Button("Remove");
        btnRemove.setOnAction(event -> this.removeSelectedOrderLine());
        this.add(btnRemove, 1, 10);

        lblTotal = new Label("Total: ");
        this.add(lblTotal, 1, 11);

        btnCheckout = new Button("Checkout");
        btnCheckout.setOnAction(event -> this.checkoutAction());
        this.add(btnCheckout, 1, 12);

        btnExport = new Button("Export all previous orders");
        btnExport.setOnAction(event -> controller.exportOrders());
        this.add(btnExport, 2, 12);

        updateProducts();
    }

    private void updateProducts() {
        PriceList pl = this.cmbPriceList.getSelectionModel().getSelectedItem();
        lstProducts.getItems().setAll(controller.getProductsInPriceList(pl));
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
        if (order != null) {
            CheckoutOrderDialog checkoutDialog = new CheckoutOrderDialog(order);
            checkoutDialog.showAndWait();
        } else {
            noOrderError();
        }
    }

    private void createOrder() {
        order = controller.createOrder(LocalDateTime.now().withNano(0),
                OrderStatusType.CREATED);
        lblOrderId.setText(order.toString());
        btnAdd.setDisable(false);
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
                AddGiftboxDialog giftboxDialog = new AddGiftboxDialog(
                        (GiftBox) product);
                giftboxDialog.showAndWait();
                if (giftboxDialog.getCancelled()) {
                    return;
                }
            }
            OrderLine orderLine = controller.createOrderLine(
                    product,
                    cmbPriceList.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txfAmount.getText()), order);
            updateContent();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private void updateContent() {
        lstOrderLines.getItems().setAll(controller.getOrderLinesOnOrder(order));
        lblTotal.setText("Total: " + controller.getTotalPriceForOrder(order));
    }

    //TODO: Register Sale skal måske flyttes til en dialog
    private void noOrderError() {

    }

    private void removeSelectedOrderLine() {
        OrderLine orderLine = lstOrderLines.getSelectionModel().getSelectedItem();
        controller.deleteOrderLineOnOrder(orderLine, order);
        updateContent();
    }

}
