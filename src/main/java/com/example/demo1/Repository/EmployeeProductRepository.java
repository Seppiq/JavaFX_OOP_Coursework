package com.example.demo1.Repository;

import com.example.demo1.Model.ReferenceTableEmployeeProduct;

import java.util.List;

public interface EmployeeProductRepository {

    List<ReferenceTableEmployeeProduct> getAll();

    void deleteByEmployeeId(int id);

    void deleteByProductId(int id);

    boolean isPresentEmployee(int id, List<ReferenceTableEmployeeProduct> list);

    boolean isPresentProduct(int id, List<ReferenceTableEmployeeProduct> list);

    void save(ReferenceTableEmployeeProduct referenceTableEmployeeProduct);
}
