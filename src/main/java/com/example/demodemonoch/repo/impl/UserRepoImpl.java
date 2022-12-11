package com.example.demodemonoch.repo.impl;

import com.example.demodemonoch.model.User;
import com.example.demodemonoch.repo.UserRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

public class UserRepoImpl implements UserRepo {

    @Override
    public User getUserById(int id) {

        Gson gson = new Gson();
        try (FileReader reader = new FileReader("user.json")) {
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
            for (User user : users) {
                if (user.getId() == id) {
                    //System.out.println(user1);
                    return user;
                }
            }
            //return user;
        } catch (Exception e) {
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        Gson gson = new Gson();
        try (FileReader reader = new FileReader("user.json")) {
            List<User> user = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
            return user;
        } catch (Exception e) {
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }

    @Override
    public void createUser(User user) throws IOException {
        Gson gson = new Gson();

        FileReader reader = new FileReader("user.json");
        List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        Writer writer = Files.newBufferedWriter(Paths.get("user.json"));
        users.add(user);
        gson.toJson(users, writer);
        writer.close();
    }

    @Override
    public List<User> updateEmployeeById(int id, User user) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("user.json")) {
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
            Writer writer = Files.newBufferedWriter(Paths.get("user.json"));
            for (User user1 : users) {
                if (user1.getId() == id) {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    user1.setPosition(user.getPosition());
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
    public List<User> deleteEmployeeById(int id) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("user.json")) {
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
            Writer writer = Files.newBufferedWriter(Paths.get("user.json"));

            ListIterator<User> iter = users.listIterator();
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
