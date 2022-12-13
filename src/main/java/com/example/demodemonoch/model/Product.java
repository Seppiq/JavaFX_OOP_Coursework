package com.example.demodemonoch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Product {

    private Integer id;

    private String name;

    private Integer price;

    private Integer amount;
}
