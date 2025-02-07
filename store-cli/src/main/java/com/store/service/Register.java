package com.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.store.model.User;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Register {
    public boolean register(User user) {
        Optional<User> optionalUser = Optional.ofNullable(user);

        if (optionalUser.map(User::getLogin)
                .filter(login -> login.length() >= 3).isEmpty()) {
            System.out.println("Login is too short or empty");
            return false;
        }

        if (optionalUser.map(User::getPassword)
                .filter(password -> password.length() >= 8).isEmpty()) {
            System.out.println("Password is too short or empty");
            return false;
        }

        if (optionalUser.map(User::getConfirmPassword).isEmpty()) {
            System.out.println("Confirm password is empty");
            return false;
        }

        if (!optionalUser.map(User::getPassword)
                .equals(optionalUser.map(User::getConfirmPassword))) {
            System.out.println("Passwords do not match");
            return false;
        }

        System.out.println("User " + optionalUser.map(User::getLogin).orElse("") + " registered");
        return true;
    }
}
