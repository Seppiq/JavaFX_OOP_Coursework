package com.example.demo1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;

    private List<Service> services;

    private List<Product> products;
}