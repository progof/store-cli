package com.store.app.controller;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.store.app.db.Database;
import com.store.app.model.CartItem;
import com.store.app.model.Product;
import com.store.app.service.ProductService;

@Component
public class CartController {
    private List<CartItem> items;
    private static final Scanner scanner = new Scanner(System.in);
    private ProductService productService = new ProductService();

    public CartController() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("\n🛒 The cart is empty.");
            return;
        }
        System.out.println("\n🛒 Cart:");
        for (CartItem item : items) {
            System.out.println(item.getProduct().getName() + " x" + item.getQuantity() + " - Price: "
                    + item.getTotalPrice() + " PLN");
        }
        System.out.println("Total cost: " + getTotalCost() + " PLN");
    }

    public double getTotalCost() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void checkout() {
        for (CartItem item : items) {
            int remainingStock = item.getProduct().getStock() - item.getQuantity();
            if (remainingStock < 0) {
                System.out.println("Insufficient quantity: " + item.getProduct().getName());
                return;
            }
            updateProductStock(item.getProduct().getId(), remainingStock);
        }
        System.out.println("The purchase completed successfully!");
        items.clear();
    }

    private void updateProductStock(int productId, int newStock) {
        try (Connection conn = Database.connect()) {
            String query = "UPDATE products SET stock = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, newStock);
                stmt.setInt(2, productId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToCart() {
        System.out.print("Enter the product ID to add to your cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product product = productService.getProductById(productId);
        if (product != null) {
            addProduct(product, quantity);
            System.out.println("Product added to cart.");
        } else {
            System.out.println("Product not found.");
        }
    }
}
