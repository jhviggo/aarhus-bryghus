package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {

    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Aarhus Bryghus - POS System");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setHeight(800);
        stage.setWidth(800);
        stage.show();
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabOrder = new Tab("Register sale");
        Tab tabRegister = new Tab("Register new product");
        Tab tabPriceList = new Tab("Manage pricelists");
        Tab tabExportInventory = new Tab("Export inventory");
        //Tab udtræk
        //Tab ikke aflevere produkter
        //Tab rundvisning

        tabOrder.setContent(new RegisterSalePane());
        tabExportInventory.setContent(new InventoryExportPane());
        tabPriceList.setContent(new PriceListPane());

        tabPane.getTabs().add(tabOrder);
        tabPane.getTabs().add(tabRegister);
        tabPane.getTabs().add(tabPriceList);
        tabPane.getTabs().add(tabExportInventory);
    }
}
