package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import controller.Controller;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
    	Controller controller = Controller.getController();
    	controller.initializeData();
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

        Tab tabOrder = new Tab("Orders");
        Tab tabRegister = new Tab("Register new product");
        Tab tabPriceList = new Tab("Manage pricelists");
        Tab tabJourneyTicket = new Tab("Journey-ticket");

        tabOrder.setContent(new OrdersPane());
        tabPriceList.setContent(new PriceListPane());
        tabPriceList.setContent(new PriceListPane());
        tabJourneyTicket.setContent(new TicketPane());
        tabRegister.setContent(new RegisterProductPane());

        tabPane.getTabs().add(tabOrder);
        tabPane.getTabs().add(tabRegister);
        tabPane.getTabs().add(tabPriceList);
        tabPane.getTabs().add(tabJourneyTicket);
    }
}
