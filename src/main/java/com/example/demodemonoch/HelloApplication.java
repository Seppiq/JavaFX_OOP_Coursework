package com.example.demodemonoch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    Stage stage = new Stage();

    @Override
    public void start(Stage prStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        prStage.setTitle("Hello World");
        prStage.setScene(new Scene(root, 1000, 800));
        prStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void showWindow() throws Exception {
        start(stage);
    }

    public void hideWindow() throws Exception {
        stage.close();
    }
}