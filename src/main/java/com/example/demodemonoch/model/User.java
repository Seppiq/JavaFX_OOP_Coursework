package com.example.demodemonoch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String position;
}
