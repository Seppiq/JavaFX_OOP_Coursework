package com.example.demo1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer idInstruction;
}
