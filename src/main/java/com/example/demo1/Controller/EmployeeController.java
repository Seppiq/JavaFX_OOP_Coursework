package com.example.demo1.Controller;


import com.example.demo1.Model.Employee;
import com.example.demo1.Model.ReferenceTableEmployeeProduct;
import com.example.demo1.Model.ReferenceTableEmployeeService;
import com.example.demo1.Repository.EmployeeRepository;
import com.example.demo1.Repository.Impl.EmployeeRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Data
public class EmployeeController {

    private final EmployeeRepository employeeService = new EmployeeRepositoryImpl();

    @FXML
    public Pane openFXPaneObjectProduct;

    @FXML
    private ToggleGroup find;

    @FXML
    private TextField findInput;

    @FXML
    private RadioMenuItem instructionRadio;

    @FXML
    private RadioMenuItem nameRadio;

    @FXML
    private RadioMenuItem surnameRadio;

    @FXML
    private GridPane addPanel;

    @FXML
    private Button cancel;

    @FXML
    private Button export;

    @FXML
    private TextField idInput;

    @FXML
    private TextField ageInput;

    @FXML
    private TextField nameInput;

    @FXML
    private Button submit;

    @FXML
    private TextField surnameInput;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnOpen;

    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, String> firstName;

    @FXML
    private TableColumn<Employee, Integer> id;

    @FXML
    private TableColumn<Employee, Integer> age;

    @FXML
    private TableColumn<Employee, String> lastName;

    @FXML
    private TableColumn<Employee, List> products;

    @FXML
    private TableColumn<Employee, List> services;

    @FXML
    public TextField idServiceInput;

    @FXML
    public TextField idProductInput;


    public EmployeeController() {
    }

    @FXML
    void initialize() {

        updateTable(employeeService.getAllEmployees());

        export.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
            File file = fileChooser.showSaveDialog(export.getScene().getWindow());
            if (file != null) {
                employeeService.saveFile(file);
            }
        });

        btnOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
            File file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());
            if (file != null) {
                updateTable(employeeService.getAllEmployees());
            }
        });


        btnDelete.setOnAction(actionEvent -> {
            if (!table.getSelectionModel().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Delete" + " ?", ButtonType.YES, ButtonType.NO);
                alert1.showAndWait();
                if (alert1.getResult() == ButtonType.YES) {
                    delete();
                    updateTable(employeeService.getAllEmployees());
                    new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                }
            } else if (table.getItems().isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Table is empty").show();
            }
        });

        cancel.setOnAction(action -> {
            clearInput();
            addPanel.setStyle("visibility: hidden;");

        });

        btnAdd.setOnAction(actionEvent -> {
            addPanel.setStyle("visibility: visible");

            openFXPaneObjectProduct.toFront();

            try {
                Node node = FXMLLoader.load(getClass().getResource("item2shorts.fxml"));
                openFXPaneObjectProduct.getChildren().add(node);

            } catch (IOException e) {
                e.printStackTrace();
            }

            submit.setOnAction(action -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Create " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    add();
                    updateTable(employeeService.getAllEmployees());
                    new Alert(Alert.AlertType.INFORMATION, "Created").show();
                }
                if (alert.getResult() == ButtonType.CANCEL) {
                    clearInput();
                }
            });
        });


        btnUpdate.setOnAction(actionEvent -> {
            if (!table.getSelectionModel().isEmpty()) {
                addPanel.setStyle("visibility: visible;");
                fillingInput();

                submit.setOnAction(action -> {
                    update();
                    updateTable(employeeService.getAllEmployees());
                    addPanel.setStyle("visibility: hidden;");
                    clearInput();
                    new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                });
            }
        });

        findInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!table.getItems().isEmpty()) {
                if (nameRadio.isSelected()) updateTable(employeeService.nameSearch(newValue));
                else if (surnameRadio.isSelected()) updateTable(employeeService.surnameSearch(newValue));
                else if (instructionRadio.isSelected()) updateTable(employeeService.search(newValue));
            }
        });
    }

    private void update() {
        Employee employee = new Employee();
        int id = table.getSelectionModel().getSelectedItem().getId();
        employee.setId(Integer.valueOf(idInput.getText()));
        employee.setFirstName(nameInput.getText());
        employee.setLastName(surnameInput.getText());
        employee.setAge(Integer.valueOf(ageInput.getText()));
        employeeService.updateEmployeeById(id, employee);
    }

    private void add() {
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(idInput.getText()));
        employee.setFirstName(nameInput.getText());
        employee.setLastName(surnameInput.getText());
        employee.setAge(Integer.valueOf(ageInput.getText()));

        ReferenceTableEmployeeProduct employeeProduct = new ReferenceTableEmployeeProduct();
        ReferenceTableEmployeeService employeeService1 = new ReferenceTableEmployeeService();

        employeeService1.setService_id(Integer.valueOf(idServiceInput.getText()));
        employeeService1.setEmployee_id(employee.getId());

        employeeProduct.setProduct_id(Integer.valueOf(idProductInput.getText()));
        employeeProduct.setEmployee_id(employee.getId());

        employeeService.saveEmployee(employee, employeeService1, employeeProduct);
        updateTable(employeeService.getAllEmployees());
        //new Alert(Alert.AlertType.INFORMATION, "Invalid data").show();
    }

    private void updateTable(List<Employee> list) {
        ObservableList<Employee> students = FXCollections.observableArrayList(list);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));

        table.setItems(students);
    }

    private void delete() {
        employeeService.deleteEmployeeById(table.getSelectionModel().getSelectedItem().getId());
    }

    private void clearInput() {
        idInput.setText("");
        nameInput.setText("");
        surnameInput.setText("");
        ageInput.setText("");
        idProductInput.setText("");
        idServiceInput.setText("");
    }

    private void fillingInput() {
        idInput.setText(table.getSelectionModel().getSelectedItem().getId().toString());
        nameInput.setText(table.getSelectionModel().getSelectedItem().getFirstName());
        surnameInput.setText(table.getSelectionModel().getSelectedItem().getLastName());
        ageInput.setText(table.getSelectionModel().getSelectedItem().getAge().toString());
    }
}
