package com.store.app.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.store.app.model.Product;
import com.store.app.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DebugDB {

    public List<User> viewUsersData() {
        List<User> users = new ArrayList<>();

        try (Connection conn = Database.connect()) {
            String query = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Product> viewProductsData() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = Database.connect()) {
            String query = "SELECT * FROM products";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    products.add(new Product(rs.getInt("id"), rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("stock")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
