package com.example.demodemonoch.service;

import com.example.demodemonoch.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public Product getProductById(int id);

    public List<Product> getAllProducts();

    public void createProduct(Product product) throws IOException;

    public List<Product> updateProductById(int id, Product product);

    public List<Product> deleteProductById(int id);
}
