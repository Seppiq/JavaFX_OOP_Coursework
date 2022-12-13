package com.example.demodemonoch.service.impl;

import com.example.demodemonoch.model.Product;
import com.example.demodemonoch.repo.ProductRepo;
import com.example.demodemonoch.repo.UserRepo;
import com.example.demodemonoch.repo.impl.ProductRepoImpl;
import com.example.demodemonoch.repo.impl.UserRepoImpl;
import com.example.demodemonoch.service.ProductService;

import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo = new ProductRepoImpl();


    @Override
    public Product getProductById(int id) {
        return productRepo.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.getAllProducts();
    }

    @Override
    public void createProduct(Product product) throws IOException {
        productRepo.createProduct(product);
    }

    @Override
    public List<Product> updateProductById(int id, Product product) {
        return productRepo.updateProductById(id, product);
    }

    @Override
    public List<Product> deleteProductById(int id) {
        return productRepo.deleteProductById(id);
    }
}
