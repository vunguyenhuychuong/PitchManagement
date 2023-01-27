package com.example.pitchmanagement.service;

import com.example.pitchmanagement.entity.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void createAccount(User user);

    User findUserById(String id);

    List<User> getUserList();
}
