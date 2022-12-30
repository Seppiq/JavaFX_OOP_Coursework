package com.example.demo1.Repository.Impl;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.Employee;
import com.example.demo1.Model.ReferenceTableEmployeeProduct;
import com.example.demo1.Model.ReferenceTableEmployeeService;
import com.example.demo1.Repository.EmployeeProductRepository;
import com.example.demo1.Repository.EmployeeRepository;
import com.example.demo1.Repository.EmployeeServiceRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeProductRepository employeeProductRepository = new EmployeeProductRepositoryImpl();

    private final EmployeeServiceRepository employeeServiceRepository = new EmployeeServiceRepositoryImpl();
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public List<Employee> nameSearch(String str) {
        List<Employee> employees = new ArrayList<>(getAllEmployees());
        employees.removeIf(elem -> !elem.getFirstName().toLowerCase().startsWith(str.toLowerCase()));
        return employees;
    }

    @Override
    public List<Employee> surnameSearch(String str) {
        List<Employee> employees = new ArrayList<>(getAllEmployees());
        employees.removeIf(elem -> !elem.getLastName().toLowerCase().startsWith(str.toLowerCase()));
        return employees;
    }

    @Override
    public List<Employee> search(String str) {
        List<Employee> employees = new ArrayList<>(getAllEmployees());
        employees.removeIf(elem -> !elem.getAge().toString().startsWith(str));
        return employees;
    }

    @Override
    public void updateEmployeeById(int id, Employee employee) {
        Employee copy = getEmployeeById(id);
        copy.setId(employee.getId());
        copy.setFirstName(employee.getFirstName());
        copy.setLastName(employee.getLastName());
        copy.setAge(employee.getAge());

        List<Employee> heads = new ArrayList<>(getAllEmployees());
        try {
            for (int i = 0; i < heads.size(); i++) {
                if (heads.get(i).getId() == id) {
                    heads.remove(i);
                    heads.add(i, copy);
                }
            }
            PrintWriter out = new PrintWriter(new FileWriter(Context.employeeFilePath));
            out.write(gson.toJson(heads));
            out.close();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void saveEmployee(Employee employee,
                             ReferenceTableEmployeeService referenceTableEmployeeService,
                             ReferenceTableEmployeeProduct referenceTableEmployeeProduct) {
        try {
            List<Employee> employees = new ArrayList<>(getAllEmployees());
            if (getEmployeeById(employee.getId()) == null) {
                employeeProductRepository.save(referenceTableEmployeeProduct);
                employeeServiceRepository.save(referenceTableEmployeeService);
                employees.add(employee);
                PrintWriter out = new PrintWriter(new FileWriter(Context.employeeFilePath));
                out.write(gson.toJson(employees));
                out.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Context.employeeFilePath));

            Type employeeListType = new TypeToken<List<Employee>>() {
            }.getType();
            List<Employee> employeeList = gson.fromJson(reader, employeeListType);
            return employeeList;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(int id) {
        try {
            List<Employee> employees = new ArrayList<>(getAllEmployees());

            for (int i = 0; i < employees.size(); i++)
                if (employees.get(i).getId() == id) {
                    employees.remove(i);
                    employeeProductRepository.deleteByEmployeeId(employees.get(i).getId());
                    employeeServiceRepository.deleteByServiceId(employees.get(i).getId());
                }
            PrintWriter out = new PrintWriter(new FileWriter(Context.employeeFilePath));
            out.write(gson.toJson(employees));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void saveFile(File saveFile) {
        List<Employee> students = new ArrayList<>(getAllEmployees());
        try {
            PrintWriter out = new PrintWriter(new FileWriter(saveFile.getPath()));
            out.write(gson.toJson(students));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try {
            List<Employee> employees = new ArrayList<>(getAllEmployees());
            for (Employee employee : employees) {
                if (employee.getId() == id) {
                    return employee;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
