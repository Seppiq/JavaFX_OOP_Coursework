package com.example.demo1.Controller;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.Service;
import com.example.demo1.Service.EmployeeService;
import com.example.demo1.Service.Impl.EmployeeServiceImpl;
import com.example.demo1.Service.Impl.ServiceServiceImpl;
import com.example.demo1.Service.ServiceService;
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
    private final ServiceService serviceService = new ServiceServiceImpl();

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

        if (Context.filepath != null)
            updateTable(serviceService.getAllEmployees());

        export.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
            File file = fileChooser.showSaveDialog(export.getScene().getWindow());
            if (file != null) {
                serviceService.saveFile(file);
            }
        });

        btnOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());
            if (file != null) {
                Context.filepath = file.getPath();
                updateTable(serviceService.getAllEmployees());
            }
        });


        btnDelete.setOnAction(actionEvent -> {
            if (!table.getSelectionModel().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Вы действительно хотите удалить" + " ?", ButtonType.YES, ButtonType.NO);
                alert1.showAndWait();
                if (alert1.getResult() == ButtonType.YES) {
                    delete();
                    updateTable(serviceService.getAllEmployees());
                    new Alert(Alert.AlertType.INFORMATION, "Запись удалена").show();
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Добавить " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    add();
                    updateTable(serviceService.getAllEmployees());
                    new Alert(Alert.AlertType.INFORMATION, "Запись добавлена").show();
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
                    updateTable(serviceService.getAllEmployees());
                    addPanel.setStyle("visibility: hidden;");
                    clearInput();
                    new Alert(Alert.AlertType.INFORMATION, "Запись обновлена").show();
                });
            }
        });

        findInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!table.getItems().isEmpty()) {
                if (nameRadio.isSelected()) updateTable(serviceService.nameSearch(newValue));
                else if (surnameRadio.isSelected()) updateTable(serviceService.surnameSearch(newValue));
                else if (instructionRadio.isSelected()) updateTable(serviceService.instructionSearch(newValue));
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
        service.setEmployeeId(Integer.valueOf(employeeIdInput.getText()));
        serviceService.updateEmployeeById(id, service);
    }

    private void add() {
        Service service = new Service();

        service.setId(Integer.valueOf(idInput.getText()));
        service.setName(nameInput.getText());
        service.setDescription(surnameInput.getText());
        service.setPrice(Integer.valueOf(instructionInput.getText()));
        service.setEmployeeId(Integer.valueOf(employeeIdInput.getText()));
        serviceService.createEmployee(service);
    }


    private void updateTable(List<Service> list) {
        ObservableList<Service> students = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        table.setItems(students);
    }

    private void delete() {
        serviceService.deleteEmployeeById(table.getSelectionModel().getSelectedItem().getId());
    }

    private void clearInput() {
        idInput.setText("");
        nameInput.setText("");
        surnameInput.setText("");
        instructionInput.setText("");
    }

    private void fillingInput() {
        idInput.setText(table.getSelectionModel().getSelectedItem().getId().toString());
        nameInput.setText(table.getSelectionModel().getSelectedItem().getName());
        surnameInput.setText(table.getSelectionModel().getSelectedItem().getDescription());
        instructionInput.setText(table.getSelectionModel().getSelectedItem().getPrice().toString());
        employeeIdInput.setText(table.getSelectionModel().getSelectedItem().getEmployeeId().toString());
    }
}
