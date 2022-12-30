package com.example.demo1.Repository;

import com.example.demo1.Model.ReferenceTableEmployeeService;

import java.util.List;

public interface EmployeeServiceRepository {

    List<ReferenceTableEmployeeService> getAll();

    void deleteByEmployeeId(int id);

    void deleteByServiceId(int id);

    boolean isPresentEmployee(int id, List<ReferenceTableEmployeeService> list);

    boolean isPresentService(int id, List<ReferenceTableEmployeeService> list);

    void save(ReferenceTableEmployeeService referenceTableEmployeeService);

}
