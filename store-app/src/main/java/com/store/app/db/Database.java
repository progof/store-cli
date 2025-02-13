package com.store.app.db;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:storeapp.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void init() {
        try (Connection conn = connect()) {
            String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL)";
            String productTable = "CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "price DOUBLE NOT NULL, " +
                    "stock INTEGER NOT NULL)";
            String cartTable = "CREATE TABLE IF NOT EXISTS cart (" +
                    "user_id INTEGER, " +
                    "product_id INTEGER, " +
                    "quantity INTEGER, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id), " +
                    "FOREIGN KEY(product_id) REFERENCES products(id))";

            Statement stmt = conn.createStatement();
            stmt.execute(userTable);
            stmt.execute(productTable);
            stmt.execute(cartTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
