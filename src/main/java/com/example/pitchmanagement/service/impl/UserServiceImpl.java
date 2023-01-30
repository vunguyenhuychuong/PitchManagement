package com.example.pitchmanagement.service.impl;

import com.example.pitchmanagement.entity.User;
import com.example.pitchmanagement.repository.UserRepository;
import com.example.pitchmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createAccount(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findUserByPassword(String password) {
        return userRepository.findByPassword(password);
    }


}
