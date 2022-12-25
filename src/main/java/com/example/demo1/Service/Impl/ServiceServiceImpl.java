package com.example.demo1.Service.Impl;

import com.example.demo1.Model.Service;
import com.example.demo1.Repository.Impl.ServiceRepoImpl;
import com.example.demo1.Repository.ServiceRepo;
import com.example.demo1.Service.ServiceService;

import java.io.File;
import java.util.List;

public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepo studentRepository = new ServiceRepoImpl();

    @Override
    public Service getEmployeeById(int id) {

        return studentRepository.getServiceById(id);
    }

    @Override
    public List<Service> instructionSearch(String str) {
        return studentRepository.instructionSearch(str);
    }

    @Override
    public List<Service> surnameSearch(String str) {
        return studentRepository.surnameSearch(str);
    }

    @Override
    public List<Service> nameSearch(String str) {
        return studentRepository.nameSearch(str);
    }

    @Override
    public List<Service> getAllEmployees() {
        return studentRepository.getAllEmployees();
    }

    @Override
    public void createEmployee(Service employee) {
        studentRepository.saveEmployee(employee);
    }

    @Override
    public void updateEmployeeById(int id, Service employee) {
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
