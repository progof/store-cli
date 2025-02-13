package com.store.app.gui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.store.app.model.Product;
import com.store.app.model.User;
import com.store.app.db.DebugDB;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class DebugGUI {

    private final Scanner scanner;
    private final DebugDB debugDB;

    public String showDebugMenu() {
        System.out.println("\nWelcome to the debug menu! 👋");
        System.out.println("1. View users data");
        System.out.println("2. View products data");
        System.out.println("3. Exit");
        return scanner.nextLine();
    }

    public void listProducts() {
        List<Product> products = debugDB.viewProductsData();
        for (Product product : products) {
            System.out.println(
                    "\nID:" + product.getId() + ". " + product.getName() + " - Price: " +
                            product.getPrice() + " PLN"
                            + " - Available: " + product.getStock());

        }
    }

    public void listUsers() {
        List<User> users = debugDB.viewUsersData();
        for (User user : users) {
            System.out.println(
                    "\nID:" + user.getId() + ". " + user.getUsername() + " - Password: " +
                            user.getPassword());
        }
    }
}
