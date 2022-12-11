module com.example.demodemonoch {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;

    opens com.example.demodemonoch to javafx.fxml;

    opens com.example.demodemonoch.controller to javafx.fxml;
    opens com.example.demodemonoch.model to javafx.fxml, javafx.graphics, com.google.gson;
    exports com.example.demodemonoch;
    exports com.example.demodemonoch.model;
    exports com.example.demodemonoch.controller;

}