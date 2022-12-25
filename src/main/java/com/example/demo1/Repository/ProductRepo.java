package com.example.demo1.Repository;

import com.example.demo1.Model.Product;

import java.io.File;
import java.util.List;

public interface ProductRepo {

    List<Product> instructionSearch(String str);

    List<Product> nameSearch(String str);

    List<Product> surnameSearch(String str);

    List<Product> getAllProducts() ;

    void deleteEmployeeById(int id);

    void updateProductById(int id, Product employee);

    void saveEmployee(Product employee);

    public void saveFile(File saveFile);

    Product getProductById(int id);
}
