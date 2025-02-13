package com.store.app.gui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Scanner;

import com.store.app.model.User;
import com.store.app.service.UserService;

@RequiredArgsConstructor
@Component
public class AuthGUI {

    private final Scanner scanner;
    private final UserService userService;

    User user = new User();

    public Boolean login() {
        System.out.print("Enter your username: ");
        user.setUsername(scanner.nextLine());
        System.out.print("Enter your password: ");
        user.setPassword(scanner.nextLine());

        if (userService.loginUser(user)) {
            System.out.println("\n✅ Logged in successfully!");
            return true;
        } else {
            System.out.println("\n⛔️ Incorrect username or password.");
            return false;
        }
    }

    public void register() {
        System.out.print("Enter your username: ");
        user.setUsername(scanner.nextLine());
        System.out.print("Enter your password: ");
        user.setPassword(scanner.nextLine());
        if (userService.registerUser(user)) {
            System.out.println("\n✅ Registration successful!");
        } else {
            System.out.println("\n⛔️ Registration failed.");
        }
    }

    public String showAuthMentu() {
        System.out.println("\nWelcome to the tea store! 👋");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Debug");
        System.out.println("4. Exit");
        return scanner.nextLine();
    }

}
