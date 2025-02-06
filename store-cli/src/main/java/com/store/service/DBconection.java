package com.store.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconection {
    Connection CONNECTION = connect();

    static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:store.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }
}
