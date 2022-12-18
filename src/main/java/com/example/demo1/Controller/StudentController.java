package com.example.demo1.Controller;


import com.example.demo1.Model.Student;
import com.example.demo1.Service.Impl.StudentServiceImpl;
import com.example.demo1.Service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class StudentController {

    private final StudentService studentService = new StudentServiceImpl();

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
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, String> firstName;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, Integer> idInstruction;

    @FXML
    private TableColumn<Student, String> lastName;

    public StudentController() {
    }

    @FXML
    void initialize() {

        export.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
            File file = fileChooser.showSaveDialog(export.getScene().getWindow());
            if (file != null) {
                studentService.saveFile(file);
            }
        });

        btnOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());
            if (file != null) {
                filepath = file.getPath();
                updateTable(studentService.getAllStudents());
            }
        });


        btnDelete.setOnAction(actionEvent -> {
            if (!table.getItems().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Вы действительно хотите удалить" + " ?", ButtonType.YES, ButtonType.NO);
                alert1.showAndWait();
                if (alert1.getResult() == ButtonType.YES) {
                    delete();
                    updateTable(studentService.getAllStudents());
                    new Alert(Alert.AlertType.INFORMATION, "Запись удалена").show();
                }
            } else if (table.getItems().isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Table is empty").show();
            }
        });

        cancel.setOnAction(action -> {
            if (!table.getItems().isEmpty()) {
                clearInput();
                addPanel.setStyle("visibility: hidden;");
            }
        });

        btnAdd.setOnAction(actionEvent -> {
            addPanel.setStyle("visibility: visible");

            submit.setOnAction(action -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Добавить " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    add();
                    updateTable(studentService.getAllStudents());
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
                    updateTable(studentService.getAllStudents());
                    addPanel.setStyle("visibility: hidden;");
                    clearInput();
                });
            }
        });

        findInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!table.getItems().isEmpty()) {
                if (nameRadio.isSelected()) updateTable(studentService.nameSearch(newValue));
                else if (surnameRadio.isSelected()) updateTable(studentService.surnameSearch(newValue));
                else if (instructionRadio.isSelected()) updateTable(studentService.instructionSearch(newValue));
            }
        });
    }

    private void update() {
        Student student = new Student();
        int id = table.getSelectionModel().getSelectedItem().getId();
        student.setId(Integer.valueOf(idInput.getText()));
        student.setFirstName(nameInput.getText());
        student.setLastName(surnameInput.getText());
        student.setIdInstruction(Integer.valueOf(instructionInput.getText()));
        studentService.updateStudentById(id, student);
    }

    private void add() {
        Student student = new Student();

        student.setId(Integer.valueOf(idInput.getText()));
        student.setFirstName(nameInput.getText());
        student.setLastName(surnameInput.getText());
        student.setIdInstruction(Integer.valueOf(instructionInput.getText()));
        studentService.createStudent(student);
    }


    private void updateTable(List<Student> list) {
        ObservableList<Student> students = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idInstruction.setCellValueFactory(new PropertyValueFactory<>("idInstruction"));
        table.setItems(students);
    }

    private void delete() {
        studentService.deleteStudentById(table.getSelectionModel().getSelectedItem().getId());
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
        instructionInput.setText(table.getSelectionModel().getSelectedItem().getIdInstruction().toString());
    }
}
