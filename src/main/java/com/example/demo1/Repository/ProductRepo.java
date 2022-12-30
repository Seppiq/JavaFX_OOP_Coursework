package com.example.demo1.Repository;

import com.example.demo1.Model.Product;
import com.example.demo1.Model.ReferenceTableEmployeeProduct;

import java.io.File;
import java.util.List;

public interface ProductRepo {

    List<Product> instructionSearch(String str);

    List<Product> nameSearch(String str);

    List<Product> surnameSearch(String str);

    List<Product> getAllProducts();

    void deleteProductById(int id);

    void updateProductById(int id, Product product);

    void saveEmployee(Product product, ReferenceTableEmployeeProduct referenceTableEmployeeProduct);

    public void saveFile(File saveFile);

    Product getProductById(int id);
}
