package com.example.demo1.Service.Impl;


import com.example.demo1.Model.Employee;
import com.example.demo1.Repository.Impl.EmployeeRepositoryImpl;
import com.example.demo1.Repository.EmployeeRepository;
import com.example.demo1.Service.EmployeeService;

import java.io.File;
import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository studentRepository = new EmployeeRepositoryImpl();

    @Override
    public Employee getEmployeeById(int id) {

        return studentRepository.getStudentById(id);
    }

    @Override
    public List<Employee> instructionSearch(String str) {
        return studentRepository.instructionSearch(str);
    }

    @Override
    public List<Employee> surnameSearch(String str) {
        return studentRepository.surnameSearch(str);
    }

    @Override
    public List<Employee> nameSearch(String str) {
        return studentRepository.nameSearch(str);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return studentRepository.getAllEmployees();
    }

    @Override
    public void createEmployee(Employee employee) {
        studentRepository.saveEmployee(employee);
    }

    @Override
    public void updateEmployeeById(int id, Employee employee) {
        studentRepository.updateEmployeeById(id, employee);
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
