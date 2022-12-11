package com.example.demodemonoch.repo;

import com.example.demodemonoch.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserRepo {
    public User getUserById(int id);

    public List<User> getAllUsers();

    public void createUser(User user) throws IOException;

    public List<User> updateEmployeeById(int id, User user);

    public List<User> deleteEmployeeById(int id);
}
