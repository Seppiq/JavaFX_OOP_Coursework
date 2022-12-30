package com.example.demo1.Repository.Impl;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.Employee;
import com.example.demo1.Model.Product;
import com.example.demo1.Model.ReferenceTableEmployeeProduct;
import com.example.demo1.Repository.EmployeeProductRepository;
import com.example.demo1.Repository.ProductRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductRepoImpl implements ProductRepo {

    EmployeeProductRepository employeeProductRepository = new EmployeeProductRepositoryImpl();

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public List<Product> instructionSearch(String str) {
        List<Product> students = new ArrayList<>(getAllProducts());
        students.removeIf(element -> !element.getDescription().toLowerCase().startsWith(str.toLowerCase()));
        return students;
    }

    @Override
    public List<Product> nameSearch(String str) {
        List<Product> students = new ArrayList<>(getAllProducts());
        students.removeIf(element -> !element.getName().toLowerCase().startsWith(str.toLowerCase()));
        return students;
    }

    @Override
    public List<Product> surnameSearch(String str) {
        List<Product> students = new ArrayList<>(getAllProducts());
        students.removeIf(element -> !element.getId().toString().startsWith(str.toLowerCase()));
        return students;
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Context.productFilePath));

            Type productTypeList = new TypeToken<List<Product>>() {
            }.getType();
            List<Product> productList = gson.fromJson(reader, productTypeList);
            return productList;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void deleteProductById(int id) {
        try {
            List<Product> products = new ArrayList<>(getAllProducts());

            for (int i = 0; i < products.size(); i++)
                if (products.get(i).getId() == id) {
                    employeeProductRepository.deleteByEmployeeId(products.get(i).getId());
                    products.remove(i);
                    break;
                }

            PrintWriter out = new PrintWriter(new FileWriter(Context.productFilePath));
            out.write(gson.toJson(products));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void updateProductById(int id, Product product) {
        Product copy = getProductById(id);
        copy.setId(product.getId());
        copy.setName(product.getName());
        copy.setDescription(product.getDescription());
        copy.setQuantity(product.getQuantity());
        try {
            List<Product> products = new ArrayList<>(getAllProducts());
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == id) {
                    products.remove(i);
                    products.add(i, copy);
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void saveEmployee(Product product, ReferenceTableEmployeeProduct referenceTableEmployeeProduct) {
        try {
            List<Product> products = new ArrayList<>(getAllProducts());
            if (getProductById(product.getId()) == null) {
                products.add(product);
                employeeProductRepository.save(referenceTableEmployeeProduct);
                PrintWriter out = new PrintWriter(new FileWriter(Context.productFilePath));
                out.write(gson.toJson(products));
                out.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void saveFile(File saveFile) {
//        //Gson gson = new Gson();
//        List<Product> students = new ArrayList<>(getAllProducts());
//        try {
//            PrintWriter out = new PrintWriter(new FileWriter(saveFile.getPath()));
//            out.write(gson.toJson(students));
//            out.close();
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
    }

    @Override
    public Product getProductById(int id) {
        try {
            List<Product> products = new ArrayList<>(getAllProducts());
            for (Product product : products) {
                if (product.getId() == id) {
                    return product;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
