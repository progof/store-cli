package com.store.database.impl;

import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;

import com.store.database.IUserRepository;
import com.store.model.User;
import com.store.service.DBConnection;

@Component
public class UserRepository implements IUserRepository {

    private final String GET_ALL_USERS_SQL = "select * from users";
    private final String GET_USER_BY_LOGIN_SQL = "select * from users where login = ?";

    private String seed = "sy2eL273fTUxQoH3Zlm7wM4ZzK3bR4Gh";

    @Override
    public Optional<User> getUser(String login) {
        try {
            PreparedStatement preparedStatement = DBConnection.CONNECTION.prepareStatement(GET_USER_BY_LOGIN_SQL);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("get user by login failed");
        }

        return Optional.empty();
    }

    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DBConnection.CONNECTION.prepareStatement(GET_ALL_USERS_SQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                result.add(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("get users failed");
        }

        return result;
    }

    @Override
    // need hashed password
    public void register(User user) {
        try {
            System.out.println("[DEBUG] Register data: " + user.getLogin() + " " + user.getPassword());
            PreparedStatement preparedStatement = DBConnection.CONNECTION
                    .prepareStatement("insert into users (login, password) values (?, ?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, DigestUtils.md5Hex(user.getPassword() + seed));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("register user failed");
        }
    }

    @Override
    public void login(User user) {
        try {

            System.out.println("[DEBUG] Login data: " + user.getLogin() + " " + user.getPassword());

            PreparedStatement preparedStatement = DBConnection.CONNECTION.prepareStatement(GET_USER_BY_LOGIN_SQL);
            preparedStatement.setString(1, user.getLogin());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String password = DigestUtils.md5Hex(user.getPassword() + seed);
                if (password.equals(rs.getString("password"))) {
                    System.out.println("User " + user.getLogin() + " logged in successfully");
                } else {
                    System.out.println("Password is incorrect");
                }
            } else {
                System.out.println("User with login " + user.getLogin() + " not found");
            }
        } catch (SQLException e) {
            System.out.println("login user failed");
        }
    }

    @Override
    public void persist(User user) {
        throw new UnsupportedOperationException();
    }
}
