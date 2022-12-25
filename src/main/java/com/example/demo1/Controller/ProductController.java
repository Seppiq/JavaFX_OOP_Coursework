package com.example.demo1.Controller;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.Product;
import com.example.demo1.Service.EmployeeService;
import com.example.demo1.Service.Impl.EmployeeServiceImpl;
import com.example.demo1.Service.Impl.ProductServiceImpl;
import com.example.demo1.Service.ProductService;
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
public class ProductController {
    private final ProductService productService = new ProductServiceImpl();

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    static public String filepath;
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
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, String> name;

    @FXML
    private TableColumn<Product, Integer> id;

    @FXML
    private TableColumn<Product, Integer> quantity;

    @FXML
    private TableColumn<Product, String> description;

    @FXML
    private TableColumn<Product, Integer> employeeId;

    @FXML
    private TextField employeeIdInput;

    @FXML
    void initialize() {

        if (Context.filepath != null)
            updateTable(productService.getAllEmployees());


        export.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
            File file = fileChooser.showSaveDialog(export.getScene().getWindow());
            if (file != null) {
                productService.saveFile(file);
            }
        });

        btnOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());
            if (file != null) {
                filepath = file.getPath();
                updateTable(productService.getAllEmployees());
            }
        });


        btnDelete.setOnAction(actionEvent -> {
            if (!table.getSelectionModel().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Delete" + " ?", ButtonType.YES, ButtonType.NO);
                alert1.showAndWait();
                if (alert1.getResult() == ButtonType.YES) {
                    delete();
                    updateTable(productService.getAllEmployees());
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
                    updateTable(productService.getAllEmployees());
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
                    updateTable(productService.getAllEmployees());
                    addPanel.setStyle("visibility: hidden;");
                    clearInput();
                    new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                });
            }
        });

        findInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (nameRadio.isSelected()) updateTable(productService.nameSearch(newValue));
            else if (surnameRadio.isSelected()) updateTable(productService.surnameSearch(newValue));
            else if (instructionRadio.isSelected()) updateTable(productService.instructionSearch(newValue));
        });
    }

    private void update() {
        Product product = new Product();
        int id = table.getSelectionModel().getSelectedItem().getId();
        product.setId(Integer.valueOf(idInput.getText()));
        product.setName(nameInput.getText());
        product.setDescription(surnameInput.getText());
        product.setQuantity(Integer.valueOf(instructionInput.getText()));
        product.setEmployeeId(Integer.valueOf(employeeIdInput.getText()));
        productService.updateEmployeeById(id, product);
    }

    private void add() {
        Product product = new Product();

        product.setId(Integer.valueOf(idInput.getText()));
        product.setName(nameInput.getText());
        product.setDescription(surnameInput.getText());
        product.setQuantity(Integer.valueOf(instructionInput.getText()));
        product.setEmployeeId(Integer.valueOf(employeeIdInput.getText()));
        productService.createEmployee(product);
    }


    private void updateTable(List<Product> list) {
        ObservableList<Product> products = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        table.setItems(products);
    }

    private void delete() {
        productService.deleteEmployeeById(table.getSelectionModel().getSelectedItem().getId());
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
        instructionInput.setText(table.getSelectionModel().getSelectedItem().getQuantity().toString());
        employeeIdInput.setText(table.getSelectionModel().getSelectedItem().getEmployeeId().toString());
    }
}
