package com.example.demo1.Controller;

import com.example.demo1.Model.Product;
import com.example.demo1.Repository.Impl.ProductRepoImpl;
import com.example.demo1.Repository.ProductRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;

import java.util.List;

@Data
public class ProductControllerShort {

    @FXML
    public TableView<Product> table;

    @FXML
    public TableColumn<Product, Integer> id;

    @FXML
    public TableColumn<Product, String> name;

    ProductRepo productService = new ProductRepoImpl();

    static public String filepath;

    @FXML
    void initialize(){
        updateTable(productService.getAllProducts());
    }

    private void updateTable(List<Product> list) {
        ObservableList<Product> products = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(products);
    }

}
