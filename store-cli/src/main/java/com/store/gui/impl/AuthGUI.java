package com.store.gui.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Scanner;

import com.store.gui.IAuthGUI;
import com.store.model.User;
import com.store.service.Register;
import com.store.database.impl.UserRepository;
import com.store.database.IUserRepository;

@RequiredArgsConstructor
@Component
public class AuthGUI implements IAuthGUI {

    private final Scanner scanner;
    private final Register registerService;
    private final IUserRepository userRepository;

    @Override
    public User register() {
        User user = new User();
        System.out.println("Register new user\n");

        // Ввод логина
        System.out.println("Enter login:");
        user.setLogin(this.scanner.nextLine());

        // Ввод пароля
        System.out.println("Enter password:");
        user.setPassword(this.scanner.nextLine());

        // Ввод подтверждения пароля
        String confirmPassword;
        while (true) {
            System.out.println("Confirm password:");
            confirmPassword = this.scanner.nextLine();
            if (user.getPassword().equals(confirmPassword)) {
                break;
            } else {
                System.out.println("Passwords do not match, try again.");
            }
        }

        // Валидация и регистрация через сервис
        if (registerService.register(user)) {
            System.out.println("User " + user.getLogin() + " successfully registered.");
            return user;
        } else {
            System.out.println("Registration failed.");
            return null;
        }
    }

    @Override
    public User login() {
        User user = new User();
        System.out.println("Enter login:");
        user.setLogin(this.scanner.nextLine());
        System.out.println("Enter password:");
        user.setPassword(this.scanner.nextLine());

        // userRepository.getUsers().stream().forEach(u -> {
        // System.out.println(u.getLogin());
        // });

        userRepository.login(user);

        return user;

    }

    @Override
    public void logout() {
        System.out.println("Logged out successfully.");
    }

    @Override
    public String showMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Logout");

        return this.scanner.nextLine();
    }
}
