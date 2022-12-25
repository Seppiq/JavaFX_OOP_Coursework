package com.example.demo1.Repository.Impl;

import com.example.demo1.Controller.ServiceController;
import com.example.demo1.Model.Employee;
import com.example.demo1.Model.Product;
import com.example.demo1.Model.Service;
import com.example.demo1.Repository.EmployeeRepository;
import com.example.demo1.Repository.ServiceRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepoImpl implements ServiceRepo {

    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

    @Override
    public List<Service> instructionSearch(String str) {
        List<Service> services = new ArrayList<>(getAllEmployees());
        services.removeIf(element -> !element.getEmployeeId().toString().startsWith(str));
        return services;
    }

    @Override
    public List<Service> nameSearch(String str) {
        List<Service> services = new ArrayList<>(getAllEmployees());
        services.removeIf(element -> !element.getName().toLowerCase().startsWith(str.toLowerCase()));
        return services;
    }

    @Override
    public List<Service> surnameSearch(String str) {
        List<Service> services = new ArrayList<>(getAllEmployees());
        services.removeIf(element -> !element.getDescription().toLowerCase().startsWith(str.toLowerCase()));
        return services;
    }

    @Override
    public List<Service> getAllEmployees() {
        try {
            List<Employee> allEmployees = employeeRepository.getAllEmployees();
            List<Service> productList = new ArrayList<>();
            for (Employee employee : allEmployees)
                productList.addAll(employee.getServices());
            return productList;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(int id) {
        try {
            Service product = getServiceById(id);
            Employee employee = employeeRepository.getStudentById(product.getEmployeeId());
            List<Service> products = new ArrayList<>(employee.getServices());

            for (int i = 0; i < products.size(); i++)
                if (products.get(i).getId() == id)
                    products.remove(i);

            employee.setServices(products);
            employeeRepository.updateEmployeeById(employee.getId(), employee);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void updateEmployeeById(int id, Service employee) {
        Service copy = getServiceById(id);
        Employee employee1 = employeeRepository.getStudentById(employee.getEmployeeId());
        copy.setId(employee.getId());
        copy.setName(employee.getName());
        copy.setDescription(employee.getDescription());
        copy.setPrice(employee.getPrice());
        copy.setEmployeeId(employee.getEmployeeId());

        try {
            List<Service> products = new ArrayList<>(getAllEmployees());

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == id) {
                    products.remove(i);
                    products.add(i, copy);
                }
            }
            if (employeeRepository.getStudentById(employee.getEmployeeId()) != null) {
                employee1.setServices(products);
                employeeRepository.updateEmployeeById(employee1.getId(), employee1);
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void saveEmployee(Service service) {
        try {
            Employee employee = employeeRepository.getStudentById(service.getEmployeeId());
            List<Service> productList = new ArrayList<>();
            if (employee.getServices() != null)
                productList = new ArrayList<>(employee.getServices());
            if (getServiceById(service.getId()) == null &&
                    employeeRepository.getStudentById(service.getEmployeeId()) != null) {
                productList.add(service);
                employee.setServices(productList);
                employeeRepository.updateEmployeeById(employee.getId(), employee);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void saveFile(File saveFile) {
        Gson gson = new Gson();
        List<Service> students = new ArrayList<>(getAllEmployees());
        try {
            PrintWriter out = new PrintWriter(new FileWriter(saveFile.getPath()));
            out.write(gson.toJson(students));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public Service getServiceById(int id) {
        try {
            List<Service> products = new ArrayList<>(getAllEmployees());
            for (Service product : products) {
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
