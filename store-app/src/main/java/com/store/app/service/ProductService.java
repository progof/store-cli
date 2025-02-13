package com.store.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.store.app.db.Database;
import com.store.app.model.Product;

@Component
public class ProductService {
    public List<Product> listProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = Database.connect()) {
            String query = "SELECT * FROM products WHERE stock > 0";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                            rs.getInt("stock")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public boolean buyProduct(int productId, int quantity) {
        try (Connection conn = Database.connect()) {
            String query = "UPDATE products SET stock = stock - ? WHERE id = ? AND stock >= ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, quantity);
                stmt.setInt(2, productId);
                stmt.setInt(3, quantity);
                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductById(int productId) {
        try (Connection conn = Database.connect()) {
            String query = "SELECT * FROM products WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, productId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    return new Product(productId, name, price, stock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
