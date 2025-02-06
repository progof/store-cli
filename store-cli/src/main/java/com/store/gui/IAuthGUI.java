package com.store.gui;

import com.store.model.User;

public interface IAuthGUI {
    User register();

    User login();

    void logout();

    String showMenu();
}
