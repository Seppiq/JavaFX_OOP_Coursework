package com.example.demo1.Service;

import com.example.demo1.Model.Product;

import java.io.File;
import java.util.List;

public interface ProductService {

    List<Product> instructionSearch(String str);

    List<Product> nameSearch(String str);

    List<Product> surnameSearch(String str);

    Product getProductById(int id);

    List<Product> getAllEmployees();

    void createEmployee(Product employee);

    void updateEmployeeById(int id, Product employee);

    void deleteEmployeeById(int id);

    void saveFile(File saveFile);
}
