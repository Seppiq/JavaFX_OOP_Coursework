package com.example.demodemonoch.controller;

import com.example.demodemonoch.model.User;
import com.example.demodemonoch.service.UserService;
import com.example.demodemonoch.service.impl.UserServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class UserController {
    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> firstName;

    @FXML
    private TableColumn<User, String> lastName;

    @FXML
    private TableColumn<User, String> position;

    // инициализируем форму данными
    @FXML
    public void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        position.setCellValueFactory(new PropertyValueFactory<User, String>("position"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);
    }

    private void initData() {
        UserService userService = new UserServiceImpl();
        usersData.addAll(userService.getAllUsers());
    }

    @FXML
    private void remove(ActionEvent actionEvent) {
        UserService userService = new UserServiceImpl();
        userService.deleteEmployeeById(tableUsers.getSelectionModel().getSelectedItem().getId());
        tableUsers.getItems().removeAll(tableUsers.getSelectionModel().getSelectedItem());
    }

    @FXML
    private Button btnAdd;

    @FXML
    void add(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddUserForm.fxml"));
        Stage stage = new Stage();
        stage.setTitle("sdf");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void update(ActionEvent actionEvent) {

    }
}
