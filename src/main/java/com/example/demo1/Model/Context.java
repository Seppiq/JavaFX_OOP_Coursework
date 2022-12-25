package com.example.demo1.Model;

import com.example.demo1.Controller.EmployeeController;
import com.example.demo1.Controller.ProductController;
import com.example.demo1.Controller.ServiceController;
import lombok.Data;

@Data
public class Context {

    public static String filepath;

    private ServiceController serviceController;

    private ProductController productController;

    private EmployeeController employeeController;

}
