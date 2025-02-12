package com.store.gui.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Scanner;

import com.store.gui.IStoreGUI;
import com.store.model.Product;

@RequiredArgsConstructor
@Component
public class StoreGUI implements IStoreGUI {

    private final Scanner scanner;

    @Override
    public String showMenu() {
        System.out.println("1. Show products");
        System.out.println("2. Add product to cart");
        System.out.println("3. Show cart");
        System.out.println("4. Remove product from cart");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
        return scanner.nextLine();
    }

    @Override
    public Product getProducts() {
        Product product = new Product();
        System.out.println("Enter product ID:");
        product.setName(scanner.nextLine());
        System.out.println("Enter product price:");
        product.setPrice(Double.parseDouble(scanner.nextLine()));
        return product;
    }

}
