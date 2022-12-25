package com.example.demo1.Service.Impl;

import com.example.demo1.Model.Product;
import com.example.demo1.Repository.Impl.ProductRepoImpl;
import com.example.demo1.Repository.ProductRepo;
import com.example.demo1.Service.ProductService;

import java.io.File;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepo studentRepository = new ProductRepoImpl();

    @Override
    public Product getProductById(int id) {

        return studentRepository.getProductById(id);
    }

    @Override
    public List<Product> instructionSearch(String str) {
        return studentRepository.instructionSearch(str);
    }

    @Override
    public List<Product> surnameSearch(String str) {
        return studentRepository.surnameSearch(str);
    }

    @Override
    public List<Product> nameSearch(String str) {
        return studentRepository.nameSearch(str);
    }

    @Override
    public List<Product> getAllEmployees() {
        return studentRepository.getAllProducts();
    }

    @Override
    public void createEmployee(Product employee) {
        studentRepository.saveEmployee(employee);
    }

    @Override
    public void updateEmployeeById(int id, Product employee) {
        studentRepository.updateProductById(id, employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        studentRepository.deleteEmployeeById(id);
    }

    @Override
    public void saveFile(File saveFile) {
        studentRepository.saveFile(saveFile);
    }
}
