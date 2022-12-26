package com.example.demo1.Controller;


import com.example.demo1.Model.Context;
import com.example.demo1.Model.Employee;
import com.example.demo1.Service.EmployeeService;
import com.example.demo1.Service.Impl.EmployeeServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

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
    private TextField instructionInput;

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
    private TableColumn<Employee, Integer> idInstruction;

    @FXML
    private TableColumn<Employee, String> lastName;

    @FXML
    private TableColumn<Employee, List> products;

    @FXML
    private TableColumn<Employee, List> services;


    public EmployeeController() {
    }

    @FXML
    void initialize() {

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
                Context.filepath = file.getPath();
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

            submit.setOnAction(action -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Create " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    add();
                    updateTable(employeeService.getAllEmployees());
                    new Alert(Alert.AlertType.INFORMATION, "Запись добавлена").show();
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
                else if (instructionRadio.isSelected()) updateTable(employeeService.instructionSearch(newValue));
            }
        });
    }

    private void update() {
        Employee employee = new Employee();
        int id = table.getSelectionModel().getSelectedItem().getId();
        employee.setId(Integer.valueOf(idInput.getText()));
        employee.setFirstName(nameInput.getText());
        employee.setLastName(surnameInput.getText());
        employee.setAge(Integer.valueOf(instructionInput.getText()));
        employee.setProducts(employeeService.getEmployeeById(id).getProducts());
        employee.setServices(employeeService.getEmployeeById(id).getServices());
        employeeService.updateEmployeeById(id, employee);
    }

    private void add() {
        Employee employee = new Employee();
        //int id = Integer.parseInt(idInput.getText());
        employee.setId(Integer.valueOf(idInput.getText()));
        employee.setFirstName(nameInput.getText());
        employee.setLastName(surnameInput.getText());
        employee.setAge(Integer.valueOf(instructionInput.getText()));
        employee.setProducts(new ArrayList<>());
        employee.setServices(new ArrayList<>());
        if (employee.getId() > 0) {
            employeeService.createEmployee(employee);
            updateTable(employeeService.getAllEmployees());
        }
        else
            new Alert(Alert.AlertType.INFORMATION, "Invalid data").show();
    }

    private void updateTable(List<Employee> list) {
        ObservableList<Employee> students = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idInstruction.setCellValueFactory(new PropertyValueFactory<>("Age"));
        products.setCellValueFactory(new PropertyValueFactory<>("products"));
        services.setCellValueFactory(new PropertyValueFactory<>("services"));
        table.setItems(students);
    }

    private void delete() {
        employeeService.deleteEmployeeById(table.getSelectionModel().getSelectedItem().getId());
    }

    private void clearInput() {
        idInput.setText("");
        nameInput.setText("");
        surnameInput.setText("");
        instructionInput.setText("");
    }

    private void fillingInput() {
        idInput.setText(table.getSelectionModel().getSelectedItem().getId().toString());
        nameInput.setText(table.getSelectionModel().getSelectedItem().getFirstName());
        surnameInput.setText(table.getSelectionModel().getSelectedItem().getLastName());
        instructionInput.setText(table.getSelectionModel().getSelectedItem().getAge().toString());
    }
}
