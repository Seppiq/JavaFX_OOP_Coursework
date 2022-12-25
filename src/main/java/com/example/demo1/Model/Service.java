package com.example.demo1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    private Integer id;

    private String name;

    private String description;

    private Integer price;

    private Integer employeeId;
}
