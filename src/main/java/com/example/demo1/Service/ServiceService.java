package com.example.demo1.Service;

import com.example.demo1.Model.Service;

import java.io.File;
import java.util.List;

public interface ServiceService {

    List<Service> instructionSearch(String str);

    List<Service> nameSearch(String str);

    List<Service> surnameSearch(String str);

    Service getEmployeeById(int id);

    List<Service> getAllEmployees();

    void createEmployee(Service employee);

    void updateEmployeeById(int id, Service employee);

    void deleteEmployeeById(int id);

    void saveFile(File saveFile);
}
