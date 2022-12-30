package com.example.demo1.Repository.Impl;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.Employee;
import com.example.demo1.Model.ReferenceTableEmployeeService;
import com.example.demo1.Model.Service;
import com.example.demo1.Repository.EmployeeRepository;
import com.example.demo1.Repository.EmployeeServiceRepository;
import com.example.demo1.Repository.ServiceRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepoImpl implements ServiceRepo {

    EmployeeServiceRepository employeeServiceRepository = new EmployeeServiceRepositoryImpl();
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public List<Service> instructionSearch(String str) {
        List<Service> services = new ArrayList<>(getAllEmployees());
        services.removeIf(element -> !element.getId().toString().startsWith(str));
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
            BufferedReader reader = new BufferedReader(new FileReader(Context.serviceFilePath));

            Type serviceTypeList = new TypeToken<List<Service>>() {
            }.getType();
            List<Service> services = gson.fromJson(reader, serviceTypeList);
            return services;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(int id) {
        try {

            List<Service> services = new ArrayList<>(getAllEmployees());

            for (int i = 0; i < services.size(); i++)
                if (services.get(i).getId() == id) {
                    employeeServiceRepository.deleteByServiceId(services.get(i).getId());
                    services.remove(i);
                    break;
                }
            PrintWriter out = new PrintWriter(new FileWriter(Context.serviceFilePath));
            out.write(gson.toJson(services));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void updateEmployeeById(int id, Service service) {
        Service copy = getServiceById(id);
        copy.setId(service.getId());
        copy.setName(service.getName());
        copy.setDescription(service.getDescription());
        copy.setPrice(service.getPrice());
        try {
            List<Service> instructions = new ArrayList<>(getAllEmployees());

            for (int i = 0; i < instructions.size(); i++) {
                if (instructions.get(i).getId() == id) {
                    instructions.remove(i);
                    instructions.add(i, copy);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void saveEmployee(Service service, ReferenceTableEmployeeService
            referenceTableEmployeeService) {
        try {

            List<Service> instructions = new ArrayList<>(getAllEmployees());

            if (getServiceById(service.getId()) == null) {
                instructions.add(service);
                employeeServiceRepository.save(referenceTableEmployeeService);
                PrintWriter out = new PrintWriter(new FileWriter(Context.serviceFilePath));
                out.write(gson.toJson(instructions));
                out.close();
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
