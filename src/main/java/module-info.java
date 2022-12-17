module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.Controller;
    opens com.example.demo1.Controller to javafx.fxml;
    opens com.example.demo1.Model to javafx.fxml, javafx.graphics, com.google.gson;
    exports com.example.demo1.Model;
}