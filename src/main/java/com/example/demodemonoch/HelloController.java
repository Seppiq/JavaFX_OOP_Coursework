package com.example.demodemonoch;

import com.example.demodemonoch.repo.UserRepo;
import com.example.demodemonoch.repo.impl.UserRepoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HelloController {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @FXML
    public void initialize() {

        UserRepo userRepo = new UserRepoImpl();
        System.out.println(userRepo.getAllUsers());

        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();

            if (actionEvent.getSource() == btnOverview) {
                pnlOverview.setStyle("-fx-background-color : #02030A");
                pnlOverview.toFront();
            }
            if (actionEvent.getSource() == btnOrders) {
                pnlOrders.setStyle("-fx-background-color : #464F67");
                pnlOrders.toFront();
            }
        }


//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() throws Exception {
//
//        User user = new User(3, "Vladik", "Semak", "position");
//        User user1 = new User(4, "Oleg", "Semak", "position");
//        User user2 = new User(5, "Kirill", "Semak", "position");
//
//        UserRepo userRepo = new UserRepoImpl();
//        //User user = userRepo.getAllUsers();
//        //List<User> users = userRepo.createUser(user);
//        // User users = userRepo.getUserById(1);
////        List<User> user2 = userRepo.getAllUsers();
////        List<User> user1 = userRepo.getUserById(1);
////        System.out.println(user1.toString());
////        System.out.println(user2.toString());
//        //System.out.println(users.toString());
//        userRepo.createUser(user);
////        userRepo.createUser(user1);
////        userRepo.createUser(user2);
//        ContainsAllApplication containsAllApplication = new ContainsAllApplication();
//        containsAllApplication.showWindow();
//        System.out.println(userRepo.deleteEmployeeById(3));
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
    }
}