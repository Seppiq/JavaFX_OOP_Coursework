package com.example.demo1.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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

//    public void handleClicks(ActionEvent actionEvent) {
//        if (actionEvent.getSource() == btnCustomers) {
//            //pnlCustomer.setStyle("-fx-background-color : #ffa500");
//            showProduct();
//            pnlCustomer.toFront();
//        }
//        if (actionEvent.getSource() == btnOverview) {
//            //setStyle("-fx-background-color : #ffa500");
//            showEmployee();
//            pnlOverview.toFront();
//        }
//        if (actionEvent.getSource() == btnOrders) {
//            //pnlOrders.setStyle("-fx-background-color : #ffa500");
//            showService();
//            pnlOrders.toFront();
//        }
//    }

//    @FXML
//    public void showEmployee() {
//        Node[] nodes = new Node[1];
//        for (int i = 0; i < nodes.length; i++) {
//            try {
//
//                final int j = i;
//                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
//
//                //give the items some effect
//
////                nodes[i].setOnMouseEntered(event -> {
////                    nodes[j].setStyle("-fx-background-color : #273fff");
////                });
////                nodes[i].setOnMouseExited(event -> {
////                    nodes[j].setStyle("-fx-background-color : #273fff");
////                });
//                pnItems.getChildren().add(nodes[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

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