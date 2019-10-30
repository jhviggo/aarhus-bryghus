package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import model.Beer;
import model.BeerType;
import model.GiftBox;
import model.Product;

import java.util.HashMap;

public class AddGiftboxDialog extends Stage {

    private Controller controller;
    private GiftBox giftBox;

    private int currentAmount;
    private int maxAmount;
    private boolean cancelled;

    private Label lblMax, lblCurrent;
    private ListView<Beer> lstBeers;
    private ListView<Beer> lstBeersInGiftBox;

    public AddGiftboxDialog(GiftBox giftBox) {
        controller = Controller.getController();
        this.giftBox = giftBox;
        maxAmount = giftBox.getMaxAmountOfProducts();
        cancelled = true;

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setTitle("Add products to giftbox");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);

        this.initContent(pane);
        this.setScene(scene);
        this.updateBeerList();
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblCurrent = new Label("Current: " + currentAmount);
        pane.add(lblCurrent, 0, 0);
        lblMax = new Label("Max: " + maxAmount);
        pane.add(lblMax, 1, 0);

        pane.add(new Label("Available:"), 0, 1);

        lstBeers = new ListView<>();
        lstBeers.setPrefSize(300, 300);
        pane.add(lstBeers, 0, 2, 2, 1);

        HBox addRemoveBox = new HBox();
        addRemoveBox.setSpacing(10);
        addRemoveBox.setAlignment(Pos.BASELINE_RIGHT);
        Button btnAddBeer = new Button("Add beer");
        btnAddBeer.setOnAction(event -> this.addBeer());
        Button btnRemoveBeer = new Button("Remove beer");
        btnRemoveBeer.setOnAction(event -> this.removeBeer());
        addRemoveBox.getChildren().add(btnAddBeer);
        addRemoveBox.getChildren().add(btnRemoveBeer);
        pane.add(addRemoveBox, 0, 3, 2, 1);

        pane.add(new Label("Currently in gift box:"), 0, 4);

        lstBeersInGiftBox = new ListView<>();
        lstBeersInGiftBox.setPrefSize(300, 300);
        pane.add(lstBeersInGiftBox, 0, 5, 2, 1);

        HBox confirmBox = new HBox();
        confirmBox.setAlignment(Pos.BASELINE_RIGHT);
        Button btnConfirm = new Button("Add giftbox");
        btnConfirm.setOnAction(event -> this.confirmAndAdd());
        confirmBox.getChildren().add(btnConfirm);
        pane.add(confirmBox, 0, 6, 2 ,1);
    }

    private void updateBeerList() {
        for (Product p : controller.getProducts()) {
            if (p instanceof Beer &&
                    ((Beer) p).getBeerType() == BeerType.BOTTLE) {
                lstBeers.getItems().add((Beer) p);
            }
        }
    }

    private void updateGiftBoxList() {
        HashMap<Beer, Integer> beersInCase = giftBox.getProductsInGiftCase();
        lstBeersInGiftBox.getItems().clear();
        for (Beer b : beersInCase.keySet()) {
            for (int i = 0; i < beersInCase.get(b); i++) {
                lstBeersInGiftBox.getItems().add(b);
            }
        }
    }

    public boolean getCancelled() {
        return cancelled;
    }

    private void addBeer() {
        try {
            controller.addProductToGiftBox(giftBox,
                    lstBeers.getSelectionModel().getSelectedItem());
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }
        currentAmount++;
        updateGiftBoxList();
    }

    private void removeBeer() {
        try {
            controller.removeProductFromGiftBox(giftBox,
                    lstBeersInGiftBox.getSelectionModel().getSelectedItem());
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }
        currentAmount--;
        updateGiftBoxList();
    }

    private void confirmAndAdd() {
        try {
            if (currentAmount != maxAmount) {
                throw new IllegalStateException("The giftbox is not full");
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }
        cancelled = false;
        this.hide();
    }

}
