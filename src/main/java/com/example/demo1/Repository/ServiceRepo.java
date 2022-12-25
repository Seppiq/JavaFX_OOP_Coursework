package com.example.demo1.Repository;

import com.example.demo1.Model.Service;

import java.io.File;
import java.util.List;

public interface ServiceRepo {

    List<Service> instructionSearch(String str);

    List<Service> nameSearch(String str);

    List<Service> surnameSearch(String str);

    List<Service> getAllEmployees();

    void deleteEmployeeById(int id);

    void updateEmployeeById(int id, Service employee);

    void saveEmployee(Service employee);

    public void saveFile(File saveFile);

    Service getServiceById(int id);
}
