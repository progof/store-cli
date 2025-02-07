package com.store.database;

import java.util.List;
import java.util.Optional;

import com.store.model.User;

public interface IUserRepository {
    Optional<User> getUser(String login);

    List<User> getUsers();

    default void register(User user) {
        throw new UnsupportedOperationException();
    }

    default void login(User user) {
        throw new UnsupportedOperationException();
    }

    default void persist(User user) {
        throw new UnsupportedOperationException();
    }
}
