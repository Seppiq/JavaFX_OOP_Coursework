package com.example.demo1.Repository;


import com.example.demo1.Model.Employee;
import com.example.demo1.Model.ReferenceTableEmployeeProduct;
import com.example.demo1.Model.ReferenceTableEmployeeService;

import java.io.File;
import java.util.List;

public interface EmployeeRepository {

    List<Employee> search(String str);

    List<Employee> nameSearch(String str);

    List<Employee> surnameSearch(String str);

    List<Employee> getAllEmployees() ;

    void deleteEmployeeById(int id);

    void updateEmployeeById(int id, Employee employee);

    void saveEmployee(Employee employee ,
                      ReferenceTableEmployeeService referenceTableEmployeeService,
                      ReferenceTableEmployeeProduct referenceTableEmployeeProduct);

    public void saveFile(File saveFile);

    Employee getEmployeeById(int id);
}
