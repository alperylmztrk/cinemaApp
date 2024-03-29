package com.project.cinema.services;

import com.project.cinema.model.User;
import com.project.cinema.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserById(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundedUser = user.get();
            foundedUser.setName(newUser.getName());
            foundedUser.setSurname(newUser.getSurname());
            foundedUser.setUsername(newUser.getUsername());
            userRepository.save(foundedUser);
            return foundedUser;
        }
            return null;
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
