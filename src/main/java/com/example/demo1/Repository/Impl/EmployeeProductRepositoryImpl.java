package com.example.demo1.Repository.Impl;

import com.example.demo1.Model.Context;
import com.example.demo1.Model.ReferenceTableEmployeeProduct;
import com.example.demo1.Repository.EmployeeProductRepository;
import com.example.demo1.Repository.EmployeeRepository;
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

public class EmployeeProductRepositoryImpl implements EmployeeProductRepository {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    @Override
    public List<ReferenceTableEmployeeProduct> getAll() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Context.employeeProductFilepath));

            Type headstudentlisttype = new TypeToken<List<ReferenceTableEmployeeProduct>>() {
            }.getType();
            List<ReferenceTableEmployeeProduct> referenceTableEmployeeProducts = gson.fromJson(reader, headstudentlisttype);
            return referenceTableEmployeeProducts;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public void deleteByEmployeeId(int id) {
        try {
            EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

            int tempId;
            List<ReferenceTableEmployeeProduct> employeeProducts = new ArrayList<>(getAll());
            for (int i = 0; i < employeeProducts.size(); i++) {
                if (employeeProducts.get(i).getEmployee_id() == id) {
                    tempId = employeeProducts.get(i).getProduct_id();
                    employeeProducts.remove(i);
                    i--;

                    PrintWriter out = new PrintWriter(new FileWriter(Context.employeeProductFilepath));
                    out.write(gson.toJson(employeeProducts));
                    out.close();

                    if (!isPresentProduct(tempId, employeeProducts))
                        employeeRepository.deleteEmployeeById(tempId);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void deleteByProductId(int id) {
        try {
            EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
            int tempId;
            List<ReferenceTableEmployeeProduct> employeeProducts = new ArrayList<>(getAll());

            for (int i = 0; i < employeeProducts.size(); i++) {
                if (employeeProducts.get(i).getProduct_id() == id) {
                    tempId = employeeProducts.get(i).getEmployee_id();
                    employeeProducts.remove(i);
                    i--;

                    PrintWriter out = new PrintWriter(new FileWriter(Context.employeeProductFilepath));
                    out.write(gson.toJson(employeeProducts));
                    out.close();

                    if (!isPresentEmployee(tempId, employeeProducts))
                        employeeRepository.deleteEmployeeById(tempId);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public boolean isPresentEmployee(int id, List<ReferenceTableEmployeeProduct> list) {
        for (ReferenceTableEmployeeProduct employeeProduct : list) {
            if (employeeProduct.getEmployee_id() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean isPresentProduct(int id, List<ReferenceTableEmployeeProduct> list) {
        for (ReferenceTableEmployeeProduct employeeProduct : list) {
            if (employeeProduct.getProduct_id() == id)
                return true;
        }
        return false;
    }

    @Override
    public void save(ReferenceTableEmployeeProduct referenceTableEmployeeProduct) {
        try {
            List<ReferenceTableEmployeeProduct> employeeProducts = new ArrayList<>(getAll());
            employeeProducts.add(referenceTableEmployeeProduct);
            PrintWriter out = new PrintWriter(new FileWriter(Context.employeeProductFilepath));
            out.write(gson.toJson(employeeProducts));
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
