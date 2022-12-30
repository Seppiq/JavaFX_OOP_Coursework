package com.example.demo1.Repository.Impl;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.ReferenceTableEmployeeService;
import com.example.demo1.Repository.EmployeeProductRepository;
import com.example.demo1.Repository.EmployeeRepository;
import com.example.demo1.Repository.EmployeeServiceRepository;
import com.example.demo1.Repository.ServiceRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceRepositoryImpl implements EmployeeServiceRepository {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public List<ReferenceTableEmployeeService> getAll() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Context.employeeServiceFilePath));

            Type ref = new TypeToken<List<ReferenceTableEmployeeService>>() {
            }.getType();
            List<ReferenceTableEmployeeService> student_instructions = gson.fromJson(reader, ref);
            return student_instructions;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void deleteByEmployeeId(int id) {
        try {
            ServiceRepo serviceRepo = new ServiceRepoImpl();
            int tempId;
            List<ReferenceTableEmployeeService> employeeServices = new ArrayList<>(getAll());

            for (int i = 0; i < employeeServices.size(); i++) {
                if (employeeServices.get(i).getEmployee_id() == id) {
                    tempId = employeeServices.get(i).getService_id();
                    employeeServices.remove(i);
                    i--;

                    PrintWriter out = new PrintWriter(new FileWriter(Context.employeeServiceFilePath));
                    out.write(gson.toJson(employeeServices));
                    out.close();

                    if (!isPresentService(tempId, employeeServices))
                        serviceRepo.deleteEmployeeById(tempId);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void deleteByServiceId(int id) {
        try {
            EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

            int tempId;
            List<ReferenceTableEmployeeService> referenceTableEmployeeServices = new ArrayList<>(getAll());
            for (int i = 0; i < referenceTableEmployeeServices.size(); i++) {
                if (referenceTableEmployeeServices.get(i).getService_id() == id) {
                    tempId = referenceTableEmployeeServices.get(i).getEmployee_id();
                    referenceTableEmployeeServices.remove(i);
                    i--;

                    PrintWriter out = new PrintWriter(new FileWriter(Context.employeeServiceFilePath));
                    out.write(gson.toJson(referenceTableEmployeeServices));
                    out.close();

                    if (!isPresentEmployee(tempId, referenceTableEmployeeServices))
                        employeeRepository.deleteEmployeeById(tempId);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public boolean isPresentEmployee(int id, List<ReferenceTableEmployeeService> list) {
        for (ReferenceTableEmployeeService employeeService : list) {
            if (employeeService.getEmployee_id() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean isPresentService(int id, List<ReferenceTableEmployeeService> list) {
        for (ReferenceTableEmployeeService empl : list) {
            if (empl.getService_id() == id)
                return true;
        }
        return false;
    }

    @Override
    public void save(ReferenceTableEmployeeService referenceTableEmployeeService) {
        try {
            List<ReferenceTableEmployeeService> employeeServices = new ArrayList<>(getAll());
            employeeServices.add(referenceTableEmployeeService);
            PrintWriter out = new PrintWriter(new FileWriter(Context.employeeServiceFilePath));
            out.write(gson.toJson(employeeServices));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
