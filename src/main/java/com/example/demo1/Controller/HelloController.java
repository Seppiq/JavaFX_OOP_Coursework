package com.example.demo1.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HelloController {

    @FXML
    public Pane pnlEmployee;
    @FXML
    public VBox pnEmployee;
    @FXML
    public Pane pnlProducts;
    @FXML
    public VBox pnProducts;
    @FXML
    public Pane pnlServices;
    @FXML
    public VBox pnServices;

    @FXML
    private VBox pnItems = null;

    @FXML
    private VBox pnItems2 = null;

    @FXML
    private VBox pnItems3 = null;


    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @FXML
    public void showEmployee() {
        pnlEmployee.toFront();
        try {
            Node node = FXMLLoader.load(getClass().getResource("Item.fxml"));
            pnEmployee.getChildren().add(node);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showProduct() {
        pnlProducts.toFront();
        try {
            Node node = FXMLLoader.load(getClass().getResource("item3.fxml"));
            pnProducts.getChildren().add(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showService() {
        pnlServices.toFront();
        try {
            Node node = FXMLLoader.load(getClass().getResource("item2.fxml"));
            pnServices.getChildren().add(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        btnOverview.setOnAction(actionEvent -> {
            showEmployee();
        });

        btnCustomers.setOnAction(actionEvent -> {
            showProduct();
        });

        btnOrders.setOnAction(actionEvent -> {
            showService();
        });
    }
}