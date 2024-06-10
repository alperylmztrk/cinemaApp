package com.project.cinema.services;

import com.project.cinema.model.User;
import com.project.cinema.repos.UserRepository;
import com.project.cinema.security.jwtauth.dto.SaveUserReq;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(SaveUserReq saveUserReq) {

        User newUser = User.builder()
                .name(saveUserReq.name())
                .surname(saveUserReq.surname())
                .username(saveUserReq.username())
                .password(passwordEncoder.encode(saveUserReq.password()))
                .authorities(saveUserReq.authorities())
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        return userRepository.save(newUser);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Kullanıcı Bulunamadı! " + username);
    }
}
