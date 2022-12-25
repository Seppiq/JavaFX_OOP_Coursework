package com.example.demo1.Repository.Impl;

import com.example.demo1.Controller.EmployeeController;
import com.example.demo1.Model.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.example.demo1.Model.Employee;
import com.example.demo1.Repository.EmployeeRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRepositoryImpl implements EmployeeRepository {

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
    public List<Employee> instructionSearch(String str) {
        List<Employee> employees = new ArrayList<>(getAllEmployees());
        employees.removeIf(elem -> !elem.getAge().toString().startsWith(str));
        return employees;
    }

    @Override
    public void updateEmployeeById(int id, Employee employee) {
        Employee copy = getStudentById(id);
        copy.setId(employee.getId());
        copy.setFirstName(employee.getFirstName());
        copy.setLastName(employee.getLastName());
        copy.setAge(employee.getAge());
        copy.setServices(employee.getServices());
        copy.setProducts(employee.getProducts());

        List<Employee> heads = new ArrayList<>(getAllEmployees());
        try {
            for (int i = 0; i < heads.size(); i++) {
                if (heads.get(i).getId() == id) {
                    heads.remove(i);
                    heads.add(i, copy);
                }
            }
            PrintWriter out = new PrintWriter(new FileWriter(Context.filepath));
            out.write(gson.toJson(heads));
            out.close();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Context.filepath));

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
                if (employees.get(i).getId() == id)
                    employees.remove(i);

            PrintWriter out = new PrintWriter(new FileWriter(Context.filepath));
            out.write(gson.toJson(employees));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void saveEmployee(Employee employee) {
        try {
            if (Context.filepath == null) {
                File file = new File("employee.json");
                PrintWriter writer = new PrintWriter(file);
                writer.write("[]");
                writer.close();
                Context.filepath = file.getPath();
            }

            List<Employee> employees = new ArrayList<>(getAllEmployees());
            if (getStudentById(employee.getId()) == null) {
                employees.add(employee);
                PrintWriter out = new PrintWriter(new FileWriter(Context.filepath));
                out.write(gson.toJson(employees));
                out.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void saveFile(File saveFile) {
        //Gson gson = new Gson();
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
    public Employee getStudentById(int id) {
        //Gson gson = new Gson();
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
