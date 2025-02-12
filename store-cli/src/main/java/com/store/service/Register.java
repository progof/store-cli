package com.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.store.model.User;
import com.store.database.impl.UserRepository;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Register {
    private final UserRepository userRepository;

    public boolean register(User user) {
        // Проверка логина
        if (user.getLogin() == null || user.getLogin().length() < 3) {
            System.out.println("Login is too short or empty");
            return false;
        }

        // Проверка пароля
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            System.out.println("Password is too short or empty");
            return false;
        }

        // Проверка наличия пользователя в базе

        Optional<User> userOptional = userRepository.getUser(user.getLogin());
        if (userOptional.isPresent()) {
            System.out.println("User with login " + user.getLogin() + " already exists");
            return false;
        }

        // Регистрация пользователя

        userRepository.register(user);

        // Если все проверки пройдены
        System.out.println("User " + user.getLogin() + " registered successfully");
        return true;
    }
}
