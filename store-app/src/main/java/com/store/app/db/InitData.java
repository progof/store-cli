package com.store.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class InitData {
    // public void addSampleTeas() {
    // try (Connection conn = Database.connect()) {
    // String query = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
    // try (PreparedStatement stmt = conn.prepareStatement(query)) {
    // stmt.setString(1, "Zielona Herbata");
    // stmt.setDouble(2, 12.50); // cena
    // stmt.setInt(3, 100); // ilość
    // stmt.executeUpdate();

    // stmt.setString(1, "Czarna Herbata");
    // stmt.setDouble(2, 10.00);
    // stmt.setInt(3, 150);
    // stmt.executeUpdate();

    // stmt.setString(1, "Herbata Owocowa");
    // stmt.setDouble(2, 15.00);
    // stmt.setInt(3, 50);
    // stmt.executeUpdate();

    // stmt.setString(1, "Herbata Miętowa");
    // stmt.setDouble(2, 13.00);
    // stmt.setInt(3, 70);
    // stmt.executeUpdate();

    // stmt.setString(1, "Herbata Jaśminowa");
    // stmt.setDouble(2, 14.50);
    // stmt.setInt(3, 120);
    // stmt.executeUpdate();
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public void addSampleTeas() {
        try (Connection conn = Database.connect()) {
            // Пример запроса для проверки наличия записи
            String checkQuery = "SELECT COUNT(*) FROM products WHERE name = ?";
            String insertQuery = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";

            // Создадим массивы с данными для вставки
            Object[][] teas = {
                    { "Zielona Herbata", 12.50, 100 },
                    { "Czarna Herbata", 10.00, 150 },
                    { "Herbata Owocowa", 15.00, 50 },
                    { "Herbata Miętowa", 13.00, 70 },
                    { "Herbata Jaśminowa", 14.50, 120 }
            };

            for (Object[] tea : teas) {
                try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, (String) tea[0]);
                    try (var rs = checkStmt.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            // Если запись отсутствует, добавляем
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                                insertStmt.setString(1, (String) tea[0]);
                                insertStmt.setDouble(2, (Double) tea[1]);
                                insertStmt.setInt(3, (Integer) tea[2]);
                                insertStmt.executeUpdate();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
