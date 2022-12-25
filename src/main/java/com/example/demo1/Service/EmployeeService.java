package com.example.demo1.Service;


import com.example.demo1.Model.Employee;

import java.io.File;
import java.util.List;

public interface EmployeeService {

    List<Employee> instructionSearch(String str);

    List<Employee> nameSearch(String str);

    List<Employee> surnameSearch(String str);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    void createEmployee(Employee employee);

    void updateEmployeeById(int id, Employee employee);

    void deleteEmployeeById(int id);

    void saveFile(File saveFile);
}
