/*
package com.project.cinema.services;

import com.project.cinema.model.User;
import com.project.cinema.repos.UserRepository;
import com.project.cinema.security.basicauth.dto.SaveUserReq;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    User testUser;

   @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("test-name");
        testUser.setSurname("test-surname");
        testUser.setUsername("test-username");

    }

    @Test
    void getAllUsers() {
      */
/*  List<User> savedUserList = new ArrayList<>();
        User user1 = new User(1L, "Alper", "Yılmaztürk", "alperylmztrk");
        User user2 = new User(2L, "Jane", "Doe", "janedoe");
        savedUserList.add(user1);
        savedUserList.add(user2);

        when(userRepository.findAll()).thenReturn(savedUserList);

        List<User> userListFromDb = userService.getAllUsers();

        assertEquals(savedUserList.size(), userListFromDb.size());
        assertEquals(savedUserList.get(0), userListFromDb.get(0));
        assertEquals(savedUserList.get(1).getName(), userListFromDb.get(1).getName());*//*

    }

    @Test
    void saveUser() {

        SaveUserReq saveUserReq = new SaveUserReq("Alper", "Yılmaztürk", "alperylmztrk", "password", null);


        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(saveUserReq);

        User savedUser = userService.saveUser(user);

        assertEquals(savedUser.getName(), user.getName());
        assertEquals(savedUser.getSurname(), user.getSurname());

    }

    @Test
    void getUserById() {

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));

        User userFromDb = userService.getUserById(testUser.getId());

        assertNotNull(userFromDb);
        assertEquals(testUser.getName(), userFromDb.getName());

    }

    @Test
    void updateUserById() {

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Alper");
        savedUser.setSurname("Yılmaztürk");
        savedUser.setUsername("alperylmztrk");

        User updatedUser = new User();
        updatedUser.setName("Test change name");
        updatedUser.setSurname("Test change surname");
        updatedUser.setUsername("Test change username");

        when(userRepository.findById(savedUser.getId())).thenReturn(Optional.of(savedUser));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);


        System.out.println(savedUser);
        User updatedUserFromDb = userService.updateUserById(savedUser.getId(), updatedUser);

        System.out.println(savedUser);
        System.out.println(updatedUserFromDb);
        assertEquals(savedUser.getId(), updatedUserFromDb.getId());
        assertEquals(savedUser, updatedUserFromDb);
        Mockito.verify(userRepository, Mockito.times(1)).findById(savedUser.getId());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

    }

    @Test
    void deleteUserById() {

        userService.deleteUserById(1L);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);

    }
}*/
