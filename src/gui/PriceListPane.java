package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import model.*;
import controller.Controller;

public class PriceListPane extends GridPane {

    private Controller controller;

    private ListView<PriceList> lstPriceLists = new ListView<>();
    private ListView<Product> lstProductList = new ListView<>();
    private ListView<String> lstProductListProducts = new ListView<>();
    private TextField txfPriceListType;
    private TextField txfProductPrice;
    private Button btnCreateNewPriceList;
    private Button btnRemovePriceList;
    private Button btnAdd;
    private Label lblInfo;

    public PriceListPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        controller = Controller.getController();

        this.add(new Label("Price list name:"), 0, 0);
        this.add(new Label("Product price"), 1, 2);
        this.lblInfo = new Label("");
        this.add(lblInfo, 0, 7, 3, 1);

        txfPriceListType = new TextField();
        this.add(txfPriceListType, 0, 1);

        txfProductPrice = new TextField();
        this.add(txfProductPrice, 1, 3);

        this.add(lstProductList, 0, 2, 1, 4);
        this.add(lstPriceLists, 2, 2, 1, 4);
        this.add(lstProductListProducts, 3, 2, 1, 4);

        lstProductList.getItems().setAll(controller.getProducts());
        lstPriceLists.getItems().setAll(controller.getPriceLists());
        lstPriceLists.getSelectionModel().selectedItemProperty().addListener(event -> this.updateSelectedPriceList());

        this.btnAdd = new Button("Add product");
        this.btnAdd.setOnAction(event -> this.addProductToPriceList());
        this.add(btnAdd, 1, 2);

        this.btnCreateNewPriceList = new Button("New price list");
        this.btnCreateNewPriceList.setOnAction(event -> this.createPriceList());
        this.add(btnCreateNewPriceList, 2, 6);

        this.btnRemovePriceList = new Button("Delete price list");
        this.btnRemovePriceList.setOnAction(event -> this.deletePriceList());
        this.add(btnRemovePriceList, 0, 6);
    }

    private void updateSelectedPriceList() {
        PriceList pl = this.lstPriceLists.getSelectionModel().getSelectedItem();
        this.lstProductListProducts.getItems().setAll(pl.getProductsWithPriceInPriceList());
    }

    private void addProductToPriceList() {
        if (this.txfProductPrice.getText().length() <= 0) {
            this.lblInfo.setText("Please input a product price");
            return;
        }

        if (this.lstProductList.getSelectionModel().getSelectedItem() == null) {
            this.lblInfo.setText("Please select a product");
            return;
        }

        if (this.lstPriceLists.getSelectionModel().getSelectedItem() == null) {
            this.lblInfo.setText("Please select a price list");
            return;
        }

        try {
            double price = Double.parseDouble(this.txfProductPrice.getText().trim());

            if (price < 0) {
                throw new RuntimeException("Price must be positive");
            }

            Product p = this.lstProductList.getSelectionModel().getSelectedItem();
            PriceList pl = this.lstPriceLists.getSelectionModel().getSelectedItem();

            controller.addProductToPriceList(p, price, pl);
            this.lblInfo.setText(p.getProductName() + " added to " + pl.getType() + " for " + price + "kr");
            this.lstProductListProducts.getItems().setAll(pl.getProductsWithPriceInPriceList());
        } catch (NumberFormatException e) {
            this.lblInfo.setText("Product price has to be a number");
        } catch (RuntimeException e) {
            this.lblInfo.setText(e.getMessage());
        }
    }

    private void createPriceList() {
        if (this.txfPriceListType.getText().length() <= 0) {
            this.lblInfo.setText("Please input a name for the price list");
            return;
        }

        PriceList pl = controller.createPriceList(this.txfPriceListType.getText());
        this.lstPriceLists.getItems().add(pl);
        this.lblInfo.setText("Price list '" + pl.getType() + "' added");
        this.txfPriceListType.setText("");
    }

    private void deletePriceList() {
        if (this.lstPriceLists.getSelectionModel().getSelectedItem() == null) {
            this.lblInfo.setText("Please select the price list you wish to delete");
            return;
        }

        String plName = this.lstPriceLists.getSelectionModel().getSelectedItem().getType();
        controller.deletePriceList(this.lstPriceLists.getSelectionModel().getSelectedItem());
        this.lblInfo.setText("Price list '" + plName + "' removed");

        this.lstPriceLists.getItems().setAll(controller.getPriceLists());
    }
}
