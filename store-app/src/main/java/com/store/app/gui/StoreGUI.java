package com.store.app.gui;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.store.app.model.Product;
import com.store.app.service.ProductService;

@RequiredArgsConstructor
@Component
public class StoreGUI {

    private final Scanner scanner;
    private final ProductService productService;

    public String showStoreMenu() {
        System.out.println("\nWitaj w sklepie herbacianym! 👋");
        System.out.println("\nWybierz opcję:");
        System.out.println("1. Wyświetl produkty");
        System.out.println("2. Dodaj produkt do koszyka");
        System.out.println("3. Pokaż koszyk");
        System.out.println("4. Zrealizuj zakup");
        System.out.println("5. Wyloguj");
        return scanner.nextLine();
    }

    public void listProducts() {
        List<Product> products = productService.listProducts();
        for (Product product : products) {
            System.out.println(
                    "ID:" + product.getId() + ". " + product.getName() + " - Price: " +
                            product.getPrice() + " PLN"
                            + " - Available: " + product.getStock());

        }
    }

}
