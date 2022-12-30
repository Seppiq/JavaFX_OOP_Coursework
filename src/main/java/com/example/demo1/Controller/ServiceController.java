package com.example.demo1.Controller;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.ReferenceTableEmployeeService;
import com.example.demo1.Model.Service;
import com.example.demo1.Repository.Impl.ServiceRepoImpl;
import com.example.demo1.Repository.ServiceRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class ServiceController {
    private final ServiceRepo serviceRepo = new ServiceRepoImpl();

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
    private TableView<Service> table;

    @FXML
    private TableColumn<Service, String> name;

    @FXML
    private TableColumn<Service, Integer> id;

    @FXML
    private TableColumn<Service, Integer> price;

    @FXML
    private TableColumn<Service, String> description;

    @FXML
    public TableColumn<Service, Integer> employeeId;

    @FXML
    public TextField employeeIdInput;


    @FXML
    void initialize() {

        updateTable(serviceRepo.getAllEmployees());

        export.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
            File file = fileChooser.showSaveDialog(export.getScene().getWindow());
            if (file != null) {
                serviceRepo.saveFile(file);
            }
        });


        btnDelete.setOnAction(actionEvent -> {
            if (!table.getSelectionModel().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Delete" + " ?", ButtonType.YES, ButtonType.NO);
                alert1.showAndWait();
                if (alert1.getResult() == ButtonType.YES) {
                    delete();
                    updateTable(serviceRepo.getAllEmployees());
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Add " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    add();
                    updateTable(serviceRepo.getAllEmployees());
                    new Alert(Alert.AlertType.INFORMATION, "Added").show();
                }
                if (alert.getResult() == ButtonType.CANCEL) {
                    clearInput();
                }
            });
        });


        btnUpdate.setOnAction(actionEvent -> {
            if (!table.getItems().isEmpty()) {
                addPanel.setStyle("visibility: visible;");
                fillingInput();

                submit.setOnAction(action -> {
                    update();
                    updateTable(serviceRepo.getAllEmployees());
                    addPanel.setStyle("visibility: hidden;");
                    clearInput();
                    new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                });
            }
        });

        findInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!table.getItems().isEmpty()) {
                if (nameRadio.isSelected()) updateTable(serviceRepo.nameSearch(newValue));
                else if (surnameRadio.isSelected()) updateTable(serviceRepo.surnameSearch(newValue));
                else if (instructionRadio.isSelected()) updateTable(serviceRepo.instructionSearch(newValue));
            }
        });
    }

    private void update() {
        Service service = new Service();
        int id = table.getSelectionModel().getSelectedItem().getId();
        service.setId(Integer.valueOf(idInput.getText()));
        service.setName(nameInput.getText());
        service.setDescription(surnameInput.getText());
        service.setPrice(Integer.valueOf(instructionInput.getText()));
        serviceRepo.updateEmployeeById(id, service);
    }

    private void add() {
        Service service = new Service();

        service.setId(Integer.valueOf(idInput.getText()));
        service.setName(nameInput.getText());
        service.setDescription(surnameInput.getText());
        service.setPrice(Integer.valueOf(instructionInput.getText()));

        ReferenceTableEmployeeService employeeService = new ReferenceTableEmployeeService();
        employeeService.setService_id(service.getId());
        employeeService.setEmployee_id(Integer.valueOf(employeeIdInput.getText()));

        serviceRepo.saveEmployee(service, employeeService);
    }


    private void updateTable(List<Service> list) {
        ObservableList<Service> students = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(students);
    }

    private void delete() {
        serviceRepo.deleteEmployeeById(table.getSelectionModel().getSelectedItem().getId());
    }

    private void clearInput() {
        idInput.setText("");
        nameInput.setText("");
        surnameInput.setText("");
        instructionInput.setText("");
        employeeIdInput.setText("");
    }

    private void fillingInput() {
        idInput.setText(table.getSelectionModel().getSelectedItem().getId().toString());
        nameInput.setText(table.getSelectionModel().getSelectedItem().getName());
        surnameInput.setText(table.getSelectionModel().getSelectedItem().getDescription());
        instructionInput.setText(table.getSelectionModel().getSelectedItem().getPrice().toString());
    }
}
