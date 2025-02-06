package com.store.gui.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Scanner;

import com.store.gui.IAuthGUI;
import com.store.model.User;

@RequiredArgsConstructor
@Component
public class AuthGUI implements IAuthGUI {

    private final Scanner scanner;

    @Override
    public User register() {
        User user = new User();
        System.out.println("Register new user\n");
        System.out.println("Enter login:");
        user.setLogin(this.scanner.nextLine());
        System.out.println("Enter password:");
        user.setPassword(this.scanner.nextLine());
        System.out.println("Enter password:");
        user.setConfirmPassword(this.scanner.nextLine());
        return user;
    }

    @Override
    public User login() {
        User user = new User();
        System.out.println("Enter login:");
        user.setLogin(this.scanner.nextLine());
        System.out.println("Enter password:");
        user.setPassword(this.scanner.nextLine());
        return user;
    }

    @Override
    public void logout() {

    }

    @Override
    public String showMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Logout");

        return this.scanner.nextLine();

    }

}
