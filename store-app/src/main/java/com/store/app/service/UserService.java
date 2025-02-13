package com.store.app.service;

import java.sql.*;

import org.springframework.stereotype.Component;

import com.store.app.util.SecurityUtil;
import com.store.app.db.Database;
import com.store.app.model.User;

@Component
public class UserService {

    public boolean registerUser(User user) {
        String hashedPassword = SecurityUtil.hashPassword(user.getPassword());

        try (Connection conn = Database.connect()) {
            String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, user.getUsername());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        System.out.println(
                                "\n⛔️ User with this username " + "[" + user.getUsername() + "]" + " already exists.");
                        return false;
                    }
                }
            }

            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, hashedPassword);
                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginUser(User user) {
        try (Connection conn = Database.connect()) {
            String query = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getUsername());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    return SecurityUtil.verifyPassword(user.getPassword(), hashedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
