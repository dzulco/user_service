package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.model.UserRequest;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public User createUser(UserRequest userRequest) {

        User user = new User();
        user.setEmail(userRequest.email());
        user.setName(userRequest.name());
        return userRepository.save(user);
    }


    public Optional<User> updateUser(Long id, User userDetails) {
        return this.getUserById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        return this.getUserById(id).map(
                user -> {
                    userRepository.delete(user);
                    return true;
                }).
                orElse(false);

    }
}