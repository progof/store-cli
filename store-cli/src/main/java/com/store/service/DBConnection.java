package com.store.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    public static Connection CONNECTION = connect();

    static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:store.db");
            createTables(connection);
            return connection;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    private static void createTables(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "login TEXT NOT NULL, "
                    + "password TEXT NOT NULL);";
            stmt.executeUpdate(createUsersTable);

            String createProductsTable = "CREATE TABLE IF NOT EXISTS products ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "price REAL NOT NULL, "
                    + "description TEXT);";
            stmt.executeUpdate(createProductsTable);

            String createBuyProductTable = "CREATE TABLE IF NOT EXISTS buy_product ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "user_id INTEGER NOT NULL, "
                    + "product_id INTEGER NOT NULL, "
                    + "quantity INTEGER NOT NULL, "
                    + "purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    + "FOREIGN KEY (user_id) REFERENCES users(id), "
                    + "FOREIGN KEY (product_id) REFERENCES products(id));";
            stmt.executeUpdate(createBuyProductTable);

        } catch (Exception e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }
}
