package com.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.store.model.User;
import com.store.database.impl.UserRepository;

@RequiredArgsConstructor
@Component
public class Login {

    private final UserRepository userRepository;

    public boolean login(User user) {
        if (user.getLogin() == null || user.getLogin().length() < 3) {
            System.out.println("Login is too short or empty");
            return false;
        }

        if (user.getPassword() == null || user.getPassword().length() < 8) {
            System.out.println("Password is too short or empty");
            return false;
        }

        userRepository.login(user);

        userRepository.getUsers().stream().forEach(u -> {
            if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword())) {
                System.out.println("User " + user.getLogin() + " logged in successfully");
            }
        });

        System.out.println("User " + user.getLogin() + " logged in successfully");
        return true;
    }

}
