package com.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.store.model.User;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Register {
    public boolean register(User user) {
        // TODO: implement register with comparing password and confirm password,
        // validate name and save user to database
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            System.out.println("Passwords do not match");
            return false;
        }
        if (user.getLogin().length() < 3) {
            System.out.println("Login is too short");
            return false;
        }
        if (user.getPassword().length() < 8) {
            System.out.println("Password is too short");
            return false;
        }
        System.out.println("User " + user.getLogin() + " registered");

        return true;
    }

}
