package com.example.demodemonoch.repo.impl;


import com.example.demodemonoch.model.Product;
import com.example.demodemonoch.repo.ProductRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

public class ProductRepoImpl implements ProductRepo {

    @Override
    public Product getProductById(int id) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("product.json")) {
            List<Product> users = gson.fromJson(reader, new TypeToken<List<Product>>() {
            }.getType());
            for (Product product : users) {
                if (product.getId() == id) {
                    //System.out.println(user1);
                    return product;
                }
            }
            //return user;
        } catch (Exception e) {
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("product.json")) {
            List<Product> user = gson.fromJson(reader, new TypeToken<List<Product>>() {
            }.getType());
            return user;
        } catch (Exception e) {
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }

    @Override
    public void createProduct(Product product) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("product.json");
        List<Product> users = gson.fromJson(reader, new TypeToken<List<Product>>() {
        }.getType());
        Writer writer = Files.newBufferedWriter(Paths.get("product.json"));
        users.add(product);
        gson.toJson(users, writer);
        writer.close();
    }

    @Override
    public List<Product> updateProductById(int id, Product product) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("product.json")) {
            List<Product> users = gson.fromJson(reader, new TypeToken<List<Product>>() {
            }.getType());
            Writer writer = Files.newBufferedWriter(Paths.get("product.json"));
            for (Product user1 : users) {
                if (user1.getId() == id) {
                    user1.setAmount(product.getAmount());
                    user1.setName(product.getName());
                    user1.setPrice(product.getPrice());
                }
            }
            gson.toJson(users, writer);
            writer.close();
            return users;
        } catch (Exception e) {
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }

    @Override
    public List<Product> deleteProductById(int id) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("product.json")) {
            List<Product> users = gson.fromJson(reader, new TypeToken<List<Product>>() {
            }.getType());
            Writer writer = Files.newBufferedWriter(Paths.get("product.json"));

            ListIterator<Product> iter = users.listIterator();
            while (iter.hasNext()) {
                if (iter.next().getId().equals(id)) {
                    iter.remove();
                }
            }

            gson.toJson(users, writer);
            writer.close();
            return users;
        } catch (Exception e) {
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }
}
