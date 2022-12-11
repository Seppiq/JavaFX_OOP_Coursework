package com.example.demodemonoch.service.impl;

import com.example.demodemonoch.model.User;
import com.example.demodemonoch.repo.UserRepo;
import com.example.demodemonoch.repo.impl.UserRepoImpl;
import com.example.demodemonoch.service.UserService;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo = new UserRepoImpl();


    @Override
    public User getUserById(int id) {
        return userRepo.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public void createUser(User user) throws IOException {
        userRepo.createUser(user);
    }

    @Override
    public List<User> updateEmployeeById(int id, User user) {
        return userRepo.updateEmployeeById(id, user);
    }

    @Override
    public List<User> deleteEmployeeById(int id) {
        return userRepo.deleteEmployeeById(id);
    }
}
