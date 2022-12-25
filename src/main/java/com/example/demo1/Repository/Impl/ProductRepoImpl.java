package com.example.demo1.Repository.Impl;

import com.example.demo1.Model.Employee;
import com.example.demo1.Model.Product;
import com.example.demo1.Repository.EmployeeRepository;
import com.example.demo1.Repository.ProductRepo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductRepoImpl implements ProductRepo {

    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

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
            List<Employee> allEmployees = employeeRepository.getAllEmployees();
            List<Product> productList = new ArrayList<>();
            for (Employee employee : allEmployees)
                productList.addAll(employee.getProducts());
            return productList;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(int id) {
        try {
            Product product = getProductById(id);
            Employee employee = employeeRepository.getStudentById(product.getEmployeeId());
            List<Product> products = new ArrayList<>(employee.getProducts());

            for (int i = 0; i < products.size(); i++)
                if (products.get(i).getId() == id)
                    products.remove(i);

            employee.setProducts(products);
            employeeRepository.updateEmployeeById(employee.getId(), employee);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void updateProductById(int id, Product product) {
        Product copy = getProductById(id);
        Employee employee = employeeRepository.getStudentById(product.getEmployeeId());
        copy.setId(product.getId());
        copy.setName(product.getName());
        copy.setDescription(product.getDescription());
        copy.setQuantity(product.getQuantity());
        copy.setEmployeeId(product.getEmployeeId());
        try {
            List<Product> products = new ArrayList<>(getAllProducts());

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == id) {
                    products.remove(i);
                    products.add(i, copy);
                }
            }
            if (employeeRepository.getStudentById(product.getEmployeeId()) != null) {
                employee.setProducts(products);
                employeeRepository.updateEmployeeById(employee.getId(), employee);
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void saveEmployee(Product product) {
        try {
            Employee employee = employeeRepository.getStudentById(product.getEmployeeId());
            List<Product> productList = new ArrayList<>();
            if (employee.getProducts() != null)
                productList = new ArrayList<>(employee.getProducts());
            if (getProductById(product.getId()) == null &&
                    employeeRepository.getStudentById(product.getEmployeeId()) != null) {
                productList.add(product);
                employee.setProducts(productList);
                employeeRepository.updateEmployeeById(employee.getId(), employee);
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
