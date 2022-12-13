package com.example.demodemonoch.controller;

import com.example.demodemonoch.model.User;
import com.example.demodemonoch.service.UserService;
import com.example.demodemonoch.service.impl.UserServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class AddUserFormController {
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField id;

    @FXML
    private TextField position;

    @FXML
    private Button addButton;

    @FXML
    public void register() throws IOException {

        UserService userService = new UserServiceImpl();
        userService.createUser(new User(Integer.valueOf(id.getText()), firstName.getText(), lastName.getText(), position.getText()));
    }
}
