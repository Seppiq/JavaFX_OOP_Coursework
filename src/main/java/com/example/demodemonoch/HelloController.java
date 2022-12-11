package com.example.demodemonoch;

import com.example.demodemonoch.model.User;
import com.example.demodemonoch.repo.UserRepo;
import com.example.demodemonoch.repo.impl.UserRepoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception {

        User user = new User(3, "Vladik", "Semak", "position");
        User user1 = new User(4, "Oleg", "Semak", "position");
        User user2 = new User(5, "Kirill", "Semak", "position");

        UserRepo userRepo = new UserRepoImpl();
        //User user = userRepo.getAllUsers();
        //List<User> users = userRepo.createUser(user);
        // User users = userRepo.getUserById(1);
//        List<User> user2 = userRepo.getAllUsers();
//        List<User> user1 = userRepo.getUserById(1);
//        System.out.println(user1.toString());
//        System.out.println(user2.toString());
        //System.out.println(users.toString());
        userRepo.createUser(user);
//        userRepo.createUser(user1);
//        userRepo.createUser(user2);
        ContainsAllApplication containsAllApplication = new ContainsAllApplication();
        containsAllApplication.showWindow();
        System.out.println(userRepo.deleteEmployeeById(3));
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}