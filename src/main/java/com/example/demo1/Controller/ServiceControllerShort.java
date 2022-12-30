package com.example.demo1.Controller;

import com.example.demo1.Model.Product;
import com.example.demo1.Model.Service;
import com.example.demo1.Repository.Impl.ProductRepoImpl;
import com.example.demo1.Repository.Impl.ServiceRepoImpl;
import com.example.demo1.Repository.ProductRepo;
import com.example.demo1.Repository.ServiceRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;

import java.util.List;

@Data
public class ServiceControllerShort {

    @FXML
    public TableView<Service> table;

    @FXML
    public TableColumn<Service, Integer> id;

    @FXML
    public TableColumn<Service, String> name;

    ServiceRepo productService = new ServiceRepoImpl();

    static public String filepath;

    @FXML
    void initialize(){
        updateTable(productService.getAllEmployees());
    }

    private void updateTable(List<Service> list) {
        ObservableList<Service> products = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(products);
    }
}
