package com.example.demodemonoch.controller;

import com.example.demodemonoch.model.User;
import com.example.demodemonoch.service.UserService;
import com.example.demodemonoch.service.impl.UserServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;

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
    private void initialize() {
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
}
